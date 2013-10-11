
package cn.bupt.gml.extract;

import java.util.HashMap;
import java.util.Map;


/**
 * @author xie
 *
 */
public class TString extends Object{

	/*************** Find methods to get Position ******************/
	public static final int FindPosition(String InputString, String keyString, int appearTimes){
		int position = 0;
		while(appearTimes>0){
			position = InputString.indexOf(keyString,position);
			if (appearTimes<0)	break;
			appearTimes--;
		}
		return position;
	}
	
	/*************** Snatch methods to get SubString ******************/
	public static final String snatch(String InputString, String beginString, String endString){
		String OutputString;
		int begin = 0, end = 0;
		begin = InputString.indexOf(beginString,end)+beginString.length();
		end = InputString.indexOf(endString,begin);
		if(begin>=beginString.length()&&end>begin){
			OutputString = InputString.substring(begin,end);
		}else{
			OutputString = "";
		}		
		return OutputString;
	}
	
	public static final String snatch_withHead(String InputString, String beginString, String endString){
		String OutputString;
		int begin = 0, end = 0;
		begin = InputString.indexOf(beginString,end);
		end = InputString.indexOf(endString,begin);
		if(begin>=0&&end>begin){
			OutputString = InputString.substring(begin,end);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final String snatch_center(String InputString, String beginString, String endString){
		String OutputString;
		int begin = 0, end = 0;
		begin = InputString.indexOf(beginString,end)+beginString.length();
		end = InputString.indexOf(endString,begin);
		if(begin>=beginString.length()&&end>begin){
			OutputString = InputString.substring(begin,end);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final String snatch_withTail(String InputString, String beginString, String endString){
		String OutputString;
		int begin = 0, end = 0;
		begin = InputString.indexOf(beginString,end)+beginString.length();
		end = InputString.indexOf(endString,begin)+endString.length();
		if(begin>=beginString.length()&&end>begin){
			OutputString = InputString.substring(begin,end);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final String snatch_withBoth(String InputString, String beginString, String endString){
		String OutputString;
		int begin = 0, end = 0;
		begin = InputString.indexOf(beginString,end);
		end = InputString.indexOf(endString,begin)+endString.length();
		if(begin>=0&&end>begin){
			OutputString = InputString.substring(begin,end);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final String snatchtoTail_withHead(String InputString, String beginString){
		String OutputString;
		int begin = 0;
		begin = InputString.indexOf(beginString);
		if(begin>=0&&begin<InputString.length()){
			OutputString = InputString.substring(begin);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final String snatchtoTail_withoutHead(String InputString, String beginString){
		String OutputString;
		int begin = 0;
		begin = InputString.indexOf(beginString)+beginString.length();
		if(begin>=beginString.length()&&begin<InputString.length()){
			OutputString = InputString.substring(begin);
		}else{
			OutputString = "";
		}
		return OutputString;
	}
	
	public static final Map<String,String> snatch_attribute(String InputString){
		Map<String,String> attrMap = new HashMap<String,String>();
		if(InputString!=null && !InputString.isEmpty()){
			StringBuffer _buf = new StringBuffer();
			boolean keylock = false , valuelock = false;
			String key = null, value = null;
			for(int i=0;i<InputString.length();i++){
				char c = InputString.charAt(i);
				if(c=='='){
					if(!keylock){
						key = _buf.toString();
						keylock = true;
					}
					_buf.delete(0, _buf.length());
				}else if(c=='"'){
					if(!valuelock){
						valuelock=true;
					}else{
						value = _buf.toString();
						attrMap.put(key, value);
						keylock = false;
						valuelock = false;
						_buf.delete(0, _buf.length());
					}
				}else if(c==' '){
					if(!keylock || !valuelock){
						_buf.delete(0, _buf.length());
					}else{
						_buf.append(c);
					}
				}else{
					_buf.append(c);
				}
			}
		}
		return attrMap;
	}
	
}
