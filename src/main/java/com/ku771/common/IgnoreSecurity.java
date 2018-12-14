package com.ku771.common;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**   
 * @title：自定义注解     
 * @class：IgnoreSecurity   
 * @description：   标识是否忽略REST安全性检查
 * @author：Eric
 * @date：2018年12月6日
 * @return: uuid
 */
@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME) 
@Documented
public @interface IgnoreSecurity {

}
