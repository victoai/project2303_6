package com.visitKorea.content;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.visitKorea.paging.PageHandler;

	
@Component
public class ContentService {
	
	@Autowired
	ContentRepository rep;
	
	// 공공데이터 content 전체 삽입
	public void getInsertAll(ArrayList<ContentDTO> list) {
		rep.insertAll(list);
	}
	
	public Integer getTotalCnt() {
		int result = rep.totalCnt();
		return result;
	}
	
	// content 10개씩 표출
	public ArrayList<ContentDTO> getSelectAll(int startList, int endList) {
		ArrayList<ContentDTO> list = rep.selectAll(startList, endList);
		return list;
	}
	
	
	// 페이징
	public PageHandler getPaging(int currentPage) {
		
		int totRecords = getTotalCnt();
		int pageSize = 10;
		int grpSize = 10;
		
		PageHandler handler = new PageHandler(currentPage, totRecords, pageSize, grpSize);
		return handler;
	}
	
	
}
