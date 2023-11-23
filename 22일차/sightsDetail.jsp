<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&family=Noto+Sans:wght@100&family=Orbit&display=swap" rel="stylesheet">

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=aad2fba6dae0bad9267b3e158cc19bd4"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="${path}/resources/javascript/common.js"></script>

<style>

    *{
        margin: 0;
        padding: 0;
        border: 0;
        background-color: transparent;
        list-style: none;
    }
    section{
    	padding-top: 90px;
    }

    .section_wrap{
    	width: 940px;
    	margin: 0 auto;
    }
    /*타이틀 박스*/
    .TitleText{
        margin: 0px;
        text-align: center;
    }
    .TitleText h1 {
        color: black;
        padding: 20px 0;
        font-size: 36px;
    }
    .TitleText p {
        color: #666666;
        font-weight: 500;
        padding-bottom: 15px;
    }
    .PostBtn{ /* 즐겨찾기 조회수 코스에 저장 박스*/
        height: 20px;
        display: flex; 
    	justify-content: flex-end; /* 버튼들을 오른쪽으로 정렬 */
    	padding: 10px 5px 20px 5px;
    }
    
	.PostBtn button{
		cursor: pointer;
		margin-left:10px;
		display: flex; /* 버튼 내부 요소를 가로로 정렬 */
    	align-items: center; /* 이미지와 텍스트를 세로 중앙 정렬 */
	}
	.PostBtn img{
		width: 21px;
		height: 19px;
		margin-right: 5px;
	}

	/*네비게이션 메뉴바*/
    .subnav{ 
    	width: 940px;
        height: 63px;
        display: flex;
        flex-direction: row;
        border-top: 1px solid #e6e6e6;
        border-bottom: 1px solid #e6e6e6;
        margin-bottom: 20px;
        justify-content: center;
        background: white;
        z-index: 1;
        transition: top 0.5s ease; /* transition 속성 추가 */
    }
    .hidden {
	    position:fixed;
	    top: 90px; /* 숨겨진 상태일 때 상단에 위치하도록 설정 */
	}
    .subnav_menu{    
    	width: 25%;
        height: 63px; 
        color: #7e7d7d;
        font-size: 20px;
        font-weight: 500;
        text-align: center; /* 글자 수평 중앙 정렬 */
        line-height: 60px; /* 글자 수직 중앙 정렬 */
        display:flex;
        justify-content: center;
        align-items: center;
        position: relative;
    }
    .subnav_menu::after {
	    content: "|";
	    color : #666;
    	position: absolute;
	    right: 0; /* 오른쪽 끝에 붙이기 */
	    top: 0; /* 수직 중앙 정렬을 위해 상단 여백 조절 */
	    font-weight: 500;
	}
	.subnav_menu:last-child::after {
	    content: "";
	    padding: 0; /* 가상 요소를 제거하기 위해 content와 padding을 설정 */
	}
	.subnav_click{
		color : #333;
		font-weight: bold;
	}
	.subnav_menu label{
		width: 50%;
		height: 80%;
		display : block;
		border-bottom: 5px solid transparent;
		cursor: pointer;
	}
	.subnav_click label{
		border-bottom: 5px solid black;
	}


	/*이미지 박스*/
    .imgBox{   
    	width: 940px;     
        position: relative;
    }
    .slide_container{   /*이미지 슬라이드*/
    	display: flex;
        overflow: hidden;
        margin: 0 auto;
       
    }
    .slide_img{
    	width: 940px;
		height: 660px;
		display: flex;
		justify-content: center;
		align-items: center;
		background-size: cover; /* 배경 이미지가 부모 요소를 완전히 채우도록 설정 */
  		background-position: center; /* 배경 이미지를 가운데 정렬 */
    }
    
    .prev, .next{ /* 슬라이드 버튼 */
        position: absolute;
        top: 280px;
        z-index: 1;
    }
    .prev{
        left: 10px;
    }
    .next{
        right: 10px;
    }


	/*상세정보 박스*/
    .DetailBox{  
        width: 940px;
    }
	.DetailBox h2{
		padding: 31px 0px 9px 0px;
		border-bottom: 2px solid #333;
	}
	.DetailText{ /*상세정보 텍스트*/
	    padding: 35px 0;
	}


	/*지도*/
	.MapView{       /* 지도 view */
        margin-bottom: 25px;
    }
    #map{
       height:350px;
    }

	/* 지도 view 디테일 박스 */
	.MapDetailBox{
		width: 940px;  
        display:flex;
        flex-direction: row;
	}
	.MapDetailBox ul{
		width: 470px;
	}
	.MapDetailBox li{
		display: flex;
		flex-direction: row;
		padding: 5px 0;
		font-weight: 100;
	}
	.MapDetailBox li:before {
	    content: "\2022";  /* Unicode 코드 포인트로 disc를 표현 */
	    font-size: 14px;
    	font-weight: bolder;
	    margin-right: 10px; /* 원하는 간격 설정 */
    }
	.MapDetailBox li strong{
		display: block;
		width:100px;
	}
	.MapDetailBox li span{
		display: block;
		width:353px;
		color: #555555;
	}
</style>

<script>
	
	//이미지 슬라이드 버튼
	let imgindex = 0;
	function nextImg() {
		let imageIds = []; // 이미지의 id 값을 저장할 배열
	    // 이미지 요소들의 id 값을 순회하며 배열에 추가
	    let imageContainer = document.querySelector('.imgBox'); // 이미지 컨테이너 요소
	    let divElements = imageContainer.getElementsByTagName('div'); // 이미지 요소들
	    for (let i = 0; i < divElements.length; i++) {
	        imageIds.push(divElements[i].id);
	    }
	    if(imgindex == imageIds.length-1){
	    	imgindex = -1;
		}
	    for(let i=0; i<imageIds.length; i++){
	    	let Image = document.getElementById(imageIds[i]);
	    	let width = -940 * (imgindex+1);
	    	//Image.style.transform = 'translateX(940px)';
	    	Image.style.transform = 'translateX('+width+'px)';
	    	Image.style.transition = 'transform 1s ease';
	    }
	    imgindex++;
	}
	
	function prevImg() {
		let imageIds = []; // 이미지의 id 값을 저장할 배열
	    // 이미지 요소들의 id 값을 순회하며 배열에 추가
	    let imageContainer = document.querySelector('.imgBox'); // 이미지 컨테이너 요소
	    let divElements = imageContainer.getElementsByTagName('div'); // 이미지 요소들
	    for (let i = 0; i < divElements.length; i++) {
	        imageIds.push(divElements[i].id);
	    }
	    if (imgindex == 0) {
	        imgindex = imageIds.length;
	    }
	    imgindex--;
	
	    for (let i = 0; i < imageIds.length; i++) {
	        let Image = document.getElementById(imageIds[i]);
	        let width = -940 * imgindex;
	        Image.style.transform = 'translateX(' + width + 'px)';
	        Image.style.transition = 'transform 1s ease';
	    }
	}
	
	// 메뉴 클릭
	function menuClick(obj){
		let element = obj;
		// 모든 요소에 대한 NodeList 가져오기
	    let elements = document.querySelectorAll('.subnav_menu');
	    // 모든 요소에서 "subnav_click" 클래스 제거
	    for (let i = 0; i < elements.length; i++) {
       		let item = elements[i];
            item.classList.remove("subnav_click");
	    }
	   
	    element.classList.add("subnav_click");  // 선택된 요소에 "subnav_click" 클래스 추가
	    document.querySelector('.subnav').classList.add('hidden');  // 선택된 요소에 "hidden" 클래스 추가
	    
	    let targetId = element.querySelector('label').getAttribute('for'); // 선택된 메뉴의 label의 for 속성값 (대상 섹션 ID) 가져오기
	    let targetSection = document.getElementById(targetId); // 대상 섹션 요소 가져오기
        targetSection.scrollIntoView({ behavior: 'smooth' }); // 부드러운 스크롤 효과와 함께 해당 섹션으로 이동
    }
	
	
	// 스크롤 이벤트 감지
	window.addEventListener('scroll', function() {
	    let lastScrollTop = 0; // 이전 스크롤 위치 초기화
	    let currentScrollTop = window.scrollY; // 현재 스크롤 위치 가져오기
	        // 스크롤 방향 확인
	        if (currentScrollTop > lastScrollTop) {
	        	document.querySelector('header').classList.add('scrolled'); // 스크롤된 상태를 나타내는 클래스 추가
	        	// 스크롤이 180px 이상 될 때만 효과 적용
	    	    if (currentScrollTop > 180) {
	            // 아래로 스크롤할 때
	            document.querySelector('.subnav').classList.add('hidden'); // hidden 클래스 추가하여 subnav 숨김
	    	    }
	        } else {
	            // 위로 스크롤할 때
	            document.querySelector('header').classList.remove('scrolled'); // 스크롤된 상태를 나타내는 클래스 제거
	            document.querySelector('.subnav').classList.remove('hidden'); // hidden 클래스 제거하여 subnav 표시
	        }
	    lastScrollTop = currentScrollTop; // 현재 스크롤 위치를 이전 스크롤 위치로 업데이트
	});
	
	
	// 좋아요 버튼 클릭
   	function likeclick(contentId, mylikeNum){
		
   		let userId = '<%= session.getAttribute("userId")%>'; // 세션에 저장된 userId값 가져오기
   		if (userId == 'null') {
   			alert("로그인이 필요한 서비스입니다.");
   			window.location.href = "${path}/loginform";
   	    }else{
   	    	$.ajax({
   		 		type:"GET" ,
   		 		url: "${path}/like",
   		 		data: "contentId=" + contentId+"&mylikeNum="+mylikeNum,
   		 		success : function( data){
   		 			$("#likeimg").empty();
   		 			let str = "";
   		 			if(mylikeNum == 0){ // 좋아요 
   		 				alert("좋아요를 누르셨습니다.");
   		 				str = "<img src=\"https://korean.visitkorea.or.kr/resources/images/sub/ico_post01_on.png\" "
   		 					+ "onclick=\"likeclick("+contentId+", 1)\">" + data.likeNum;
   		 			}else{ // 좋아요 취소
   		 				alert("좋아요가 취소되었습니다.");
   		 				str = "<img src=\"https://korean.visitkorea.or.kr/resources/images/sub/ico_post01.png\" "
   		 					+ "onclick=\"likeclick("+contentId+", 0)\">" + data.likeNum;
   		 			}
   		 			$("#likeimg").append(str);
   		 		},
   		 		error: function(){
   		 			alert( "error");
   		 		}
   		 	});	
   	    }
   	}
   	

</script>


</head>
<body>

	
	
	<header><%@ include file="header.jsp" %></header>
	
	<section>
		<div class="section_wrap">
	       <div class="TitleText">
	           <h1><strong>${content.title}</strong></h1>
	           <p>${content.nickName } ${content.sigunguname}</p>
	
	           <div class="PostBtn">
	               	<button id="likeimg">
	               		<c:choose>
						    <c:when test="${mylikeNum == 0}">
						        <img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_post01.png" onclick="likeclick(${content.contentid}, 0)">
						    </c:when>
						    <c:otherwise>
						    	<img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_post01_on.png" onclick="likeclick(${content.contentid}, 1)">
						    </c:otherwise>
						</c:choose>
	               		${likeNum}
               		</button>
	               	<button><img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_post07.png" style="width:24px;">${content.view_num}</button>
	               	<button><img src="https://korean.visitkorea.or.kr/resources/images/sub/ico_post05.png" style="width:20px;"></button>
	           </div>
	       </div>
	
	       <!-- 네비게이션 메뉴바 -->
           <ul class="subnav">
	           <li class="subnav_menu subnav_click" onclick="menuClick(this)"><label for="imgBox">사진보기</label></li>
	           <li class="subnav_menu" onclick="menuClick(this)"><label for="DetailBox">상세보기</label></li>
	           <li class="subnav_menu" onclick="menuClick(this)"><label for="ReviewBox">여행톡</label></li>
           </ul>
	        
	
		   <!-- 이미지 박스 -->
	       <div class="imgBox" id="imgBox">
	            <div class="slide_container">
	            <c:forEach var="item" items="${imglist}" varStatus="loop">
	            	<div class="slide_img" style="background-image:url('${item.imgurl}');" id="image${loop.index + 1}">
	            		<!--<img src="${item.imgurl}" id="image${loop.index + 1}">  left:${940*(loop.index)}px-->
	            	</div>
	            </c:forEach>
	            </div>
	            <img class="prev" src="https://korean.visitkorea.or.kr/resources/images/sub/btn_photo_prev.png" onclick="prevImg()">
	            <img class="next" src="https://korean.visitkorea.or.kr/resources/images/sub/btn_photo_next.png" onclick="nextImg()">
	       </div>
	
	
	       <!-- 상세내용 박스 -->
	       <div class="DetailBox" id="DetailBox">
	       		<h2>상세정보</h2>
	            <div class="DetailText">
                    ${content.overview }
	            </div>
				<!-- 지도 -->
	            <div class="MapView">
	            	<div id ="map"></div>
	            </div>
	            
				<!-- 상세내용 -->
				<div class="MapDetailBox">
					<ul>
						<li>
							<strong>문의 및 안내</strong>
							<c:choose>
							    <c:when test="${not empty content.tel}">
							    	<span>${content.tel }</span>
							    </c:when>
							    <c:otherwise>
							        <span>${content.infocenter}</span>
							    </c:otherwise>
							</c:choose>
	                	</li>
	                	<li>
		                	<strong>주소</strong>
		                	<span>${content.address}</span>
	                	</li>
	                	<li>
		                	<strong>주차</strong>
		                	<span>${content.parking}</span>
	                	</li>
					</ul>
					<ul>
						<li>
		                	<strong>홈페이지</strong>
		                	<span>${content.homepage}</span>
		                </li>
		                
		                <li>
		                	<strong>영업시간</strong>
		                	<span>${content.usetime}</span>
		                </li>
		                <li>
		                	<strong>휴일</strong>
		                	<span>${content.restdate}</span>
		                </li>
		                
		                
					</ul>
				</div>
				
				
	  
	        </div>
	        
	        <!-- 여행톡 박스 -->
	        <div class="ReviewBox" id="ReviewBox"></div>
	    </div>
	</section>
	
	<footer><%@ include file="footer.jsp" %></footer>
	
	
	
	
	
	
	<script>
		let mapy = ${content.mapy};
		let mapx = ${content.mapx};
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = { 
		    center: new kakao.maps.LatLng(mapy, mapx), // 지도의 중심좌표
		    level: 4 // 지도의 확대 레벨
		};
		var map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
		//마커가 표시될 위치입니다 
		var markerPosition  = new kakao.maps.LatLng(mapy, mapx); 
		//마커를 생성합니다
		var marker = new kakao.maps.Marker({
			position: markerPosition
		});
		//마커가 지도 위에 표시되도록 설정합니다
		marker.setMap(map);
		//아래 코드는 지도 위의 마커를 제거하는 코드입니다
		//marker.setMap(null);   
	</script>
	
	
	
</body>
</html>