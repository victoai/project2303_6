package com.corn.market.post.domain;

public class PostList {
	
	private String post_id; // 판매글id
	private String user_id; // 사용자id  
	private String nickname; // 닉네임
	private String category_id; //카테고리id
	private String category_name; //카테고리id
	private String town_code; //지역 코드
	private String town_name; //지역 이름
	private String title; // 제목
	private String price; // 가격
	private String content; //내용
	private String created; // 등록일
	private String updated; // 수정일
	private String post_status; // 상태 (기본 '판매중')
	private String post_img; // 이미지주소 (기본 '/resources/images/post/post_img_thumbnail.png')
	
	public PostList() {}

	public PostList(String post_id, String user_id, String nickname, String category_id, String category_name,
			String town_code, String town_name, String title, String price, String content, String created,
			String updated, String post_status, String post_img) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.nickname = nickname;
		this.category_id = category_id;
		this.category_name = category_name;
		this.town_code = town_code;
		this.town_name = town_name;
		this.title = title;
		this.price = price;
		this.content = content;
		this.created = created;
		this.updated = updated;
		this.post_status = post_status;
		this.post_img = post_img;
	}

	public String getPost_id() {
		return post_id;
	}

	public void setPost_id(String post_id) {
		this.post_id = post_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getCategory_id() {
		return category_id;
	}

	public void setCategory_id(String category_id) {
		this.category_id = category_id;
	}
	
	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getTown_code() {
		return town_code;
	}

	public void setTown_code(String town_code) {
		this.town_code = town_code;
	}

	public String getTown_name() {
		return town_name;
	}

	public void setTown_name(String town_name) {
		this.town_name = town_name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public String getPost_status() {
		return post_status;
	}

	public void setPost_status(String post_status) {
		this.post_status = post_status;
	}

	public String getPost_img() {
		return post_img;
	}

	public void setPost_img(String post_img) {
		this.post_img = post_img;
	}

	@Override
	public String toString() {
		return "PostList [post_id=" + post_id + ", user_id=" + user_id + ", nickname=" + nickname + ", category_id="
				+ category_id + ", category_name=" + category_name + ", town_code=" + town_code + ", town_name="
				+ town_name + ", title=" + title + ", price=" + price + ", content=" + content + ", created=" + created
				+ ", updated=" + updated + ", post_status=" + post_status + ", post_img=" + post_img + "]";
	}

}
