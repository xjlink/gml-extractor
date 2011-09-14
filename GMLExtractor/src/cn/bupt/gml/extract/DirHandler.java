package cn.bupt.gml.extract;

import java.io.*;
import java.util.*;

public class DirHandler {
	
	private GMLMapping mapping;
	private String postfix;
	protected List<Document> buffer;
	protected int file_count = 0;
	protected int record_count = 0;
	protected Date start_time;
	protected Date finish_time;
	
	public DirHandler(GMLMapping mapping){
		this.mapping = mapping;		
	}
	
	public void scan(String filePath){
		start_time = new Date();
		File file = new File(filePath);
		mapping.set_root_dir(filePath);
		this.postfix = ".xml";
		if(!file.isDirectory()){
			scanOneFile(file);
		}else{
			scanDir(file);
		}
		finish_time = new Date();
		onScanOver(filePath);
	}
	
	public void scan(String filePath, String postfix){
		start_time = new Date();
		File file = new File(filePath);
		mapping.set_root_dir(filePath);
		this.postfix = postfix;
		if(!file.isDirectory()){
			scanOneFile(file);
		}else{
			scanDir(file);
		}
		finish_time = new Date();
		onScanOver(filePath);
	}
	
	private void scanDir(File file){
		System.out.println("Coming in to the Directory " + file.getPath());
		File files[] = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				scanDir(files[i]);
			} else {
				scanOneFile(files[i]);
			}
		}
		onDirOver(file);
	}
	
	private void scanOneFile(File file){
		if (file.getName().endsWith(postfix)) {
			System.out.println("Scaning......"	+ file.getAbsolutePath() + ", total files:"+ file_count);
			mapping.parse(file);
			buffer = mapping.getDocList();
			if(buffer.size()>0){
				file_count++;
				record_count += buffer.size();
				onFileOver(file);
			}
		} else {
			System.out.println(file.getAbsolutePath()+ " is not the xml file to be scaned");
		}
	}
	
	protected void onFileOver(File file){
		
	}
	
	protected void onDirOver(File dir){
		
	}
	
	protected void onScanOver(String path){
		
	}
}
