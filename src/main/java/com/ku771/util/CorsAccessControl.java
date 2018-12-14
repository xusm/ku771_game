package com.ku771.util;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**   
 * @title：跨域操作
 * @class：CorsAccessControl   
 * @description：   header头信息解析以及CORS 跨域 
 * @author：Eric
 * @date：2018年12月6日
 * @param： ServletResponse
*/

public class CorsAccessControl {
	public static void setHearder(ServletResponse res){
		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");//header头信息解析并允许指定域名跨域（设置为*则允许所有）
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "1728000");
        response.setHeader("Access-Control-Allow-Credentials", "true");//是否支持cookie跨域
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
	}
}
