package com.acorn.form;

import java.io.File;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

 
@Controller
public class SellerController {
	
 
	
	String fileDir ="c:\\test\\upload\\";
	
//	@GetMapping("/menu_manage")
// public String readmNst(@RequestParam("storeCode") int storeCode, Model model) {
//	 	
//		System.out.println("storeCode @service : " + storeCode);
//		List<MenuDTO> readMenuInfo = sc.selectMenuInfo();
//		System.out.println(readMenuInfo);
//		StoreDTO readStoreNmRtRcMp = sc.selectStoreNmRtRcMp(storeCode);
//		
//		/*
//		Map<String , Object> map = new HashMap<>();
//		map.put("list", readtMenuInfo);
//		map.put("storeInfo", readStoreNmRtRcMp);
//		*/
//		model.addAttribute("readStoreNmRtRcMp", readStoreNmRtRcMp);
//		model.addAttribute("readMenuInfo", readMenuInfo);
//		
//		return "seller/store_manage";
//	}
//	
	 
	
	@PostMapping("/store_manage")
	public String insertMenu(Integer menuCode, String menuName, Integer menuPrice, MultipartFile menuImage, String menuClassification, String menuContent, Integer menuStatus) throws IllegalStateException, IOException {
		System.out.println("ㅇㄹㅇㄹㅇㄹㅇㄹ" + menuName) ;
		
		try {
			if( !menuImage.isEmpty()) {
				//이미지 업로드 파트
				String fileName  = menuImage.getOriginalFilename();
				String menuRealImage = fileDir +menuName+fileName ; // c:\\test\\upload\\고양이.jpg
				menuImage.transferTo(new File(menuRealImage)); 
				
				 
			 
			}
			
		} catch (Exception e) {
				System.out.println("값이 null이다");
		}
		return  "seller/store_manage";
}

//	@ResponseBody
//	@RequestMapping(value="/menu_list" , method=RequestMethod.GET)
//	public List<MenuDTO> selectAllMenuList() {
//		List<MenuDTO> menu = sc.selectAllMenuInfo();
//		return menu;
//	}
	
	
	

}
