package com.corn.market.account.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corn.market.account.domain.AccountId;
import com.corn.market.account.domain.AccountPw;
import com.corn.market.account.domain.SearchIdMail;
import com.corn.market.account.domain.SearchIdPhone;
import com.corn.market.account.domain.SearchPw;

@Component
public class AccountDao {

	@Autowired
	private DataSource dataSource;
	
	//아이디 찾기 - 휴대폰번호로
	public AccountId selectUserId1(SearchIdPhone phone) {
		String user_id = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM user_tbl22 WHERE user_name = ? AND phone = ?";
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, phone.getUser_name());
			pst.setString(2, phone.getPhone());
			rs = pst.executeQuery();
			if(rs.next()) {
				user_id = rs.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		AccountId id = new AccountId(user_id);
		return id;
	}
	
	//아이디 찾기 - 이메일로
	public AccountId selectUserId2(SearchIdMail mail) {
		String user_id = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT user_id FROM user_tbl22 WHERE user_name = ? AND email = ?";
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, mail.getUser_name());
			pst.setString(2, mail.getEmail_id()+"@"+mail.getEmail_domain());
			rs = pst.executeQuery();
			if(rs.next()) {
				user_id = rs.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		AccountId id = new AccountId(user_id);
		return id;
	}
	
	//이메일 찾기
	public int selectEmail(String email) {
		int check = 0;
		String check_ = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT email FROM user_tbl22 WHERE email = ?";
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, email);
			rs = pst.executeQuery();
			if(rs.next()) {
				check_ = rs.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		check = (check_==""||check_==null) ? 0 : 1; //이메일이 회원정보에 없으면 0, 있으면 1
		return check;
	}
	
	//비밀번호 찾기
	public AccountPw selectUserPw(SearchPw searchPw) {
		String user_pw = "";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "SELECT user_pw FROM user_tbl22 WHERE user_id = ? AND user_name = ? AND email = ?";
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, searchPw.getUser_id());
			pst.setString(2, searchPw.getUser_name());
			pst.setString(3, searchPw.getEmail_id()+"@"+searchPw.getEmail_domain());
			rs = pst.executeQuery();
			if(rs.next()) {
				user_pw = rs.getString(1); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		AccountPw pw = new AccountPw(user_pw);
		return pw;
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
