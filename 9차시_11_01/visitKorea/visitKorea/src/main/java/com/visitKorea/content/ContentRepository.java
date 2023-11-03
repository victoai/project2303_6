package com.visitKorea.content;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.visitKorea.paging.PageHandler;


@Component
public class ContentRepository {
	
	@Autowired
	DataSource ds;
	
	// content 삽입
	public void insertAll(ArrayList<ContentDTO> list) {
		Connection con = null;
		PreparedStatement pst = null;
		
		String sql = "insert into contentInfoTbl values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			
			for(int i =0; i<list.size(); i++) {
				ContentDTO item = list.get(i);
				pst.setString(1, item.getContentid());
				pst.setInt(2, item.getContenttypeid());
				pst.setString(3, item.getCat1code());
				pst.setString(4, item.getCat2code());
				pst.setString(5, item.getCat3code());
				pst.setString(6, item.getTitle());
				pst.setInt(7, item.getSidoCode());
				pst.setString(8, item.getSigunguCode());
				pst.setString(9, item.getAddress());
				pst.setString(10, item.getTel());
				pst.setString(11, item.getCreatedtime());
				pst.setString(12, item.getModifiedtime());
				pst.setString(13, item.getMapx());
				pst.setString(14, item.getMapy());
				pst.setString(15, item.getMlevel());
				pst.setString(16, item.getFirstimage());
				pst.setString(17, item.getFirstimage2());
				
				pst.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		close(pst,con);
	}
	
	// content 전체 갯수
	public Integer totalCnt() {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		int result = 0;
		String sql = "select count(*) from contentInfoTbl";
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				result = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		close(rs,pst,con);
		return result;
	}
	
	
	
	

	// content 10개씩 표출
	public ArrayList<ContentDTO> selectAll(int startList, int endList) {
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		
		String sql 	= "select num, ct.contentid, ct.contenttypeid, ct.title, sd.sidocode, sd.nickname, sg.sigungucode, sg.name, ct.createdtime, ct.modifiedtime, ct.mapx, ct.mapy, ct.firstimage  from  "
					+ "(select rownum as num, contentid, contenttypeid, title, sidoCode,  LPAD(sidoCode, 2, '0') || LPAD(sigunguCode, 3, '0') AS sigungucode, createdtime, modifiedtime, mapx, mapy,firstimage from  "
					+ "(select * from contentInfoTbl where contenttypeid = '12' order by createdtime desc)) ct  "
					+ "join SidoTbl sd on sd.sidocode = ct.sidocode "
					+ "join SigunguTbl sg on sg.sigungucode = ct.sigungucode "
					+ "where num between "+ startList +" and " + endList;
					//+ "where num between 1 and 10";
		
		ArrayList<ContentDTO> contentList = new ArrayList<>();
		
		try {
			con = ds.getConnection();
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				int num = rs.getInt(1);
				String contentid = rs.getString(2); // 게시물 ID
			    int contenttypeid = rs.getInt(3) ; // 게시물 유형 (장소/축제/공연 등)
			    String title = rs.getString(4); // 게시물 제목
			    int sidoCode = rs.getInt(5); // 시/도 코드
			    String nickname = rs.getString(6);   // 시도 별칭
			    String sigunguCode = rs.getString(7);   // 시/군/구 코드
			    String sigunguname = rs.getString(8);   // 시/군/구 코드
			    String createdtime = rs.getString(9); // 생성시간
			    String modifiedtime = rs.getString(10); // 수정시간
			    String mapx = rs.getString(11);  // 지리적 위도
			    String mapy = rs.getString(12);  // 지리적 경도
			    String firstimage = rs.getString(13);
			    ContentDTO content = new ContentDTO(num,contentid,contenttypeid,title,sidoCode,nickname,sigunguCode,sigunguname,createdtime,modifiedtime,mapx,mapy,firstimage);
			    contentList.add(content);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		close(rs,pst,con);
		return contentList;
	}
	
	
	
	
	
	
	// 자원반납
	public void close(AutoCloseable ...a) {
		for(AutoCloseable item : a) {
			try {
				item.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
