package com.model2.mvc.common;


public class Search {
	
	private int page;
	private String searchCondition;
	private String searchKeyword;
	private int pageNumSize;
	private int displayCount;
	
	public Search(){
	}
	
	public int getPageNumSize() {
		return pageNumSize;
	}
	public void setPageNumSize(int pageUnit) {
		this.pageNumSize = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}

	public int getStartIndex() {
		return (this.getPage() - 1) * this.getDisplayCount() + 1;
	}

	public int getEndIndex() {
		return this.getStartIndex() + this.getDisplayCount() - 1;
	}

	public int getDisplayCount() {
		return displayCount;
	}

	public void setDisplayCount(int pageSize) {
		this.displayCount = pageSize;
	}
}