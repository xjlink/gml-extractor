package cn.bupt.gml.extract;

import java.util.*;

public class Statistics {

	private MappingConfig mappingConfig;
	
	public Statistics(MappingConfig mappingConfig){
		
		this.mappingConfig = mappingConfig;
	}
	
	public List<String> getMissingEntry(Document doc){
		List<String> missingList = new ArrayList<String>();
		for(String entryName:mappingConfig.getEntry_map().keySet()){
			if(!doc.getDocument().containsKey(entryName)){
				missingList.add(entryName);
			}
		}		
		return missingList;
	}
	
	public List<String> getMissingEntryByGroup(Document doc, String group){
		List<String> missingList = new ArrayList<String>();
		Map<String,Entry> entry_map = mappingConfig.getEntry_map();
		for(String entryName:entry_map.keySet()){
			if(entry_map.get(entryName).getGroup().equals(group)){
				if(!doc.getDocument().containsKey(entryName)){
					missingList.add(entryName);
				}
			}
		}		
		return missingList;
	}
}
