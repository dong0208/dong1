package com.kaishengit.util;

import java.util.List;

public class Page<T> {

	//	总页码
	private int totalpage;
	//	当前页码
	private int pageNo;
	//	当前页的数据
	private List<T> items;
	//	每页显示的数量
	private int pageSize = 3;
	//	起始行号
	private int start;

	public Page(int total, int pageNo) {
		// 总页码
		this.totalpage = total / pageSize; // 16/5 =3

		if (total % pageSize != 0) {
			this.totalpage++;
		}
		
		if(totalpage <1) {
			totalpage = 1;
		}
		
		if(pageNo > totalpage) {
			pageNo = totalpage;
		}
		
		// 如果客户端传来的pageNo比1小，则默认显示第一页
		if(pageNo < 1) {
			pageNo = 1;
		}
		
		// 当前页码
		this.pageNo = pageNo;

		// 起始行号
		this.start = (pageNo - 1) * pageSize;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

}
