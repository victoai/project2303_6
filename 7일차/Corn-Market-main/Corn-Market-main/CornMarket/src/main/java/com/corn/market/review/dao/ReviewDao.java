package com.corn.market.review.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corn.market.review.domain.ReviewReg;

@Component
public class ReviewDao {

	@Autowired
	private DataSource dataSource;
	
	//리뷰 등록
	public void insertReview(ReviewReg review) {
		String sql = "INSERT INTO end_deal_tbl22 VALUES(deal_sq.NEXTVAL,?,DEFAULT, ?, ?)";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, review.getRoom_id());
			pst.setString(2, review.getRate());
			pst.setString(3, review.getReview());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
	}
	//판매글의 거래상태를 거래완료로 수정
	public void updatePostStatus(String post_id) {
		String sql = "UPDATE post_tbl22 "
				+ "SET post_status = '거래완료' "
				+ "WHERE post_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, post_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
	}
	//판매글의 거래상태 가져오기
	public String selectPostStatus(String post_id) {
		String post_status = "";
		String sql = "SELECT post_status FROM post_tbl22 WHERE post_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, post_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				post_status = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
		return post_status;
	}
	
	private void close(AutoCloseable...acs) {
		try {
			for(AutoCloseable ac : acs) {
				if(ac != null) ac.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//
	
}
