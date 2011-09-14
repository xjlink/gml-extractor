package cn.bupt.gml.extract;

import java.util.*;

public class InvertedPath {

	public InvertedPath(){
		invertedMap = new TreeMap<String,String>();
	}
	
	
	public void addPath(String path, String name){
		invertedMap.put(path, name);
	}
	
	public String getKeyName(String path){
		return invertedMap.get(path);
	}
	
	public List<String> comparePath(String real_path){
		List<String> matchPathList = new ArrayList<String>();
		for(Iterator<String> it=invertedMap.keySet().iterator();it.hasNext();){
			String inside_path = it.next();
			String campare_path = inside_path.replaceAll("\\[[^\\]]+\\]", "").replaceAll("/@.*", "");
			if(real_path.endsWith(campare_path))	
				matchPathList.add(inside_path);
		}
		return matchPathList;
	}
	
	public List<String> comparePath(String real_path, String attr_value){
		List<String> matchPathList = new ArrayList<String>();
		if(attr_value==null||attr_value.length()==0){
			for(Iterator<String> it=invertedMap.keySet().iterator();it.hasNext();){
				String inside_path = it.next();
				String campare_path = inside_path.replaceAll("\\[[^\\]]+\\]", "").replaceAll("/@.*", "");
				if(real_path.endsWith(campare_path))	
					matchPathList.add(inside_path);
			}
		}else{
			for(Iterator<String> it=invertedMap.keySet().iterator();it.hasNext();){
				String inside_path = it.next();
				String campare_path = inside_path.replaceAll("\\[[^\\]]+\\]", "").replaceAll("/@.*", "");
				String campare_attr = TString.snatch(inside_path, "=", "]");
				if(real_path.endsWith(campare_path)&&attr_value.equals(campare_attr))	
					matchPathList.add(inside_path);
			}
		}
		return matchPathList;
	}
	
	
	public InvertedPath clone(){
		
		return new InvertedPath();
	}
	
	private TreeMap<String,String> invertedMap;
	
}
