package cn.bupt.gml.extract;

import java.util.*;

public class Document {

	private HashMap<String,List<String>> document = null;

	public Document(){
		document = new HashMap<String,List<String>>();
	}
	
	public void addField(String fieldName, String fieldValue){
		if (fieldValue != null && fieldValue.length() > 0) {
			if (document.containsKey(fieldName)) {
				document.get(fieldName).add(fieldValue);
			} else {
				List<String> valueList = new ArrayList<String>();
				valueList.add(fieldValue);
				document.put(fieldName, valueList);
			}
		}
	}
	
	
	public HashMap<String, List<String>> getDocument() {
		return document;
	}

	public List<String> getField(String fieldName){
		return document.get(fieldName);
	}
	
	public String getSingleValue(String fieldName){
		List<String> fieldList = document.get(fieldName);
		if(fieldList!=null&&fieldList.size()>0){
			return fieldList.get(0);
		}else{
			return null;
		}
	}
	
	public void removeField(String fieldName){
		document.remove(fieldName);
	}

	public void clearField(String fieldName){
		document.clear();
	}
	
	public Document clone(){		
		Document copy_document = new Document();
		for(Iterator<String> keyIt = document.keySet().iterator();keyIt.hasNext();){
			for(String str:document.get(keyIt.next())){
				copy_document.addField(new String(keyIt.next()), new String(str));
			}
		}		
		return copy_document;		
	}
	
	public void print(){
		System.out.println("---------------Document----------------");
		for(String key:document.keySet()){
			System.out.println("--"+key);
			List<String> ls= document.get(key);
			for(String value:ls){
				System.out.println("     "+value);
			}
		}
		System.out.println("---------------------------------------");
	}
	
}
