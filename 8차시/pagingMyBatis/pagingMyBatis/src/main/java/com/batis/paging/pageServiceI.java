package com.batis.paging;

import java.util.List;

public interface pageServiceI {
	public int  getTotalCnt();
	public List<BoardDTO> getListPage(int curpage, int pageSize);
}
