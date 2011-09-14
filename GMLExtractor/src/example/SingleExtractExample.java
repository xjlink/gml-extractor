package example;

import java.io.File;

import cn.bupt.gml.extract.Document;

public class SingleExtractExample {

	/**
	 * single document example
	 * @param args
	 */
	public static void main(String[] args) {
		
		MyGMLMapping mapping = new MyGMLMapping("D:/Jave Code/gmlextractor/example-mapping.xml");
		mapping.parse(new File("D:/Jave Code/gmlextractor/example.xml"));
		int count = 0;
		for (Document doc : mapping.getDocList()) {
			System.out.println("(" + (++count)+").");
			doc.print();
		}
	
	}
	
}
