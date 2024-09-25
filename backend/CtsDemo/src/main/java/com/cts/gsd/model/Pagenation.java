package com.cts.gsd.model;

import java.util.List;

import org.springframework.data.domain.Page;

public class Pagenation {
	private List<RequestForm> content;
	private long totalElement;
	private int totalPage;
	private int pageSize;
	private int pageNo;
	private boolean isLast;
	private boolean isFirst;

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public List<RequestForm> getContent() {
		return content;
	}

	public void setContent(List<RequestForm> content) {
		this.content = content;
	}

	public long getTotalElement() {
		return totalElement;
	}

	public void setTotalElement(long totalElement) {
		this.totalElement = totalElement;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
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

	public boolean isLast() {
		return isLast;
	}

	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public static Pagenation createPagenation(Page<RequestForm> page)
 {
	 Pagenation pagenation=new Pagenation();
	 List<RequestForm> requestForm = page.getContent();
	 pagenation.setContent(requestForm);
	 pagenation.setTotalPage(page.getTotalPages());
	 pagenation.setTotalElement(page.getTotalElements());
	 pagenation.setPageSize(page.getSize());
	 pagenation.setPageNo(page.getNumber());
	 pagenation.setFirst(page.isFirst());
	 pagenation.setLast(page.isLast());
	 return pagenation;
	}

}