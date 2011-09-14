package cn.bupt.gml.extract;

import java.io.File;
import java.util.*;

public class MappingConfig {

	
	public static MappingConfig getInstance(String name){
		synchronized (_instanceMap) {
			if(_instanceMap.containsKey(name)){
				return _instanceMap.get(name);
			}else{
				MappingConfig _instance = new MappingConfig(name);
				_instanceMap.put(name, _instance);
				return _instance;
			}
		}
	}
	
	public static List<String> getNameList(){
		File dir = new File(MAPPING_CONFIG_PATH);
		List<String> nameList = new ArrayList<String>();
		Map<String,String> _map = new HashMap<String,String>();
		String[] names = dir.list();
		if(names!=null&&names.length>0)
		for(String name : names){
			name = name.split("\\.")[0];
			if(name.substring(name.length()-1, name.length()).matches("[0-9]")){
				name = name.substring(0,name.length()-1);
			}
			if(!_map.containsKey(name)){
				_map.put(name, null);
				nameList.add(name);
			}
		}
		return nameList;
	}
	
	public static List<String> getBatchList(String sname){
		File dir = new File(MAPPING_CONFIG_PATH);
		List<String> nameList = new ArrayList<String>();
		String[] names = dir.list();
		if(names!=null&&names.length>0)
		for(String name : names){
			name = name.split("\\.")[0];
			if(name.indexOf(sname)>=0){
				nameList.add(name.replaceAll(sname, ""));
			}
		}
		return nameList;
	}
	
	public static List<String> getConfigList(){
		File dir = new File(MAPPING_CONFIG_PATH);
		List<String> nameList = new ArrayList<String>();
		String[] names = dir.list();
		if(names!=null&&names.length>0)
		for(String name : names){
			nameList.add(name);
		}
		return nameList;
	}
	
	public void readFromXML(File xmlFile){
		
		GMLReader reader = new GMLReader(){

			@Override
			protected void content(String content) {
				// TODO Auto-generated method stub
				if(_xpath.lastIndexOf("/path")>=0){
					invertedPath.addPath(content, entry.getName());
					entry.addPath(content, null, null);
				}
			}

			@Override
			protected void endElement(String qName) {
				// TODO Auto-generated method stub
				this._pop(qName);
			}

			@Override
			protected void startElement(String qName, Map<String, String> attr) {
				// TODO Auto-generated method stub
				this._push(qName);
				if(qName.equalsIgnoreCase("DocumentMapping")){
					if(attr.containsKey("encoding")){
						encoding = attr.get("encoding");
					}
				}
				if(qName.equalsIgnoreCase("document")){
					node = attr.get("node");
				}
				if(qName.equalsIgnoreCase("entry")){
					entry = new Entry(attr.get("name"), attr.get("separator"), attr.get("placeholder"), attr.get("group"), attr.get("tag"), attr.get("multiValued"));
					entry_map.put(attr.get("name"), entry);
				}
			}
			
			private StringBuffer _xpath = new StringBuffer();
			private Entry entry;
			private void _push(String e){
				_xpath.append("/"+e);
			}			
			private void _pop(String e){
				int n = _xpath.lastIndexOf("/"+e);
				if(n>=0){
					_xpath.delete(n, _xpath.length());
				}
			}
			
		};
		reader.parse(xmlFile);
	}
	
	private static HashMap<String,MappingConfig> _instanceMap = new HashMap<String, MappingConfig>();
	
	private static String MAPPING_CONFIG_PATH = System.getProperty("mapping.config.path");
	
	private MappingConfig(String name){
		String filePath = null;
		if(MAPPING_CONFIG_PATH!=null && MAPPING_CONFIG_PATH.length()>0){
			filePath = MAPPING_CONFIG_PATH+File.separator+name+".mapping.xml";
		}else{
			filePath = name;
		}
		readFromXML(new File(filePath));
	}
	
	private String encoding = "";
	private String node = "";
	private Map<String,Entry> entry_map = new HashMap<String,Entry>();
	private InvertedPath invertedPath = new InvertedPath();


	public static String getMAPPING_CONFIG_PATH() {
		return MAPPING_CONFIG_PATH;
	}

	public static void setMAPPING_CONFIG_PATH(String mapping_config_path) {
		MAPPING_CONFIG_PATH = mapping_config_path;
	}

	public String getEncoding() {
		return encoding;
	}

	public String getNode() {
		return node;
	}
	
	public Map<String, Entry> getEntry_map() {
		return entry_map;
	}

	public InvertedPath getInvertedPath(){
		return invertedPath;
	}
	
}
