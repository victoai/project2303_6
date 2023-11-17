package com.acorn.form;

import java.io.File;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SimpleUpload2 {

	String fileDir ="c:\\test\\upload\\"  ; // 물리적인 폴더를 만들어야 함 
	                  
	   
	 
	@ResponseBody
	@PostMapping("/ftest")
	public String upload(   MultipartFile file , String username  ) throws IllegalStateException, IOException {
		
		System.out.println( "ftest" + username);
		if( !file.isEmpty()) {
			String fileRealName  = file.getOriginalFilename();
			String fullPath = fileDir + fileRealName ; // c:\\test\\upload\\고양이.jpg
			file.transferTo(new File(fullPath));
			 
		}		
		   
		return "success";
	}
	
 
	
}
