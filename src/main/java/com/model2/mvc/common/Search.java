package com.model2.mvc.common;


public class Search {
	
	private int currentPage;
	private String searchCondition;
	private String searchKeyword;
	private int pageNumSize;
	private int displayCount;
	private String buyerId;
	
	public Search(){
	}
	
	public int getPageNumSize() {
		return pageNumSize;
	}
	public void setPageNumSize(int pageUnit) {
		this.pageNumSize = pageUnit;
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
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
		return (this.getCurrentPage() - 1) * this.getDisplayCount() + 1;
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

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}


}