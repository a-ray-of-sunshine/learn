package com.sunshine.learn.collection;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemTest {

	public static void main(String[] args) throws IOException {
		
		FileSystem fs = FileSystems.getDefault();
		
		for(FileStore store : fs.getFileStores()){
			System.out.println(store.getTotalSpace() / (1024 * 1024 * 1024));
		}
		
		for(Path  path : fs.getRootDirectories()){
			System.out.println(path.toAbsolutePath());
			System.out.println(path.getFileName());
		}
		
	}
}
