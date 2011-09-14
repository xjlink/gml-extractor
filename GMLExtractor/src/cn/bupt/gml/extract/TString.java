
package cn.bupt.gml.extract;


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
	
}
