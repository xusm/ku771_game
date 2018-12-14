package com.ku771.util;

import java.util.UUID;

/**   
 * @title：生成UUID 
 * @class：CodecUtil   
 * @description：   
 * @author：Eric
 * @date：2018年12月6日
 * @return:uuid
 */
public class CodecUtil {
	
	public static String createUUID(){
		return UUID.randomUUID().toString();
	}
}
