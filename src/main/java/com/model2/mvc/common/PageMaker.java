package com.model2.mvc.common;


public class PageMaker {

    ///Field
    private int currentPage;        // 현재페이지
    private int totalCount;        // 총 게시물 수
    private int pageUnit;            // 하단 페이지 번호 화면에 보여지는 수 -> pageNumSize
    private int pageSize;            // 한 페이지당 보여지는 게시물수 -> displayCount
    private int maxPage;            // 최대 페이지 번호(전체 페이지) -> maxPageNum
    private int beginUnitPage;    //화면에 보여지는 페이지 번호의 최소수 -> currentStartPageNum
    private int endUnitPage;    //화면에 보여지는 페이지 번호의 최대수 -> currentEndPageNum


    public PageMaker() {
    }

    public PageMaker(int currentPage, int totalCount, int pageUnit, int pageSize) {
        this.totalCount = totalCount;
        this.pageUnit = pageUnit;
        this.pageSize = pageSize;

        //분모가 0이 되면 안되니까
        this.maxPage = (pageSize == 0) ? totalCount : ((totalCount - 1) / pageSize) + 1;
        this.currentPage = (currentPage > maxPage) ? maxPage : currentPage;

        this.beginUnitPage = ((currentPage - 1) / pageUnit) * pageUnit + 1;

        if (maxPage <= pageUnit) {
            this.endUnitPage = maxPage;
        } else {
            this.endUnitPage = beginUnitPage + (pageUnit - 1);
            if (maxPage <= endUnitPage) {
                this.endUnitPage = maxPage;
            }
        }
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getBeginUnitPage() {
        return beginUnitPage;
    }

    public int getEndUnitPage() {
        return endUnitPage;
    }

//    public void setEndUnitPage(int endUnitPage) {
//        this.endUnitPage = endUnitPage;
//    }
//
//    public void setEndUnitPage(){
//        this.endUnitPage = this.beginUnitPage + (this.pageUnit -1);
//        if( this.maxPage <= this.endUnitPage){
//            this.endUnitPage = maxPage;
//        }
//    }


//    public void setPrePage() {
//        this.beginUnitPage -= this.pageUnit;
//        setEndUnitPage();
//    }
//
//    public void setNextPage() {
//        this.beginUnitPage += this.pageUnit;
//        setEndUnitPage();
//    }
//
//    public int getEndPageNumber(int pageUnit, int startPage, int endPage, int maxPage) {
//        return pageUnit < maxPage ? pageUnit : maxPage;
//    }

    @Override
    public String toString() {
        return "Page [currentPage=" + currentPage + ", totalCount="
                + totalCount + ", pageUnit=" + pageUnit + ", pageSize="
                + pageSize + ", maxPage=" + maxPage + ", beginUnitPage="
                + beginUnitPage + ", endUnitPage=" + endUnitPage + "]";
    }

    public boolean isEnablePrev() {
        return this.currentPage <= this.pageUnit;
    }


    public String getPrevPage() {
        return String.valueOf(this.currentPage - 1);
    }

    public boolean isEnableNext() {
        return this.endUnitPage < this.maxPage;
    }


    public String getNextPage() {
        return String.valueOf(this.endUnitPage + 1);
    }
}