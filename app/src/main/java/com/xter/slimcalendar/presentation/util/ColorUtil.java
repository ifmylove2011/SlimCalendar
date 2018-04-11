package com.xter.slimcalendar.presentation.util;

/**
 * Created by XTER on 2018/4/10.
 * 颜色相关工具
 */

public class ColorUtil {
	/**
	 * 六位标准色值--"d89830"
	 * @param str 六位色值
	 * @return 六位色值
	 */
	public static String reserve(String str){
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<str.length();i++){
			String st=str.charAt(i)+"";
			int temp=Integer.parseInt(st,16);
			sb.append(Integer.toHexString(15 - temp).toUpperCase());
		}
		return sb.toString();
	}

	public static String reserve(int color){
		String colorStr = Integer.toHexString(color);
		String newColorStr = reserve(colorStr);
		if(newColorStr.startsWith("00")){
			newColorStr = "ff"+newColorStr.substring(2);
		}
		return "#"+newColorStr;
	}
}
