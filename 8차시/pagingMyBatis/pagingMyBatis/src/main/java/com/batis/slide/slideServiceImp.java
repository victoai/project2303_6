package com.batis.slide;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class slideServiceImp implements slideServiceI{
	
	@Autowired
	slideRespositoryI dao;
	
	@Override
	public int getTotalCnt() {
		return dao.totalCnt();
	}
	
	@Override
	public List<SlideDTO> getListSlide(int curpage, int pageSize) {
		return dao.getSlide(curpage, pageSize);
	}
}
