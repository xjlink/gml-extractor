package cn.bupt.gml.extract;

import java.io.*;
import java.util.*;

public class GMLReader {

	public void parse(byte[] bytes){
		this.parse(new ByteArrayInputStream(bytes));
	}
	
	public void parse(byte[] bytes, String encoding){
		this.parse(new ByteArrayInputStream(bytes), encoding);
	}
	
	public void parse(File file){
		try {
			this.parse(new FileInputStream(file));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void parse(File file, String encoding){
		try {
			this.parse(new FileInputStream(file), encoding);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void parse(InputStream ins){
		try {
			Reader reader = new InputStreamReader(ins);
			parse(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void parse(InputStream ins, String encoding){
		try {
			Reader reader = new InputStreamReader(ins,encoding);
			parse(reader);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	public void parse(Reader reader){
		try {
			int _pre = 0;		
			int _cur = 0;
			StringBuffer _buf = new StringBuffer();
			
			while ((_cur = reader.read()) != -1) {
                if(_cur == '<' || _cur == '>'){
                	if(_pre == '>' && _cur == '<'){
                		content(_buf.toString());
                	}
                	if(_pre == '<' && _cur == '>'){
                		if(_buf.charAt(0)=='!' || _buf.charAt(0)=='?'){
                			continue;
                		}
                		if(_buf.charAt(0)=='/'){
                			endElement(_buf.deleteCharAt(0).toString());
                		}else{
                			/*
                			String[] strs = _buf.toString().split(" ");
                			String qName = strs[0];
                			Map<String,String> attr = new HashMap<String,String>();
                			if(strs.length>1){
                				for(int i=1;i<strs.length;i++){
                					if(strs[i].indexOf('=')>0){
                						String[] pair = strs[i].split("=");
                						attr.put(pair[0], pair[1].replaceAll("\"", "").replaceAll("'", ""));
                					}
                				}
                			}*/
                			String qName = _buf.toString().split(" ")[0];
                			Map<String,String> attr = TString.snatch_attribute(_buf.toString());
                			startElement(qName,attr);
                			if(_buf.charAt(_buf.length()-1)=='/'){
                				endElement(qName);
                			}
                		}
                	}
                	_pre = _cur;
                	_buf = new StringBuffer();
                }else{
                	if (_cur != '\r' && _cur != '\n') {
                        _buf.append((char)_cur);
                    }
                }
            }
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	
	protected void startElement(String qName, Map<String,String> attr){
		System.out.println("startElement = "+qName);
		for(String key:attr.keySet()){
			System.out.println(key+"="+attr.get(key));
		}
	}
	
	protected void endElement(String qName){
		System.out.println("endElement = "+qName);
	}
	
	protected void content(String content){
		System.out.println("content = "+content);
	}
	
}
