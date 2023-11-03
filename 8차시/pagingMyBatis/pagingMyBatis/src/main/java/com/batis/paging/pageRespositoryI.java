package com.batis.paging;

import java.util.List;

public interface pageRespositoryI {
	
	public int totalCnt();
	public List<BoardDTO> getPage(int curpage, int pageSize);
}
