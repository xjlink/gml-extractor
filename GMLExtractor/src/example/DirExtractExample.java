package example;


import cn.bupt.gml.extract.DirHandler;

public class DirExtractExample {

	
	/**
	 * Dir extract example
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		MyGMLMapping mapping = new MyGMLMapping("D:/example-mapping.xml");
		DirHandler handler = new DirHandler(mapping);
		
		long start_time = java.util.Calendar.getInstance().getTimeInMillis();
		handler.scan("D:/test-dir");
		System.out.println("finish time ========="+ String.valueOf(java.util.Calendar.getInstance().getTimeInMillis()- start_time) + "ms");

	}
	
	
}
