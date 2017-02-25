package com.qheeshow.eway.common.page;

import com.qheeshow.eway.common.bean.BaseBean;
/**
 * 分页信息模型类，结合{@link com.ha.common.db.mybatis.plugin.page.PageInterceptor PageInterceptor}类
 * 使用以实现MyBatis查询的自动分页。
 *
 */
public class PageInfo extends BaseBean{

	private static final long serialVersionUID = 1;

	// pagesize ，每一页显示多少
	private int pageSize = 20;
	// 总页数
	private int totalPage;
	// 总记录数
	private int totalCount;
	// 当前页数
	private int currentPage = 1;
	
	// 当前显示到的ID, 在mysql limit 中就是第一个参数.
	private int rowOffset;

	public int getPageSize() {
		return pageSize;
	}

	public PageInfo setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public PageInfo setTotalPage(int totalPage) {
		this.totalPage = totalPage;
		return this;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public PageInfo setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		return this;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public PageInfo setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		return this;
	}

	public int getRowOffset() {
		return currentPage<=0?0:(currentPage-1)*pageSize;
	}

}
