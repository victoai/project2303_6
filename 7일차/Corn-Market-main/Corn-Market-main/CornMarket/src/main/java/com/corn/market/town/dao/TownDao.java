package com.corn.market.town.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Component;

import com.corn.market.town.domain.TownCertification;
import com.corn.market.town.domain.TownInfo;

@Component
public class TownDao {

	@Autowired
	private DataSource dataSource;
	
	//구 이름으로 town code 가져오기
	public String selectTownCode(TownInfo townInfo) {
		String town_code = "";
		String sql = "SELECT town_code "
				+ "FROM town_tbl22 "
				+ "WHERE town_name = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, townInfo.getTown_name().split(" ")[1]);
			rs = pst.executeQuery();
			if(rs.next()) {
				town_code = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return town_code;
	}
	
	//동네인증 이전 기록 삭제
	// 트랜잭션 사용
	public void deletePrevAddress(String user_id) throws SQLException {
		String sql = "DELETE FROM address_tbl22 WHERE user_id = ?";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			System.out.println("db삭제 연결 "+conn);
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			//예외 발생, 메소드 호출할때 예외 처리
			throw e; 
		} finally {
			close(pst);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
	
	//동네인증 등록
	// 트랜잭션 사용
	public void insertNewAddress(TownCertification town) throws SQLException {
		String sql = "INSERT INTO address_tbl22 VALUES(addr_sq.NEXTVAL, ?, ?, ?, ?, DEFAULT)";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = DataSourceUtils.getConnection(dataSource);
			System.out.println("db등록 연결 "+conn);
			pst = conn.prepareStatement(sql);
			pst.setString(1, town.getUser_id());
			pst.setString(2, town.getTown_code());
			pst.setString(3, town.getLatitude());
			pst.setString(4, town.getLongitude());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			//예외 발생, 메소드 호출할때 예외 처리
			throw e; 
		} finally {
			close(pst);
			DataSourceUtils.releaseConnection(conn, dataSource);
		}
	}
	
	//동네인증 아이디당 이전 인증기록 개수 체크
	public int selectCountAddress(String user_id) {
		int count = 0;
		String sql = "SELECT COUNT(*) "
				+ "FROM address_tbl22 "
				+ "WHERE user_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return count;
	}
	
	private void close(AutoCloseable...acs) {
		for(AutoCloseable ac : acs) {
			try {
				if(ac!=null) ac.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
