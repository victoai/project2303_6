package com.batis.paging;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class pageRespositoryImp implements pageRespositoryI{
	@Autowired
	private SqlSession session;
	private static String namespace = "com.batis.boardMapper"; //mapper 이름
	
	
	@Override
	public int totalCnt() {
		return session.selectOne( namespace + ".getTotalCnt");
	}
	@Override
	public List<BoardDTO> getPage(int curpage, int pageSize) {
		int startPage, endPage;
		startPage = ((curpage-1)*pageSize)+1; //시작 인덱스
		endPage = curpage*pageSize; //끝 인덱스
		
		Map<String, Object> parameters = new HashMap<String, Object>();
	    parameters.put("startPage", startPage);
	    parameters.put("endPage", endPage);

	    System.out.println(curpage+ ", "+ pageSize);
	    System.out.println(parameters);
		return session.selectList(namespace+ ".getListPage", parameters); //mapper에 넘겨줌
	}
	
}