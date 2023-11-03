<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

<style>
.wrap {
	height: 3000px;
	width: 1000px;
	margin: 0 auto;
	dixplay: flex;
}

.imgwrap {
	width: 1000px;
	height: 100px;
	border: none;
	display: flex;
	outline: 1px solid gray;
	line-height: 100px;
}

.imgBox {
	width: 100px;
	height: 100px;
}
</style>

<script>
	$("html, body").animate({scrollTop: 0}, 0);
    let page = 1; //초기 페이지
    let HeightY = 600; //페이지당 나오는 아이템들 높이합
    let cursorH = page*HeightY; //로드 시 스크롤 위치 조정
    let wrapH = HeightY*5; //초기 wrap 높이
    
    let totalPage; // totalpage ajax에서 불러옴
    

    let loading = false; // 추가 데이터 로딩 중 여부
    loadPage(page);
    
  	function  pageToString(list){
    	 console.log( list); 
    	 let sql = "";
    	 list.forEach(  ( item) => { 
    		 console.log( item); 
     		 sql += `     	 
     		<div class="imgwrap">
     			<div class="div1">
     				<img
     					src='<%= "${item.image1}" %>'
     					class="imgBox" />
     			</div>
     			<div>&nbsp[<%="${item.num}"%>] <%="${item.content}"%> |&nbsp</div>
     			<div>좋아요 수 : <%="${item.likenum}"%>
     			</div>
     		</div>  
    	`;} );
    	
        return sql;
 	}

    // 페이지 로드 함수
    function loadPage(pageNumber) {
        if (!loading) {
            loading = true;
            $.ajax({
                url: "slidelist?p=" + pageNumber, // 서버측 엔드포인트 설정
                type: "GET",              
                success: function (data) {
                    // 받은 HTML을 .wrap에 추가
                    let list =  data.list;
                    totalPage = data.totalPage;
                    console.log("성공" + data);     
                    console.log("헨들러" + totalPage);  
                 
                    let sql = pageToString(list);
                    $(".wrap").empty();
                    $(".wrap").append(sql);
                  
                    loading = false;
                },
                error: function (error) {
                    console.log("Error:", error);
                    loading = false;
                }
            });
        }
    }

    // 스크롤 이벤트
    window.addEventListener("scroll", function () {
        const scrollY = window.scrollY;
        const pageHeight = document.body.scrollHeight;
        
        if (scrollY >= HeightY && page < totalPage) {
            page += 1;
            HeightY += cursorH;
            //alert(page + " 작동, " + pageHeight+", " + scrollY);
            $("html, body").animate({scrollTop: (page-1)*cursorH}, 500);
            loadPage(page);
            wrapH += cursorH;
            $(".wrap").css("height", wrapH);
	    } 
    });
    
</script>
</head>
<body>
	<div class="wrap"></div>


</body>
</html>
