package com.qheeshow.eway.common.util;

import java.util.ArrayList;
import java.util.List;

public class UrlFilterConfig {

	private static final UrlFilterConfig instance = new UrlFilterConfig();

	private List<String> excludeUrlList = new ArrayList<String>();

	private void loadConfig() {
		String[] excludeUrls = Config.get("excluded_url_pattern").split(",");
//		System.out.println(excludeUrls);
		for (String excludeUrl : excludeUrls) {
			String patternUrl = convertToPattern(excludeUrl);
			if (!StringUtil.isEmpty(patternUrl))
				excludeUrlList.add(patternUrl);
		}
	}
	
	private static String convertToPattern(String input) {
		if (StringUtil.isEmpty(input))
			return null;
		if(input.matches("\\.[^\\.]+"))//.js , .gif ... -> *.js , *.gif ...
			input = "*"+input;
		input = input.trim().replaceAll("\\.", "\\\\.");
		if (input.contains("*"))
			return input.replaceAll("\\*", ".*");
		return input;
	}

	private UrlFilterConfig() {
		try{
			loadConfig();
		}catch(Throwable t){
			t.printStackTrace();
			throw t;
		}
	}

	public static UrlFilterConfig getInstance() {
		return instance;
	}

	/**
	 * 判定一个请求的资源是否可以免除登录认证
	 * @param contextPath 应用的context上下文
	 * @param servletPath 请求资源对应的路径
	 * @return true：该资源不需要登录认证；false：该资源需要登录认证
	 */
	public boolean matchExcludeUrl(String contextPath, String servletPath) {
		for (String excludeUrl : excludeUrlList) {
			if (servletPath.matches(excludeUrl) || (contextPath+servletPath).matches(excludeUrl))
				return true;
		}
		return false;
	}

	public static void main(String args[]){
		System.out.println(convertToPattern(".jsp"));
	}
}