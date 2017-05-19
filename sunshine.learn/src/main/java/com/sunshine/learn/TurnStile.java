package com.sunshine.learn;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class TurnStile {
	
	public static void main(String[] args) {
		
		// 闸机
		TurnStile ts = new TurnStile();
		
		// 排队过闸机的人
		List<People> peoples = new LinkedList<>();
		peoples.add(new People());
		peoples.add(new People());
		peoples.add(new People());
		peoples.add(new People());
		peoples.add(new People());

		for(People people : peoples){
			people.throughTurnStile(ts);
			System.out.println(String.format("闸机处于 %s 状态", ts.getTurnStileStatus()));
		}
	}

	private TurnStileStatus status = TurnStileStatus.Locked;
	
	public void handleEvent(ExtenalEvent event){
		
		switch(event){
			case Push:
				if(status == TurnStileStatus.Locked){
					// 状态不变
				}else{
					status = TurnStileStatus.Locked;
				}
				break;
			case Coin:
				if(status == TurnStileStatus.Locked){
					status = TurnStileStatus.Unlocked;
				}else{
					// 状态不变
				}
				break;
		}
		
	}
	
	public TurnStileStatus getTurnStileStatus(){
		return status;
	}
}

// 闸机的状态
enum TurnStileStatus{
	// 锁定（关闭)
	Locked,
	// 解锁（开启）
	Unlocked
}

// 闸机可以响应的外部事件
enum ExtenalEvent{
	// 投币（刷卡）
	Coin,
	// 推机械手臂（进入）
	Push
}

class People{

	// 不同的人面对闸机有会发出多种行为
	private List<ExtenalEvent> actions = new ArrayList<>();
	
	private String name;
	private static int count;

	public People() {
		name = "people" + count++;
		Random random = new Random();
		actions.add(0 == random.nextInt(2) ? ExtenalEvent.Coin : ExtenalEvent.Push);
		actions.add(1 == random.nextInt(2) ? ExtenalEvent.Push : ExtenalEvent.Coin);
	}

	// 人具有通过闸机的方法
	public void throughTurnStile(TurnStile ts) {
		System.out.print(String.format("%s 尝试用行为 %s 通过闸机, 结果是: ", name, actions));
		for(ExtenalEvent event : actions){
			ts.handleEvent(event);
		}
	}
}