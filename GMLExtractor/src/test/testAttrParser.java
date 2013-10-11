package test;

import java.util.Map;

import cn.bupt.gml.extract.TString;

public class testAttrParser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String InputString = "catalogRow chapterName=\"第一回 明代九成的商业税进了谁的口袋\" ebookPageNum=\"16\" ";
		Map<String,String> attrMap = TString.snatch_attribute(InputString);
		for(Map.Entry<String,String> entry:attrMap.entrySet()){
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
	}

}
