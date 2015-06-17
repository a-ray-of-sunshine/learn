package com.sunshine.learn.nio;

import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Iterator;
import java.util.Set;

public class SelectedIO {

	public static void dispatch(SelectableChannel channel) {
		
		Selector selector = null;
		SelectionKey key = null;
		try {
			selector = Selector.open();
			channel.configureBlocking(false);
			key = channel.register(selector, SelectionKey.OP_READ);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		while (true) {
			int readyChannels = -1;
			try {
				readyChannels = selector.select();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (readyChannels == 0)
				continue;
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> keyIterator = selectedKeys.iterator();
			while (keyIterator.hasNext()) {
				key = keyIterator.next();
				if (key.isAcceptable()) {
					// a connection was accepted by a ServerSocketChannel.
				} else if (key.isConnectable()) {
					// a connection was established with a remote server.
				} else if (key.isReadable()) {
					// a channel is ready for reading
				} else if (key.isWritable()) {
					// a channel is ready for writing
				}
			}
		}
	}

}
