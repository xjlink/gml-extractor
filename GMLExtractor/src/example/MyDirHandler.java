package example;

import java.io.File;

import cn.bupt.gml.extract.DirHandler;
import cn.bupt.gml.extract.Document;
import cn.bupt.gml.extract.GMLMapping;

public class MyDirHandler extends DirHandler {

	public MyDirHandler(GMLMapping mapping) {
		super(mapping);
		// TODO Auto-generated constructor stub
	}
	
	protected void onFileOver(File file){
		for(Document doc:buffer){
			doc.print();
		}
	}
	
	protected void onScanOver(String path){
		System.out.println();
		System.out.println("Start Time = "+start_time);
		System.out.println("Finish Time = "+finish_time);
		System.out.println("Total XML Files :"+file_count);
		System.out.println("Total Records :"+record_count);
	}

}
