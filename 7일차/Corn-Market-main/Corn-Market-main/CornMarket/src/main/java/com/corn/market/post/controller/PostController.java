package com.corn.market.post.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.corn.market.post.domain.Criteria;
import com.corn.market.post.domain.Page;
import com.corn.market.common.fileUpload.FileUploadService;
import com.corn.market.post.domain.PostList;
import com.corn.market.post.domain.PostVO;
import com.corn.market.post.service.PostService;

@Controller
public class PostController {

	@Autowired
	PostService postService;
	@Autowired
	private FileUploadService fileService;
	
	//판매글 검색
	@GetMapping("/post/search")
	public String getSearchResult(String keyword, Model model, Criteria cri) 
			throws Exception {
        model.addAttribute("list", postService.getSearchResult(cri, keyword));            
        int total = postService.getSearchTotal(keyword);
        Page pageMake = new Page(cri, total);
        //System.out.println(cri.getPageNum());
        model.addAttribute("pageMaker", pageMake);  
        model.addAttribute("keyword",keyword);
        return "post/postlookup_search";
    } 
	
	/* 판매글 전체 조회 페이지(페이징 적용) */
    @GetMapping("/post")
    public String boardListGET(Model model, Criteria cri) 
    		throws Exception {
        model.addAttribute( "list", postService.getListPaging(cri) );            
        int total = postService.getTotal();
        Page pageMake = new Page(cri, total);
        model.addAttribute("pageMaker", pageMake);  
        return "post/postlookup";
    } 
    
    // 판매글 카테고리별 조회 - 메인 (페이징)
    @GetMapping("/post/category")
    public String postCategoryListMain(String id, Model model, Criteria cri) 
    		throws Exception {
    	ArrayList<PostList> list = (ArrayList<PostList>) postService.getCategoryList(cri, id);
    	model.addAttribute("list",list);
    	Page pageMake = new Page(cri, postService.getCategoryTotal(id));
    	model.addAttribute("pageMaker", pageMake);
    	return "post/postlookup_category";
	}
    
	// 판매글 카테고리별 조회 (ajax)
	@ResponseBody
	@PostMapping("/post/category/{category_id}")
	public ArrayList<PostList> postCategoryList(@PathVariable("category_id") String category_id) 
			throws Exception {
		ArrayList<PostList> list = (ArrayList<PostList>) postService.getPostCategoryList(category_id);
		return list;
	} 
	// 판매글 지역별 조회 (ajax)
	@ResponseBody
	@GetMapping("/post/town/{town_code}")
	public ArrayList<PostList> postTownList(@PathVariable("town_code") String town_code) 
			throws Exception {
		ArrayList<PostList> list = (ArrayList<PostList>) postService.getPostTownList(town_code);
		return list;
	}
	// 판매글 상세 조회 (기본)
	@GetMapping("/post/{post_id}")
	public String getView(@PathVariable String post_id, 
			Model model, HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		PostList post = postService.getOnePost(post_id);
		model.addAttribute("post", post);
		//model.addAttribute(id);
		return "post/postotherinfo";
	}
	
	// 판매글 등록 페이지 이동
	@GetMapping("/post/enroll")
	public String mainPageGet() {
		return "post/postregister";
	} 
	// 판매글 등록 + 이미지 등록
	@PostMapping("/post/enroll")
	public String write(PostVO post, HttpSession session, 
			MultipartHttpServletRequest files, HttpServletRequest request)
			throws Exception {
		String id = (String) session.getAttribute("id");
		post.setUser_id(id);
		post.setContent(postService.replaceLine(post.getContent()));
		String url = fileService.multiFileUpload(files, request);  //파일 업로드
		post.setPost_img(url);  //사진등록
		//System.out.println("이미지 등록: "+post.getPost_img());
		postService.registerPost(post); //등록
		return "redirect:/post";
	}
	
	// 판매글 수정 페이지 이동 
	@GetMapping("/post/{post_id}/modify")
	public String boardModifyGET(@PathVariable String post_id, Model model, 
			HttpSession session) throws Exception {
		String id = (String) session.getAttribute("id");
		PostList post = postService.getOnePost(post_id);
		model.addAttribute("post", post);
		model.addAttribute(id);
		return "post/postmodify";
	}
	// 판매글 수정
	@PostMapping("/post/{post_id}/modify")
	public String postModify(@PathVariable String post_id, PostVO post, 
			HttpSession session, MultipartHttpServletRequest files, HttpServletRequest request) 
					throws Exception {
		String id = (String) session.getAttribute("id");
		post.setUser_id(id);
		post.setContent(postService.replaceLine(post.getContent()));
		String url = fileService.multiFileUpload(files, request);  //파일 업로드
		post.setPost_img(url);  //사진등록
		//System.out.println("이미지 수정: "+post.getPost_img());
		postService.modifyPost(post);
		return "redirect:/post/"+post_id;
	}
	
	// 판매글 삭제
	@GetMapping("/post/{post_id}/delete")
	public String deletePost(@PathVariable String post_id) throws Exception {
		postService.deletePost(post_id);
		return "redirect:/post";
	}

}