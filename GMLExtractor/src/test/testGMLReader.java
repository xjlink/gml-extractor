package test;

import java.io.File;
import java.net.URL;

import cn.bupt.gml.extract.GMLReader;
import junit.framework.TestCase;

public class testGMLReader extends TestCase{
	
	private GMLReader reader = new GMLReader();

	public void testLocal(){
		
		File  file = new File("D:/Jave Code/NewFile.xml");
		reader.parse(file);
	}
	
	
	public void testRemote(){
		
		try{
			URL url = new URL("http://www.baidu.com");
			reader.parse(url.openStream());
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
