package com.visitKorea.content;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor // 기본생성자
@AllArgsConstructor // 매개변수 있는 생성자
public class ContentDTO {
	int num;
	String contentid; // 게시물 ID
    int contenttypeid; // 게시물 유형 (장소/축제/공연 등)
    String cat1code; // 대분류
    String cat2code; // 중분류
    String cat3code; // 소분류
    String title; // 게시물 제목
    int sidoCode; // 시/도 코드
    String nickName; // 시도별칭
    String sigunguCode;   // 시/군/구 코드
    String sigunguname; // 시군구명
    String address;  // 주소
    String tel;  // 전화번호
    String createdtime; // 생성시간
    String modifiedtime; // 수정시간
    String mapx;  // 지리적 위도
    String mapy;  // 지리적 경도
    String mlevel; // 지도레벨
    String firstimage;
    String firstimage2;
    int view_num;
    
	public ContentDTO(String contentid, int contenttypeid, String cat1code, String cat2code, String cat3code,
			String title, int sidoCode, String sigunguCode, String address, String tel, String createdtime,
			String modifiedtime, String mapx, String mapy, String mlevel, String firstimage, String firstimage2) {
		super();
		this.contentid = contentid;
		this.contenttypeid = contenttypeid;
		this.cat1code = cat1code;
		this.cat2code = cat2code;
		this.cat3code = cat3code;
		this.title = title;
		this.sidoCode = sidoCode;
		this.sigunguCode = sigunguCode;
		this.address = address;
		this.tel = tel;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.mapx = mapx;
		this.mapy = mapy;
		this.mlevel = mlevel;
		this.firstimage = firstimage;
		this.firstimage2 = firstimage2;
	}

	public ContentDTO(int num, String contentid, int contenttypeid, String title, int sidoCode, String nickName,
			String sigunguCode, String sigunguname, String createdtime, String modifiedtime, String mapx, String mapy,
			String firstimage) {
		super();
		this.num = num;
		this.contentid = contentid;
		this.contenttypeid = contenttypeid;
		this.title = title;
		this.sidoCode = sidoCode;
		this.nickName = nickName;
		this.sigunguCode = sigunguCode;
		this.sigunguname = sigunguname;
		this.createdtime = createdtime;
		this.modifiedtime = modifiedtime;
		this.mapx = mapx;
		this.mapy = mapy;
		this.firstimage = firstimage;
	}
	
	
    
    
	
}
