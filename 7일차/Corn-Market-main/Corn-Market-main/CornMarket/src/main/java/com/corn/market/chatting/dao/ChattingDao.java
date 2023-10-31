package com.corn.market.chatting.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corn.market.chatting.domain.ChatUserInfo;
import com.corn.market.chatting.domain.ChattingContent;
import com.corn.market.chatting.domain.ChattingContentList;
import com.corn.market.chatting.domain.ChattingInfo;
import com.corn.market.chatting.domain.ChattingRoom;
import com.corn.market.chatting.domain.ChattingRoomDeleteInfo;
import com.corn.market.chatting.domain.ChattingRoomInfo;

@Component
public class ChattingDao {
	
	@Autowired
	private DataSource dataSource;

	//채팅방 새로 등록
	public void insertChattingRoom(ChattingRoom chattingRoom) {
		String sql = "INSERT INTO chatting_room_tbl22 "
				+ "VALUES(?,?,?,?,DEFAULT,DEFAULT)";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, chattingRoom.getRoom_id());
			pst.setString(2, chattingRoom.getPost_id());
			pst.setString(3, chattingRoom.getSeller_id());
			pst.setString(4, chattingRoom.getBuyer_id());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
	}
	//판매글 아이디로 판매자 아이디 가져오기
	public String selectSellerId(String post_id) {
		String user_id = "";
		String sql = "SELECT user_id "
				+ "FROM post_tbl22 "
				+ "WHERE post_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, post_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				user_id = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return user_id;
	}
	
	//채팅 내용 등록
	public void insertChattingContent (ChattingContent chattingContent) {
		String sql = "INSERT INTO chatting_content_tbl22 "
				+ "VALUES(content_sq.NEXTVAL,?,?,?,DEFAULT,DEFAULT) ";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, chattingContent.getRoom_id());
			pst.setString(2, chattingContent.getSender_id());
			pst.setString(3, chattingContent.getChat_content());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
	}
	
	//채팅방 목록 조회
	public ArrayList<ChattingRoomInfo> selectChattingRoom(String user_id) {
		ArrayList<ChattingRoomInfo> list = new ArrayList<>();
		String sql = "SELECT r.room_id, DECODE(? ,r.seller_id,r.buyer_id,r.buyer_id,r.seller_id) id, u.profile_img, u.nickname, c.chat_content, TO_CHAR(c.send_date,'YYYY\"년 \"FMMM\"월 \"DD\"일\"'), t.town_name "
				+ "FROM chatting_room_tbl22 r "
				+ "JOIN user_tbl22 u "
				+ "ON DECODE(?,r.seller_id,r.buyer_id,r.buyer_id,r.seller_id) = u.user_id "
				+ "JOIN chatting_content_tbl22 c "
				+ "ON r.room_id = c.room_id "
				+ "JOIN post_tbl22 p "
				+ "ON r.post_id = p.post_id "
				+ "JOIN town_tbl22 t "
				+ "ON p.town_code = t.town_code "
				+ "WHERE r.chatting_status IN(0,DECODE(?, seller_id, 2, buyer_id, 1)) AND(r.seller_id = ? OR r.buyer_id = ?) AND c.send_date IN(SELECT MAX(send_date) FROM chatting_content_tbl22 GROUP BY room_id) "
				+ "ORDER BY c.send_date DESC";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, user_id);
			pst.setString(3, user_id);
			pst.setString(4, user_id);
			pst.setString(5, user_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				String room_id = rs.getString(1);
				String other_user_id = rs.getString(2);
				String other_profile_img = rs.getString(3);
				String other_nickname = rs.getString(4);
				String last_chat_content = rs.getString(5);
				String last_send_date = rs.getString(6);
				String post_town_name = rs.getString(7);
				list.add(new ChattingRoomInfo(room_id,other_user_id, other_profile_img, other_nickname, last_chat_content, last_send_date, post_town_name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return list;
	}
	
	//채팅내용 조회
	public ArrayList<ChattingContentList> selectChattingContent(String room_id) {
		ArrayList<ChattingContentList> list = new ArrayList<>();
		String sql = "SELECT sender_id, TO_CHAR(send_date,'YYYY\"년 \"FMMM\"월 \"DD\"일\"'), chat_content, TO_CHAR(send_date, 'HH24:MI') "
				+ "FROM chatting_content_tbl22 "
				+ "WHERE room_id = ?  ORDER BY send_date";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, room_id);
			rs = pst.executeQuery();
			while(rs.next()) {
				String sender_id = rs.getString(1);
				String date = rs.getString(2);
				String chat_content = rs.getString(3);
				String time = rs.getString(4);
				list.add(new ChattingContentList(sender_id, date, chat_content, time));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return list;
	}
	
	//채팅창 정보 조회
	public ChattingInfo selectChattingInfo(String room_id, String user_id) {
		ChattingInfo chattingInfo = null;
		String sql = "SELECT r.seller_id, r.post_id, p.title, p.post_img, u.profile_img, u.nickname, TRIM(TO_CHAR(p.price,'99,999,999')) "
				+ "FROM chatting_room_tbl22 r "
				+ "JOIN post_tbl22 p "
				+ "ON r.post_id = p.post_id "
				+ "JOIN user_tbl22 u "
				+ "ON DECODE(?,r.seller_id,r.buyer_id,r.buyer_id,r.seller_id) = u.user_id "
				+ "WHERE room_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			pst.setString(2, room_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String seller_id = rs.getString(1);
				String post_id = rs.getString(2);
				String post_title = rs.getString(3);
				String post_img = rs.getString(4);
				String other_profile_img = rs.getString(5);
				String other_nickname = rs.getString(6);
				String post_price = rs.getString(7);
				chattingInfo = new ChattingInfo(seller_id, room_id, post_id, post_title, post_img, other_profile_img, other_nickname, post_price, selectChattingContent(room_id));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return chattingInfo;
	}
	
	//판매글id와 구매자id(세션)로 채팅방 확인 (채팅방 생성시)
	public String checkChattingRoom(String post_id, String user_id) {
		String check = "";
		String sql = "SELECT TO_CHAR(NVL(MAX(room_id), 0)) "
				+ "FROM chatting_room_tbl22 "
				+ "WHERE post_id = ? AND buyer_id = ?  ";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, post_id);
			pst.setString(2, user_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				check = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return check; //조회 결과 없으면 0 있으면 방id 반환
	}
	
	//채팅방 삭제
	public void updateChattingStatus (ChattingRoomDeleteInfo deleteInfo) {
		String sql = "UPDATE chatting_room_tbl22 "
				+ "SET chatting_status = DECODE(?, seller_id, 1, buyer_id, 2) "
				+ "WHERE room_id = ? ";
		Connection conn = null;
		PreparedStatement pst = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, deleteInfo.getUser_id());
			pst.setString(2, deleteInfo.getRoom_id());
			pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pst,conn);
		}
	}
	
	//사용자 프로필 이미지, 닉네임 조회
	public ChatUserInfo selectUserInfo(String user_id) {
		ChatUserInfo chatUserInfo = null;
		String sql = "SELECT nickname, profile_img "
				+ "FROM user_tbl22 "
				+ "WHERE user_id = ?";
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = dataSource.getConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, user_id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String user_name = rs.getString(1);
				String profile_img  = rs.getString(2);
				chatUserInfo = new ChatUserInfo(user_name, profile_img);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs,pst,conn);
		}
		return chatUserInfo;
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
