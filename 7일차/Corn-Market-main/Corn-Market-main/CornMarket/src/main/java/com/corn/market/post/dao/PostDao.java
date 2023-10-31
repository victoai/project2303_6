package com.corn.market.post.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.corn.market.post.domain.Criteria;
import com.corn.market.post.domain.PostList;
import com.corn.market.post.domain.PostVO;

@Repository
public class PostDao {

	@Autowired
	private SqlSession session;
	private static String namespace = "com.corn.market.PostMapper.";

	// 판매글 전체 조회 - 최신글 (기본)
	public List<PostList> selectAll() throws Exception {
		return session.selectList(namespace+"selectAll");
	} 
	// 판매글 카테고리별 조회
	public List<PostList> selectCategory(String category_id) throws Exception {
		return session.selectList(namespace+"selectCategory", category_id);
	} 
	// 판매글 지역별 조회
	public List<PostList> selectTown(String town_code) throws Exception {
		return session.selectList(namespace+"selectTown", town_code);
	} 
	
	// 판매글 상세 조회
	public PostList selectOne(String post_id) throws Exception {
		return session.selectOne(namespace + "selectOne", post_id);
	}
	
	// 판매글 등록
	public void enroll(PostVO post) throws Exception {
		session.insert(namespace+"enroll", post);
	}

	// 판매글 수정
	public void update(PostVO post) throws Exception{
		session.update(namespace+"modify", post);
	}

	// 판매글 상태 삭제로 변경
	public void delete(String post_id) throws Exception{
		session.update(namespace+"delete", post_id); 
	}


	//판매글 전체 조회 - 최신글 (기본) + 페이징 
	public List<PostList> getListPaging(Criteria cri) throws Exception {
		return session.selectList(namespace + "getListPaging", cri);
	}

	// 판매글 카테고리별 조회 + 페이징 
	public List<PostList> selectCategoryList(Map<String, Object> cateMap) throws Exception {
		return session.selectList(namespace+"selectCategoryList", cateMap);
	} 

	//판매글 총 갯수 
	public int getTotal() throws Exception {
		return session.selectOne(namespace + "getTotal"); 
	}
	//판매글 카테고리별 총 갯수 
	public int getCategoryTotal(String category_id) throws Exception {
		return session.selectOne(namespace + "getCategoryTotal", category_id); 
	}
	
	//판매글 검색 (페이징)
	public List<PostList> selectSearchResult(Map<String, Object> searchMap) {
		return session.selectList(namespace + "selectSearchResult", searchMap);
	}
	//판매글 검색 총 갯수 
	public int getSearchTotal(String keyword) throws Exception {
		return session.selectOne(namespace + "getSearchTotal", keyword); 
	}

}
 
  
