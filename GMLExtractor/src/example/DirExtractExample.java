package example;



public class DirExtractExample {

	
	/**
	 * Dir extract example
	 * @param args
	 */
	
	public static void main(String[] args) {
		
		MyGMLMapping mapping = new MyGMLMapping("D:/example-mapping.xml");
		MyDirHandler handler = new MyDirHandler(mapping);
		handler.scan("D:/test-dir");
	}
	
	
}
