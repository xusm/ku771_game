package com.ku771.util;

/**   
 * @title：字符串工具类     
 * @class：StringUtil   
 * @description：  字符串工具类     
 * @author：Eric
 * @date：2018年12月6日
*/
public class StringUtil {
	  
	/**     
	 * @description 给定字符串是否为空或空串
	 * @author：Eric     
	 * @date 2018年12月6日
	 * @param str
	 * @return     
	 */
	public static boolean isNotEmpty(String str) {
		if (str != null && str.length() != 0) {
			return true;
		}
		return false;
	}

	/**     
	 * @description 给定字符串是否为空或空串
	 * @author：Eric
	 * @date 2018年12月6日
	 * @param str
	 * @return     
	 */
	public static boolean isEmpty(String str) {
		if (str != null && str.length() != 0) {
			return false;
		}
		return true;
	}
}
