package com.model2.mvc.common;


public class PageMaker {

    ///Field
    private int currentPage;        // 현재페이지
    private int totalCount;        // 총 게시물 수
    private int pageNumSize;            // 하단 페이지 번호 화면에 보여지는 수 -> pageNumSize
    private int displayCount;            // 한 페이지당 보여지는 게시물수 -> displayCount
    private int maxPageNum;            // 최대 페이지 번호(전체 페이지) -> maxPageNum
    private int currentStartPageNum;    //화면에 보여지는 페이지 번호의 최소수 -> currentStartPageNum
    private int currentEndPageNum;    //화면에 보여지는 페이지 번호의 최대수 -> currentEndPageNum


    public PageMaker() {
    }

    public PageMaker(int currentPage, int totalCount, int pageNumSize, int displayCount) {
        this.totalCount = totalCount;
        this.pageNumSize = pageNumSize;
        this.displayCount = displayCount;

        //분모가 0이 되면 안되니까
        this.maxPageNum = (displayCount == 0) ? totalCount : ((totalCount - 1) / displayCount) + 1;
        this.currentPage = (currentPage > maxPageNum) ? maxPageNum : currentPage;

        this.currentStartPageNum = ((currentPage - 1) / pageNumSize) * pageNumSize + 1;

        if (maxPageNum <= pageNumSize) {
            this.currentEndPageNum = maxPageNum;
        } else {
            this.currentEndPageNum = currentStartPageNum + (pageNumSize - 1);
            if (maxPageNum <= currentEndPageNum) {
                this.currentEndPageNum = maxPageNum;
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getCurrentStartPageNum() {
        return currentStartPageNum;
    }

    public int getCurrentEndPageNum() {
        return currentEndPageNum;
    }

    public boolean isEnablePrev() {
        return this.currentPage <= this.pageNumSize;
    }

    public String getPrevPage() {
        return String.valueOf(this.currentPage - 1);
    }

    public boolean isEnableNext() {
        return this.currentEndPageNum < this.maxPageNum;
    }

    public String getNextPage() {
        return String.valueOf(this.currentEndPageNum + 1);
    }
}
