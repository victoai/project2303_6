<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
  .nj_area_box{
     width: 1200px;
     margin: 0 auto;
     border: 1px solid black;
  }
  .nj_area_txt{
     width: 600px;
     height: 150px;
     border: 1px solid black;
  }
  .nj_area_sido{
     width: 600px;
     border: 1px solid black;
     display: flex;
     flex-wrap: wrap;
  }
  .nj_area_sido li{
     list-style-type: none;
     flex: 0 0 calc(33.333% - 10px);
     margin-right: 10px; /* 간격 설정 */
     margin-bottom: 10px; /* 아래쪽 간격 설정 */
  }
  .nj_area_sigun{
     display : none;
     width: 600px;
     border: 1px solid black;
     display: flex;
   
  }
  .nj_area_btn{
     width: 600px;
  }

  .nj_area_btn button{
     width: 150px;
     height: 50px;
  }
  /*여행 기간*/
  .nj_schedule_box{
     display: none;
     width: 1200px;
     margin: 0 auto;
     border: 1px solid black;
  }
  .nj_schedule_txt{
     width: 600px;
     height: 150px;
     border: 1px solid black;
  }
  .nj_schedule_list{
     width: 600px;
     border: 1px solid black;
     display: flex;
     flex-wrap: wrap;
  }
  .nj_schedule_list li{
     list-style-type: none;
  }
  .nj_schedule_btn{
     width: 600px;
  }

  .nj_schedule_btn button{
     width: 150px;
     height: 50px;
  }
  .nj_tour_box{
     display: none;
  }
  .nj_tour_random div {
     display: flex;
     align-items: center;
     cursor: pointer;
  }
  .nj_list_box {
     margin-bottom: 10px;
     border: 1px solid black;
     height: 300px;
  }
  .nj_list_text{
     max-height: 150px; 
     overflow: auto; 
     flex: 1; 
     padding: 10px; 
     display: flex;
  }
  .nj_placeimg{
     width: 300px;
  }
  
</style>
<script>
	
function showsigu(obj) {
    let value = parseInt(obj.value);
    $('.nj_area_sigun').css("display", "block");
    $.ajax({
        type: "GET",
        url: "/prj/recTour2",
        data: { value: value },
        success: function (data) {
            console.log(data);
            $(".nj_area_sigun").empty();
            console.log(data.length);

            // 그룹을 묶기 위한 ul 요소 생성
            let ul = $("<ul>", { class: "outer" });

            for (let i = 0; i < data.length; i++) {
                let item = data[i];
                let li = $("<li>");
                let radio = $("<input>", {
                    type: "radio",
                    id: "s_area" + i,
                    name: "s_area",
                    value: item.sigungucode
                });
                let label = $("<label>", {
                    for: "s_area" + i,
                    text: item.name + i
                });

                li.append(radio);
                li.append(label);

                // 각 li 요소를 그룹화된 ul에 추가
                ul.append(li);

                // 한 그룹에 3개씩 출력되도록 처리
                if ((i + 1) % 3 === 0) {
                    // 그룹을 .nj_area_sigun에 추가하고 새로운 ul 요소 생성
                    $(".nj_area_sigun").append(ul);
                    ul = $("<ul>", { class: "outer" });
                }
            }

            // 남은 요소가 있는 경우 추가
            if (ul.children().length > 0) {
                $(".nj_area_sigun").append(ul);
            }
        }
    });
}
  function hiddensigu(){
	$('.nj_area_sigun').css("display","none");  
  }
  function areaBtn(){
	  $('.nj_area_box').css("display","none");
	  $('.nj_schedule_box').css("display","block");
  }
  function sheduleBtnBefore(){
	  $('.nj_area_box').css("display","block");
	  $('.nj_schedule_box').css("display","none");
  }
  
  let selectPlaces=[];
  function sheduleBtnNext(){
	  $('.nj_schedule_box').css("display","none");
	  $('.nj_tour_box').css("display","block");
	  $.ajax({
	        type: "GET",
	        url: "/prj/recTour3",	       
	        success: function (data) {
	            console.log(data);
	            console.log(data.length);
	            let tourRandom = document.querySelector(".nj_tour_random");
	            for(let i=0; i<10; i++){
	            	let item = data[i];
	            	let div = document.createElement("div");
            	    div.setAttribute("data-value", item.contentid);
                    div.classList.add("nj_list_box");
                    div.innerHTML =
                        '<img src="' +
                        item.firstimage +
                        '" alt="Option' +
                        i +
                        '" class="nj_placeimg">' +
                        '<div class="nj_list_text">' +
                        '<span>' +
                        item.title +
                        '</span>' +
                        '<span>' +
                        item.address +
                        '</span>' +
                        '<span>' +
                        item.overview +
                        '</span>' +
                        '</div>';
                    
                    // 데이터 속성에 정보 저장
                    div.dataset.item = JSON.stringify(item);

                    // 클릭 이벤트 추가
                    div.addEventListener('click', selecttour , this);
                    
                    tourRandom.appendChild(div);
                   
	            }
	        }
	    });
	  $('.nj_rec_btn').on('click', function() {
	        // 서버로 selectedItems 배열 전송하는 코드 작성
	        /*
	        $.ajax({
	            type: "GET", // POST 또는 GET 요청 설정
	            url: "http://localhost:8080/hi", // 서버 URL
	            //data: JSON.stringify(selectPlaces), // JSON 데이터 배열 전송
	            contentType: "application/json",
	            success: function(response) {
	                // 서버 응답에 대한 동작
	                console.log(response);
	            }
	           
	        });
	        */
	        if(selectPlaces.length==0){
	        	alert("선택하셍");
	        }else{
	        	for(let i=0; i<selectPlaces.length; i++){
		        	console.log(selectPlaces[i]);
		        }
	        }
	        window.location.href = "http://localhost:8080/recommend";
	    
	       
	    });
	  
	
  }


  function selecttour(  obj){
	  
	  alert("fkgfgkfgkgfgf");
	
	    console.log( event);
	    
	  if($(obj).css("border")=="1px solid rgb(0, 0, 0)"){
		  let selectedItem = JSON.parse(event.currentTarget.dataset.item);
		  alert(JSON.stringify(selectedItem));
		  $(obj).css("border","3px solid red");
		  selectPlaces.push(selectedItem);
	  }
	  
	  /*
	  else{
		  $(event.currentTarget).css("border","1px solid black");
		  let selectedItem = JSON.parse(event.currentTarget.dataset.item);
		  let index = selectPlaces.findIndex(item => item.contentid === selectedItem.contentid);
		  if (index > -1) {
			  selectPlaces.splice(index, 1);
	        }
	  } 
	  
	  */
  }
  
</script>
</head>
<body>
<!-- div의 크기는 군구 데이터 유무에 따라 높이 달라진다. -->
<div class="nj_area_box">
  <!-- 여행 선택 텍스트 -->
  <div class="nj_area_txt">
  <b>01</b>/03<br>
  여행 희망 지역 선택
  </div>
  <!-- 시/도에 대한 버튼 출력 -->
  <div class="nj_area_sido">
    <li>
      <input type="radio" id="area1" name="area" value=1 onclick="hiddensigu()">
      <label for="area1">서울</label>
    </li>
    <li>
      <input type="radio" id="area2" name="area" value=2 onclick="hiddensigu()">
      <label for="area2">인천</label>
    </li>
    <li>
      <input type="radio" id="area3" name="area" value=3 onclick="hiddensigu()">
      <label for="area3">대전</label>
    </li>
    <li>
      <input type="radio" id="area4" name="area" value=4 onclick="hiddensigu()">
      <label for="area4">대구</label>
    </li>
    <li>
      <input type="radio" id="area5" name="area" value=5 onclick="hiddensigu()">
      <label for="area5">광주</label>
    </li>
    <li>
      <input type="radio" id="area6" name="area" value=6 onclick="hiddensigu()">
      <label for="area6">부산</label>
    </li>
    <li>
      <input type="radio" id="area7" name="area" value=7 onclick="hiddensigu()">
      <label for="area7">울산</label>
    </li>
    <li>
      <input type="radio" id="area8" name="area" value=8 onclick="hiddensigu()">
      <label for="area8">세종</label>
    </li>
    <li>
      <input type="radio" id="area31" name="area" value=31 onclick="showsigu( this)">
      <label for="area31">경기</label>
    </li>
    <li>
      <input type="radio" id="area32" name="area" value=32 onclick="showsigu( this)">
      <label for="area32">강원</label>
    </li>
    <li>
      <input type="radio" id="area33" name="area" value=33 onclick="showsigu( this)">
      <label for="area33">충북</label>
    </li>
    <li>
      <input type="radio" id="area34" name="area" value=34 onclick="showsigu( this)">
      <label for="area34">충남</label>
    </li>
    <li>
      <input type="radio" id="area35" name="area" value=35 onclick="showsigu( this)">
      <label for="area35">경북</label>
    </li>
    <li>
      <input type="radio" id="area36" name="area" value=36 onclick="showsigu( this)">
      <label for="area36">경남</label>
    </li>
    <li>
      <input type="radio" id="area37" name="area" value=37 onclick="showsigu( this)">
      <label for="area37">전북</label>
    </li>
    <li>
      <input type="radio" id="area38" name="area" value=38 onclick="showsigu( this)">
      <label for="area38">전남</label>
    </li>
    <li>
      <input type="radio" id="area39" name="area" value=39 onclick="hiddensigu()">
      <label for="area39">제주</label>
    </li>
   
  </div>
  <!-- 군/구에 대한 버튼 출력-->
  <div class="nj_area_sigun">
  </div>
  <div class="nj_area_btn">
  <!-- 다음 버튼 클릭 시 여행 기간 선택 -->
  <button onclick="areaBtn()">다음</button>
  </div>
</div>
<div class="nj_schedule_box">
        <!-- 여행 선택 텍스트 -->
        <div class="nj_schedule_txt">
        <b>02</b>/03<br>
        여행 기간을 선택해 주세요!
        </div>
        <!-- 여행 기간 선택 -->
        <div class="nj_schedule_list">
          <li>
            <input type="radio" id="day1" name="schedule" value="day1">
            <label for="day1">당일 여행</label>
          </li>
          <li>
            <input type="radio" id="day2" name="schedule" value="day2">
            <label for="day2">1박 2일</label>
          </li>
          <li>
            <input type="radio" id="day3" name="schedule" value="day3">
            <label for="day3">2박 3일</label>
          </li>
          
         
        </div>
       
        <div class="nj_schedule_btn">
            <button onclick="sheduleBtnBefore()">이전</button>
            <button onclick="sheduleBtnNext()">다음</button>
        </div>
</div>
<div class="nj_tour_box">
        <div class="nj_tour_txt">
	        <b>03</b>/03<br>
	        관심있는 여행지를 선택해주세요.
	        관심 여행지를 기반으로 추천해드립니당!
	        5개부터 10개???
        </div>
        <div class="nj_tour_random">
        </div>
        <div><button class="nj_rec_btn">추천받기</button></div>
        <!-- 이전 버튼 누르면 이전에 선택했던 데이터 모두 취소 기능 추가해야 함-->
</div>
</body>
</html>