package com.qheeshow.eway.common.web;

import java.util.HashMap;

import com.qheeshow.eway.common.page.PageInfo;

public class HaResponse extends HashMap<String, Object> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String KEY_SUSSESS = "success";
	public static final String KEY_DATA = "data";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_ERROR_CODE = "errorCode";
	
	public static final String KEY_Current_Page = "currentPage";
	public static final String KEY_Total_Count = "totalCount";
	public static final String KEY_Total_Page = "totalPage";
	

	public static HaResponse sussess(Object data) {
		HaResponse resp = new HaResponse();
		resp.put(KEY_SUSSESS, true);
		resp.put(KEY_DATA, data);
		return resp;
	}

	public static HaResponse sussess() {
		HaResponse resp = new HaResponse();
		resp.put(KEY_SUSSESS, true);
		return resp;
	}

	public static HaResponse fail(Object data) {
		HaResponse resp = new HaResponse();
		resp.put(KEY_SUSSESS, false);
		resp.put(KEY_DATA, data);
		return resp;
	}

	public static HaResponse fail() {
		HaResponse resp = new HaResponse();
		resp.put(KEY_SUSSESS, false);
		return resp;
	}

	public HaResponse message(String message) {
		this.put(KEY_MESSAGE, message);
		return this;
	}

	public HaResponse errorCode(String errorCode) {
		this.put(KEY_ERROR_CODE, errorCode);
		return this;
	}
	
	public HaResponse page(PageInfo page) {
		this.put(KEY_Current_Page, page.getCurrentPage());
		this.put(KEY_Total_Count, page.getTotalCount());
//		this.put(KEY_Total_Page, page.getTotalPage());
		return this;
	}
	
	
}
