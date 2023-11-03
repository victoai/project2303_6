package com.batis.paging;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pageServiceImp implements pageServiceI{
	
	@Autowired
	pageRespositoryI dao;
	
	@Override
	public int getTotalCnt() {
		return dao.totalCnt();
	}
	
	@Override
	public List<BoardDTO> getListPage(int curpage, int pageSize) {
		return dao.getPage(curpage, pageSize);
	}
}
