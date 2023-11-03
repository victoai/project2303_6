package com.batis.slide;

public class SlideHandler {
	int currentPage; //현재 페이지
	int totRecords; //총 레코드 수
	int pageSize; //페이지 사이즈(한페이지에 보이는 글의 수 )
	
	int totalPage;
	
	public SlideHandler(int currentPage, int totRecords, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.totRecords = totRecords;
		this.pageSize = pageSize;
		this.totalPage = (totRecords/pageSize)+1;
	}
	
	public SlideHandler() {
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public int getTotRecords() {
		return totRecords;
	}
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public String toString() {
		return "PageHandler2 [currentPage=" + currentPage + ", totRecords=" + totRecords + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + "]";
	}
}
