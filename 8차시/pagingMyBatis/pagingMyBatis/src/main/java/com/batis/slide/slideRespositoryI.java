package com.batis.slide;

import java.util.List;

public interface slideRespositoryI {
	public int totalCnt();
	public List<SlideDTO> getSlide(int curpage, int pageSize);
}
