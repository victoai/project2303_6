<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<style>
	*{
       margin: 0;
       padding: 0;
       list-style: none;
    }
    section{
        border: 1px solid black;
        width: 940px;
        margin: 0 auto;
    }
    
    
    .titleArea{
        border: 1px solid black;
    }
    
    
    .titleContent{
        border: 1px solid black;
        display: flex;
        flex-direction: row;
        justify-content: space-between; /* 양쪽 끝으로 정렬 */
        width: 600px;
    }
    .titleContent_sort{
        border: 1px solid black;
        display: flex;
        flex-direction: row;
        justify-content: flex-end;
    }
    .titleContent_sort p::after{
        content: "ㅣ";
        margin : 0 10px;
    }
    .titleContent_sort p:last-child::after{
        content: "";
        margin : 0;
    }

	
    .listWrap{
        display: flex;
        flex-direction: row;
        justify-content: space-between; 
    }
    
    /* 관광지 리스트 */
    .contentList{
        display: flex;
        flex-direction: row;
        border-bottom: 1px solid black;
        position: relative;
        width: 600px;
    }
    .contentList img{
        width: 140px;
        height: 94px;
        padding: 10px;
    }
    .contentList_text{
        display: flex;
        flex-direction: column; /* 내용을 세로로 정렬 */
        justify-content: center; /* 수직 방향으로 중앙 정렬 */
    }
    .contentList_text img{
        width: 14px;
        height: 16px;
        position: absolute;
        top: 0;
        right: 0;
    }

	/* 지역배너 */
    .titleContent_list{
        border: 1px solid black;
        width: 300px;
    }
    .titleContent_list_sort{
        display: flex;
        flex-direction: row;
        justify-content: center;
    }
    .titleContent_list_sort li{
        padding: 0 10px;
    }
    
    /* 페이지 번호 */
    .page_grp{
     	border: 1px solid black;
     	width: 600px;
     	display: flex;
        flex-direction: row;
        justify-content: center; /* 수평 가운데 정렬 */
    	align-items: center; /* 수직 가운데 정렬 */
    	padding: 20px 0;
    }
    .page_num{
     	border: 1px solid #13294b;
     	width: 32px;
     	height: 32px;
     	text-align: center;
     	line-height: 32px;
     	margin-left: 5px;
     	cursor: pointer;
    }
    
    .page_click{
	    background-color: #13294b;
	    color: #fff;
    }
    
</style>

<script>

	window.addEventListener("load", function(){
		currentPageList(1);
	}); 

	function currentPageList(currentPage){
		$.ajax({
	 		type:"GET" ,
	 		url: "/searchapi/sightslist",
	 		data: "currentPage=" + currentPage,
	 		success : function( data){	 
	 			toHtml(data);
	 			pageClick(currentPage);
	 		},
	 		error: function(){
	 			alert( "error");
	 		}
	 	});	
	}
	
	function  toHtml( data){
		//alert("성공");
		$("#listSubWrap").empty();
		$("#page_grp").empty();
		
		let item = data.contentList;
		let str1 = "";
		for(let i=0; i<item.length; i++){
			str1 += "<li>";
			str1 += "	<ul class=\"contentList\">";
			str1 += "		<li>";
			str1 += "			<img src=\""+item[i].firstimage+"\">";
			str1 += "		</li>";
			str1 += "		<li class=\"contentList_text\">";
			str1 += "			<h3>"+item[i].title+"</h3>";
			str1 += "			<p>"+item[i].nickName + " " + item[i].sigunguname +"</p>";
			str1 += "			<p>이 데이터는 어디에..??</p>";
			str1 += "			<img src=\"https://korean.visitkorea.or.kr/resources/images/sub/btn_dot2.png\">";
			str1 += "		</li>";
			str1 += "	</ul>";
			str1 += "</li>";
		}
		
		let item2 = data.handler;
		let str2 = "";
		if(item2.currentGrp > 1){
			str2 += "<div class=\"page_num\" onclick=\"currentPageList("+(item2.grpStartPage-1)+")\"> << </div>";
		}
		for(let i = item2.grpStartPage; i <= item2.grpEndPage ; i++){
			str2 += "<div class=\"page_num\" id=\""+i+"\" onclick=\"currentPageList("+i+")\">"+i+"</div>";
		}
		if(item2.currentGrp != item2.lastGrp){  
			str2 += "<div class=\"page_num\" onclick=\"currentPageList("+(item2.grpEndPage+1)+")\"> >> </div>";
		} 
		$("#listSubWrap").append(str1);
		$("#page_grp").append(str2);	
	}
	
	
	// 현재 페이지 스타일 변경 클래스 추가
	function pageClick(num){
		let element = document.getElementById(num); // 요소를 선택
		element.classList.add("page_click"); // 클래스 추가
	}
	
	
</script>

</head>

<body>
	<section>
        <div class="titleArea">
            <h1>#<span>전체</span></h1>
        </div>
        <div class="titleContent">
            <div class="titleContent_cnt">
                <h3>총<span style="color: #0a97cd;">${totalcnt}</span>건</h3>
            </div>
            <div class="titleContent_sort">
                <p>최신순</p>
                <p>거리순</p>
                <p>인기순</p>
            </div>
        </div>
        <div class="listWrap">
            <ul id="listSubWrap">
            	 <%--<c:forEach var="item" items="${contentList}">
                <li>
                    <ul class="contentList">
                        <li>
                            <img src="${item.firstimage}">
                        </li>
                        <li class="contentList_text">
                            <h3>${item.title}</h3>
                            <p>${item.nickName} ${item.sigunguname}</p>
                            <p>이 데이터는 어디에..??</p>
                            <img src="https://korean.visitkorea.or.kr/resources/images/sub/btn_dot2.png">
                        </li>            
                    </ul>
                </li>
                </c:forEach>--%>
            </ul> 
            <ul class="titleContent_list">
          		<c:forEach var="item" items="${sidoList}" varStatus="loop">
	          		<c:if test="${loop.index % 3 == 0}">
		            	<li>
			                 <ul class="titleContent_list_sort">
	                 </c:if>
			                     <li>#<span>${item.nickName}</span></li>
			        <c:if test="${loop.index % 3 == 2 or loop.last}">
			                 </ul>
		           		</li> 
		           	</c:if>	
           	 	</c:forEach>
            </ul>
        </div>
        
        <div class="page_grp" id="page_grp">
        	<%-- <c:if test="${handler.currentGrp > 1}">
       			<div class="page_num"><<</div>
       		</c:if>
        	<c:forEach var="item" begin="${handler.grpStartPage}" end="${handler.grpEndPage}">
       			<div class="page_num" id="${item}" onclick="currentPageList(${item})">${item}</div>
        	</c:forEach>
       		<c:if test="${handler.currentGrp != handler.lastGrp}">
       			<div class="page_num" onclick="currentPageList(${handler.grpEndPage+1})">>></div>
       		</c:if> --%>
        </div>
        
    </section>
</body>
</html>