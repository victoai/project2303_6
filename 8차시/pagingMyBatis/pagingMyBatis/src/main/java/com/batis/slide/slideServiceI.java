package com.batis.slide;

import java.util.List;

public interface slideServiceI {
	public int  getTotalCnt();
	public List<SlideDTO> getListSlide(int curpage, int pageSize);
}
