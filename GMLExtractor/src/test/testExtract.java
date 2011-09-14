package test;

import java.io.File;

import junit.framework.TestCase;

import cn.bupt.gml.extract.Document;
import cn.bupt.gml.extract.GMLMapping;


public class testExtract extends TestCase{

	/**
	 * extract single document
	 */
	
	public void testSingleDoc() {
		
		System.setProperty("mapping.config.path", "D:/Jave Code/mytool/");
		long free_mem = Runtime.getRuntime().freeMemory();
		long start_time = java.util.Calendar.getInstance().getTimeInMillis();

		GMLMapping mapping = new GMLMapping("springerbook");//
		System.out.println("Init time ========="+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()	- start_time) + "ms");
		start_time = java.util.Calendar.getInstance().getTimeInMillis();
		mapping.parse(new File("D:/Jave Code/mytool/426_3-540-77206-5_BookFrontMatter_1.xml.Meta"));//
		
		System.out.println("parse time ========="+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()- start_time) + "ms");
		System.out.println();
		
		int count = 0;
		for (Document doc : mapping.getDocList()) {
			System.out.println("(" + (++count)+").");
			doc.print();
			
			System.out.println("missing fields: "+mapping.getStatistics().getMissingEntryByGroup(doc,"request"));
			System.out.println();System.out.println();
		}
		
		System.out.println("finish time ========="+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()- start_time) + "ms");

		System.out.println(">>>>>>>>memory cost "+ ((free_mem - Runtime.getRuntime().freeMemory()) / 1024)+ "KB");
	
			
	}
	
	
	
}
