package com.sunshine.learn.thread.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 *    CLH算法将多个线程的自旋分散在不同的节点进行，当一个线程释放锁时，只会将其后继结点的cache失效掉，
 *    对于其他线程没有任何影响，其他线程还是在各自的cache中进行局部自旋。这样，就大大减少了bus上的交通阻塞，不会导致锁的释放被延迟。
       另外，CLH算法由于总是将最近一个申请锁的线程放在链表的最后，从而带来了一个附加的功能，那就是对锁竞争的公平性，保证了先申请锁的线程先得到锁。
       而且，CHL算法也是空间有效的，对于N个线程，L个锁，如果每个线程每次最多只能获取一个锁，则需要的存储空间是O（N+L），其中N个线程对应N个myNode，L个锁对应L个tail。
       当然，CLH算法也有一个缺点，在cache-less的NUMA架构体系下，因为每个线程自旋的是其前驱线程的QNode中的locked域，如果内存位置比较远，则性能是要打折扣的。
       
       参考： [CLH锁](http://xw-z1985.iteye.com/blog/2064372)
 *
 */
public class CLHLock implements ILock {

	private AtomicReference<QNode> tail;
	private ThreadLocal<QNode> myNode;
	private ThreadLocal<QNode> myPred;
	
	public CLHLock() {
		tail = new AtomicReference<QNode>(new QNode());
		
		// 这样赋值的目地是出于性能的考虑，initialValue 方法
		// 在 myNode 的调用 get 方法时，才被调用
		myNode = new ThreadLocal<QNode>(){
			@Override
			protected QNode initialValue() {
				return new QNode();
			}
		};
		
		myPred = new ThreadLocal<QNode>(){
			@Override
			protected QNode initialValue() {
				return null;
			}
		};
	}

	@Override
	public void lock() {
		// get 方法会使用 上面的 initialValue 方法得到调用 
		QNode qnode = myNode.get();
		// 将 myNode 的 locked 字段设置成true表示正在等待锁
		qnode.locked = true;
		
		// 将 myNode,放置到尾部, 并返回其前驱结点
		QNode pred = tail.getAndSet(qnode);

		// 将上面方法调用返回的前驱结点，放置到本地缓存中，
		myPred.set(pred);

		// 最后该线程一直在其前驱节点的locked域自旋，
		// 直到locked域变为false，即前驱节点释放了锁。
		// 注意，对于SMP架构，由于是在cache中自旋，所以是高效的；
		// 参考：各种主流CPU架构：[SMP、NUMA、MPP体系结构介绍](http://www.cnblogs.com/yubo/archive/2010/04/23/1718810.html)
		while(pred.locked);
	}

	@Override
	public void unlock() {
		// 通知 qnode 的后继结点（线程），可以获取获锁了。
		QNode qnode = myNode.get();
		// locked 设置为 false 使得在 qnode 的后继结点上
		// 自旋的线程退出 while 循环，从而获得锁
		qnode.locked = false;

		// !!!!!! important !!!!!!!
		// 重用前驱节点，将前驱节点设置为myNode，以便下一次该线程获取锁时使用。
		// 之所以能重用，是因为此时其前驱线程不会再使用该前驱节点了。
		// 而该线程原来的myNode，可能被其后继线程或tail引用(也就是上面的 qnode 所指向的 node, 很有可能被后继结点在其上自旋)。
		// 所以，当前线程的 myNode 必须 重新 set 
		// 对于java语言来说，重用实际上是没有必要的，因为如果不重用，则前驱节点是可以被jvm回收的，从而降低内存的使用。
		// myPred 的 locked 字段 一定是 false的, 因为，myNode能够获取到锁的原因就是 其 pred 结点的 locked 字段为 false.
		// 这样就可以重用这个结点了。
		// myNode 必须得重新设置值(设置成 locked 字段为 false 的一个 node), 否则, 当前线程再次获取获锁的时候就会存在问题
		myNode.set(myPred.get());
		
		// 使用下面的代码也可以，新设置一个node
		// myNode.set(new QNode());
		
		// 如果，不重设 myNode，可能有出现下面的情况：
		// 线程A， 线程B, 其中 A 是 B 的前驱, B 在  A 的 myNode的 locked 字段上自旋
		// 线程 A 第一次，获取锁成功，执行到 上面的 qnode.locked = false; 处,退出，（没有重设 myNode）
		// 此时，线程B在 A 的 myNode 上自旋成功（因为 A 的 myNode的locked=false）, 线程B成功执行
		// 线程 A 开始了第二次获取锁， 线程 A 拥有 myNode 的引用(A.myNode) ， 线程 B的前驱结点还是 myNode (B.myPred)
		// 这样当 A 和 B 开始第二次获取锁的时候，会造成 对 原来的 myNode 的并发访问，而这个访问在 lock 中并没有进行同步
		// 所以线程执行会出问题
	}
}

class QNode{
	// locked 
	//  	1. false, 对应的线程已经释放锁
	//		2. ture 线程正在等待锁，或线程已经获取到锁 
	public volatile boolean locked = false;
}