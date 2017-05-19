package com.sunshine.learn.collection;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ZipFSPUser {

	public static void main(String[] args) throws IOException {

		  Map<String, String> env = new HashMap<>(); 
	        env.put("create", "true");
	        // locate file system by using the syntax 
	        // defined in java.net.JarURLConnection
	        // URI uri = URI.create("jar:file:/temp/zipfs/zipfstest.zip");
	         Path uri = Paths.get("/temp/zipfs/zipfstest.ooo");
	        
	        try (FileSystem zipfs = FileSystems.newFileSystem(uri, ZipFSPUser.class.getClassLoader())) {
	            Path externalTxtFile = Paths.get("/msdia80.dll");
	            Files.createDirectories(zipfs.getPath("/asfas"));
	            Path pathInZipfile = zipfs.getPath("/asfas/msdia80.dll");          
	            // copy a file into the zip file
	            Files.copy( externalTxtFile,pathInZipfile, 
	                    StandardCopyOption.REPLACE_EXISTING ); 
	            
	            System.out.println(zipfs.provider());
	        }
	        
	        Path path = Paths.get("/msdia80.dll");
	        Iterator<Path> iterator = path.iterator();
	        while(iterator.hasNext()){
	        	Path p = iterator.next();
	        	System.out.println(p.getFileName());
	        }
	        
	        Path p = path.resolveSibling("temp/zipfs");
	        System.out.println(p.getFileName());
	        
	        InputStream stream = Files.newInputStream(path);
	        System.out.println(stream.read());
	        System.out.println(stream.read());
	        System.out.println(stream.read());
	        System.out.println(stream);
	        
	        
	        Path p1 = Paths.get("/temp/dir");
	        // Files.createFile(p1);
	        Files.createDirectories(p1);
	        // Buffer
	        // NativeThread
	        // FileStore

	}
}
