<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>날짜 출력</title>
  <style>
    #dateContainer {
      margin-top: 20px;
    }
  </style>
</head>
<body>

<button onclick="showNextDates()">다음 10일 보기</button>
<div id="dateContainer"></div>

<script>
  // 초기 날짜를 설정
  let currentDate = new Date();
  // 
  let cnt=0;


  function showNextDates() {
	  
	 if( cnt >= 9) {
		 alert(" 3개월까지만 지원");
		 return;
	 }
	  
    // 이전에 표시된 날짜 정보 지우기
    document.getElementById('dateContainer').innerHTML = '';

    // 10개의 날짜 정보를 표시
    for (let i = 0; i < 10; i++) {
      // 날짜 정보를 div에 추가
      var dateDiv = document.createElement('div');
      dateDiv.textContent = (i + 1) + ". 날짜: " + currentDate.toLocaleDateString();
      document.getElementById('dateContainer').appendChild(dateDiv);

      // 다음 날짜로 업데이트
      currentDate.setDate(currentDate.getDate() + 1);
    }
    
    cnt++;
    
   
  }
</script>

</body>
</html>

