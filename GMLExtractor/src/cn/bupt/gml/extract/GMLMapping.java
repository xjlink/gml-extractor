package cn.bupt.gml.extract;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.*;


public class GMLMapping extends GMLReader {

	public GMLMapping(String mappingName){
		super();
		mappingConfig = MappingConfig.getInstance(mappingName);
		invertedPath = mappingConfig.getInvertedPath();
		statistics = new Statistics(mappingConfig);
	}
	
	public GMLMapping(String mappingName, FileWriter fw){
		super();
		this.fw = fw;
		mappingConfig = MappingConfig.getInstance(mappingName);
		invertedPath = mappingConfig.getInvertedPath();
		statistics = new Statistics(mappingConfig);
	}
	
	public void parse(File file){	
		_gml_path = file.getAbsolutePath();
		_current_dir = file.getParent();
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(file);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(File file, String encoding){	
		_gml_path = file.getAbsolutePath();
		_current_dir = file.getParent();
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(file, encoding);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(URL url){	
		_gml_path = url.getPath();
		_current_dir = url.getHost();
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(url.openStream());
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(URL url, String encoding){	
		_gml_path = url.getPath();
		_current_dir = url.getHost();
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(url.openStream(), encoding);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(byte[] bytes){	
		_gml_path = "/";
		_current_dir = "/";
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(bytes);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(byte[] bytes, String encoding){	
		_gml_path = "/";
		_current_dir = "/";
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(bytes, encoding);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(InputStream inputStream){	
		_gml_path = "/";
		_current_dir = "/";
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(inputStream);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}
	
	public void parse(InputStream inputStream, String encoding){	
		_gml_path = "/";
		_current_dir = "/";
		_xpath = new StringBuffer();
		appending = false;	tag = false;
		_entity_buffer = new StringBuffer();
		_combine_buffer = new StringBuffer();
		_combine_counter = 0;
		docList = new ArrayList<Document>();
		try {
			super.parse(inputStream, encoding);
		} catch (Exception e){
			e.printStackTrace();
			if(fw!=null){
				parseException(_root_dir,_gml_path,e);
			}
		}
	}

	protected Document extendField(String rootDir, String currentDir, Document doc){		
		return doc;
	}
	
	protected void parseException(String rootDir,String filePath,Exception e){
		try {
			fw.write(filePath + "\t---\t" + e.getMessage() + "\n");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public List<Document> getDocList() {
		return docList;
	}
	

	public void startElement(String qName, Map<String,String> attr) {
		
		this._push(qName);
		if(qName.equals(mappingConfig.getNode()))
			_doc = new Document();
		if(tag)
			_entity_buffer.append("<"+qName+">");
		// Get Attribute
		List<String> matchPathList = invertedPath.comparePath(_xpath.toString());
		if (matchPathList.size()>0) {	
			for(String inside_path:matchPathList){
				String key = invertedPath.getKeyName(inside_path);			
				int index_n = inside_path.indexOf("/@");
				if (index_n > 1) {
					String attrName = inside_path.substring(index_n+2,inside_path.length());
					String value = attr.get(attrName);
					addCombineValue(key, value);
				}else{
					t_attr_key = TString.snatch(inside_path, "[@", "=");
					t_attr_value = attr.get(t_attr_key);
					_entity_buffer = new StringBuffer();
					appending = true;
					if(mappingConfig.getEntry_map().get(key).isTag())	
						tag = true;
				}
			}
		}
	}

	public void endElement(String qName) {
		
		// Get Content
		List<String> matchPathList = invertedPath.comparePath(_xpath.toString(),t_attr_value);
		if (matchPathList.size()>0 && matchPathList.get(0).indexOf("/@") < 0) {
			String key = invertedPath.getKeyName(matchPathList.get(0));
			String value = _entity_buffer.toString().trim();
			appending = false;	tag = false;
			addCombineValue(key, value);
		}
		this._pop(qName);
		if(qName.equals(mappingConfig.getNode())){
			_doc.addField("GML_Path", _gml_path);
			_doc = extendField(_root_dir,_current_dir,_doc);
			if(docList.size()<=2000){
				docList.add(_doc);
			}
		}
		if(tag){
			_entity_buffer.append("</"+qName+">");
		}
	}

	public void content(String content){

		if(appending){
			_entity_buffer.append(content.trim());
		}
	}

	private void addCombineValue(String key, String value){
		Entry entry = mappingConfig.getEntry_map().get(key);
		int path_size = entry.getPath_list().size();
		if(path_size>1){
			if(++_combine_counter < path_size){
				if(value!=null)
				_combine_buffer.append(value).append(entry.getSeperator());					
			}else{
				if(value!=null)
				_combine_buffer.append(value);		
				value = _combine_buffer.toString().trim();
				if(value==null||value.length()==0)
					value = entry.getPlaceholder();
				if(mappingConfig.getEntry_map().get(key).isMultiValued()){
					_doc.addField(key, value);
				}else{
					if(!_doc.getDocument().containsKey(key)){
						_doc.addField(key, value);
					}
				}
				_combine_buffer.delete(0, _combine_buffer.length());
				_combine_counter = 0;
			}
		}else{
			if(value==null||value.length()==0)
				value = entry.getPlaceholder();
			if(mappingConfig.getEntry_map().get(key).isMultiValued()){
				_doc.addField(key, value);
			}else{
				if(!_doc.getDocument().containsKey(key)){
					_doc.addField(key, value);
				}
			}
		}
	}
	
	private FileWriter fw;
	private String _gml_path;
	private String _current_dir;
	private String _root_dir;
	private String t_attr_key;
	private String t_attr_value;
	
	private final MappingConfig mappingConfig;
	private Statistics statistics;
	private InvertedPath invertedPath;
	private StringBuffer _xpath;
	private Document _doc;
	private boolean appending;
	private boolean tag;
	private StringBuffer _entity_buffer;
	private StringBuffer _combine_buffer;
	private int _combine_counter;
	private List<Document> docList;
	
	private void _push(String e){
		if(e!=null&&e.length()>0){
			_xpath.append("/"+e);
		}
	}
	
	private void _pop(String e){
		if(e!=null&&e.length()>0){
			int n = _xpath.lastIndexOf("/"+e);
			if(n>=0){
				_xpath.delete(n, _xpath.length());
			}
		}
	}

	public String get_root_dir() {
		return _root_dir;
	}

	public void set_root_dir(String _root_dir) {
		this._root_dir = _root_dir;
	}

	public Statistics getStatistics() {
		return statistics;
	}


	
}
