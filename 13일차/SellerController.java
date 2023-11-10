package com.acorn.baemin.seller.controller;

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

import com.acorn.baemin.domain.MenuDTO;
import com.acorn.baemin.domain.SellerDTO;
import com.acorn.baemin.domain.StoreDTO;
import com.acorn.baemin.seller.service.SellerService;

@Controller
public class SellerController {
	
	@Autowired
	private SellerService sc;
	
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
	@GetMapping("/store_manage")
	public String readstNs(@RequestParam("storeCode") int storeCode, @RequestParam("sellerCode") int sellerCode, Model model) {
		
		System.out.println("storeCode @service: " + storeCode);
		System.out.println("sellerCode @service : " + sellerCode);
		StoreDTO readStoreNmRtRcMp = sc.selectStoreNmRtRcMp(storeCode);
		StoreDTO readtStoreDTA = sc.selectStoreDTA(storeCode);
		SellerDTO readSellerNmRn = sc.selectSellerNmRn(sellerCode);
		List<MenuDTO> readMenuInfo = sc.selectAllMenuInfo();
		System.out.println(readMenuInfo);
		
		model.addAttribute("readStoreNmRtRcMp", readStoreNmRtRcMp);
		model.addAttribute("readtStoreDTA", readtStoreDTA);
		model.addAttribute("readSellerNmRn", readSellerNmRn);
		model.addAttribute("readMenuInfo", readMenuInfo);
		
		return "seller/store_manage";
	}
	
	@PostMapping("/store_manage")
	public String insertMenu(Integer menuCode, String menuName, Integer menuPrice, MultipartFile menuImage, String menuClassification, String menuContent, Integer menuStatus) throws IllegalStateException, IOException {
		System.out.println("ㅇㄹㅇㄹㅇㄹㅇㄹ" + menuName) ;
		
		try {
			if( !menuImage.isEmpty()) {
				//이미지 업로드 파트
				String fileName  = menuImage.getOriginalFilename();
				String menuRealImage = fileDir +menuName+fileName ; // c:\\test\\upload\\고양이.jpg
				menuImage.transferTo(new File(menuRealImage)); 
				
				//db에 넣기
				String menuImageName = menuName+fileName;
				MenuDTO menu = new MenuDTO(menuCode, menuName, menuPrice, menuImageName, menuContent, menuClassification, menuStatus);
				sc.insertMenu(menu);
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
