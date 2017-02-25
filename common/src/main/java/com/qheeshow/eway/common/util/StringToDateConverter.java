/**  
 * @Project: znoa
 * @Title: StringToDateConverter.java
 * @Package com.oa.commons.converter
 * @date 2013-5-23 下午4:26:45
 * @Copyright: 2013 
 */
package com.qheeshow.eway.common.util;

import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 用于SpringMVC配置的日期转换工具
 *
 */
public class StringToDateConverter implements Converter<String,Date>{
	
	public Date convert(String source) {
		return StringUtils.isEmpty(source)?null:DateUtil.parseDate(source); 
	}
}
