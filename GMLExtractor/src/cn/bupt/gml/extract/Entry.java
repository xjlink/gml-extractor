package cn.bupt.gml.extract;

import java.util.*;

public class Entry {
	
	private String name;
	private String seperator;
	private String placeholder;
	private String group;
	private boolean tag;
	private boolean multiValued;
	private List<Path> path_list;
	
	
	public Entry(String name,String seperator,String placeholder,String group,String tag,String multiValued){
		this.name = name;
		if(seperator==null)	this.seperator = "";
		else	this.seperator = seperator;
		if(placeholder==null)	this.placeholder = "";
		else	this.placeholder = placeholder;
		if(group==null)	this.group = "";
		else	this.group = group;
		if(tag!=null&&tag.equals("true"))	this.tag = true;
		else	this.tag = false;
		if(multiValued!=null&&multiValued.equals("true"))	this.multiValued = true;
		else	this.multiValued = false;
		path_list = new ArrayList<Path>();
	}
	
	public void addPath(String path, String order, String connector){
		Path pathObj = new Path();
		/*
		pathObj.setPath(path);
		pathObj.setOrder(order);
		pathObj.setConnector(connector);
		*/
		path_list.add(pathObj);
	}
	
	private class Path{
		
		/**
		 * for later functions
		 */
		
		/**
		private String path;
		private String order;
		private String connector;
		
		public String getPath() {
			return path;
		}
		public void setPath(String path) {
			this.path = path;
		}
		public String getOrder() {
			return order;
		}
		public void setOrder(String order) {
			this.order = order;
		}
		public String getConnector() {
			return connector;
		}
		public void setConnector(String connector) {
			this.connector = connector;
		}		
		*/	
	}




	public String getName() {
		return name;
	}

	public String getSeperator() {
		return seperator;
	}
	
	public String getPlaceholder() {
		return placeholder;
	}

	public boolean isTag() {
		return tag;
	}
	
	public boolean isMultiValued() {
		return multiValued;
	}

	public List<Path> getPath_list() {
		return path_list;
	}

	public String getGroup() {
		return group;
	}
	
	
	
}
