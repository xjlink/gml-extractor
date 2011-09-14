package example;


import cn.bupt.gml.extract.*;

public class MyGMLMapping extends GMLMapping {

	public MyGMLMapping(String mappingName) {
		super(mappingName);
	}

	protected Document extendField(String rootDir, String currentDir, Document doc){
		
		return doc;
	}
	
}
