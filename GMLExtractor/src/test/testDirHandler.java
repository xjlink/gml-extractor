package test;


import junit.framework.TestCase;

import cn.bupt.gml.extract.DirHandler;
import cn.bupt.gml.extract.GMLMapping;


public class testDirHandler extends TestCase{

	
	/**
	 * extract all document in one directory
	 */
	
	public void testDir() {
		
		System.setProperty("mapping.config.path", "D:/mapping/");
		GMLMapping mapping = new GMLMapping("iop");
		DirHandler handler = new DirHandler(mapping);
		
		long start_time = java.util.Calendar.getInstance().getTimeInMillis();
		handler.scan("D:/iop");
		System.out.println("finish time ========="+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()- start_time) + "ms");

	}
	
	
}
