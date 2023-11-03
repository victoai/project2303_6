package com.batis.paging;

public class PageHandler {
	int currentPage; //현재 페이지
	int totRecords; //총 레코드 수
	int pageSize; //페이지 사이즈(한페이지에 보이는 글의 수 )
	int grpSize; //그룹의 사이즈
	
	int totalPage; 
	int currentGrp;
	int grpStartPage;
	int grpEndPage;
	
	public PageHandler(int currentPage, int totRecords, int pageSize, int grpSize) {
		super();
		this.currentPage = currentPage;
		this.totRecords = totRecords;
		this.pageSize = pageSize;
		this.grpSize = grpSize;
		
		calcPage();
	}
	
	public PageHandler(int currentPage, int totRecords, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.totRecords = totRecords;
		this.pageSize = pageSize;
		this.grpSize = 2;
		
		calcPage();
	}
	
	private void calcPage() {
		//1.총페이지수
		//2.현재그룹
		//3.현재그룹시작페이지
		//4.현재그룹의 마지막 페이지
		
		//1. 총페이지수
		int remain = totRecords % pageSize;
		if(remain == 0) {
			totalPage = totRecords/pageSize;
		}else {
			totalPage = totRecords/pageSize+1;
		}
		//현재 그룹 구하기
		int remain2 = currentPage % grpSize;
		if(remain2 == 0) {
			currentGrp = currentPage / grpSize;
		}else {
			currentGrp = currentPage / grpSize+1;
		}
		
		//현재 그룹 시작, 끝 페이지
		grpStartPage = (currentGrp -1 )* grpSize +1;
		grpEndPage = currentGrp* grpSize;
		
		
		if(grpEndPage > totalPage) {
			grpEndPage = totalPage;
		}
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
	public int getGrpSize() {
		return grpSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public int getCurrentGrp() {
		return currentGrp;
	}
	public int getGrpStartPage() {
		return grpStartPage;
	}
	public int getGrpEndPage() {
		return grpEndPage;
	}
	public static void main(String[] args) {
		
	}
}
