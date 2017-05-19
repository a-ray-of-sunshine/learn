package com.sunshine.learn.collection;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.util.List;

/**
 * 检测目录的变化，并实时打印 
 */
public class WatchServiceTest {

	public static void main(String[] args) throws IOException, InterruptedException {
		
		FileSystem fs = FileSystems.getDefault();

		Path path = Paths.get("/temp");
		WatchService ws = fs.newWatchService();
		
		WatchKey watchKey = path.register(ws, StandardWatchEventKinds.ENTRY_CREATE, 
				StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
		
//		while(true){
//			
//			List<WatchEvent<?>> pollEvents = watchKey.pollEvents();
//			
//			for(WatchEvent<?> event : pollEvents){
//				System.out.println(event.kind());
//			}
//			
//			if(!watchKey.reset()){
//				break;
//			}
//		}

	}
}