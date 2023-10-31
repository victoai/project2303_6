package com.corn.market.common.fileUpload;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service
public class FileUploadService {

	//파일 한개
	public String oneFileUpload(MultipartFile file, HttpServletRequest request) {
		if(file==null) return "/resources/images/profile/profile_img_default.png";
		//파일명 얻기
		String fileName = file.getOriginalFilename();
		//파일 확장자 구하기 ".png"
		String fileExtension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		//파일 저장 프로젝트 내 경로
		String path = "/resources/images/profile/";
		//파일 저장 실제 위치 경로
		String folderPath = request.getSession().getServletContext().getRealPath(path); 
		//System.out.println("파일실제위치: "+folderPath);
		//파일 이름 변경 (profile_날짜+시간+랜덤숫자)
		String random = Integer.toString((int)(Math.random()*99+1));
		String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
		String fileRename = "profile_"+date+time+random; //전체 파일 이름
		
		//파일 저장 (파일경로 + 파일이름 + 확장자)
		File saveFile = new File(folderPath+fileRename+fileExtension);
		try {
			//실제 파일 저장메서드(filewriter 작업을 손쉽게 한방에 처리해준다)
			file.transferTo(saveFile); 
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//디비에 저장할 url
		String url = path+fileRename+fileExtension;
		
		return url; 
	}
	
	//파일 여러개
	public String multiFileUpload(MultipartHttpServletRequest files, HttpServletRequest request) {
		if(files==null) return "/resources/images/post/post_img_thumbnail.png";
		//파일 저장 프로젝트 내 경로
		String path = "/resources/images/post/";
		//파일 저장 실제 위치 경로
		String folderPath = request.getSession().getServletContext().getRealPath(path); 
		//System.out.println("파일실제위치: "+folderPath);
		
		String random = ""; 
		String date = ""; 
		String time = ""; 
		String fileRename = ""; 
		
		//리스트에 파일 담기
		List<MultipartFile> list = files.getFiles("files");
		//리스트 size 만큼의 String 배열 만들기 (DB 등록용)
		String[] urls = new String[list.size()];
		//파일 하나씩 업로드 (반복)
		for(int i=0; i<list.size(); i++) {
			String fileName = list.get(i).getOriginalFilename();
			//System.out.println("파일"+(i+1)+": "+fileName);
			//파일 확장자 구하기 ".png"
			
			if(fileName.length() < 5 || fileName.lastIndexOf(".") < 1) return "/resources/images/post/post_img_thumbnail.png";
			String fileExtension = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			
			//파일 이름 변경 (profile_날짜+시간+랜덤숫자)
			random = Integer.toString((int)(Math.random()*99+1));
			date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));
			fileRename = "profile_"+date+time+random; //전체 파일 이름
			
			//파일 저장 (파일경로 + 파일이름 + 확장자)
			File saveFile = new File(folderPath+fileRename+fileExtension);
			try {
				list.get(i).transferTo(saveFile); //실제 파일 저장메서드
				urls[i] = path+fileRename+fileExtension; //디비에 저장할 url배열
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}//for
		
		//url 콤마로 합치기
		String url = "";
		if(list.size() > 1) url = String.join(",", urls); //파일 여러개일때
		else if(list.size() == 1) url = urls[0]; //파일 한개일때
		//System.out.println(url);
				
		return url; //디비에 저장할 url
	}
}
