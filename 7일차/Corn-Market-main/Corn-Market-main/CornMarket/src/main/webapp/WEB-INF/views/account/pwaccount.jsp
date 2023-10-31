<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Corn-Market 아이디/비밀번호찾기</title>
    <c:set var="path" value="${pageContext.request.contextPath}"/>
    <link rel="stylesheet" href="${path}/resources/css/account/account.css"/>

</head>


<body>
<jsp:include page="../base/header.jsp"/>
       <div class="search-idpw-title">
           <h2>아이디/비밀번호 찾기</h2>
       </div>
       <div class="search-wrap">
           <div class="search-tab">
               <ul class="search-tab-ul">
                   <li class="search-current">
                       <div class="search-status-id"><a href='<c:url value="/account/id" />'>아이디 찾기</a></div>
                   </li>
                   <li class="search-current">
                       <div class="search-status-pw"><a href='<c:url value="/account/pw" />'>비밀번호 찾기</a></div>
                   </li>
               </ul>
           </div>
           <div class="id-search-wrap">
               <div class="id-search-notice">
                   <span>본인확인 후 비밀번호를 다시 설정할 수 있습니다.
                   </span>
               </div>
               <div class="search-type">
                   <ul>
                       <li class="type-list-current">
                           <div class="search-title">
                               <button type="button" id="search-display" onclick="searchdisplayemail()"
                                   class="arrow-dropdown-hp">등록된 이메일로
                                   찾기</button>
                           </div>
                           <div class="search-type-display-email">
                               <div class="inputEnter" style="display: block;">
                                   <form action="<c:url value='/account/pw'/>" method="POST" name="frmPw">
                                       <div class="inputBox">
                                           <div class="styleInput">
                                               <input id="conInput" name="user_id" type="text"
                                                   class="input1" placeholder="아이디">
                                               <span id="conClear" class="delBtn" style="display: none;"></span>
                                           </div>
                                           <div class="styleInput">
                                               <input id="memberNm2" name="user_name" type="text" class="input1"
                                                   placeholder="이름">
                                               <span id="conClear" class="delBtn" style="display: none;"></span>
                                           </div>
                                           <div class="emailInput">
                                               <div class="styleInput">
                                                   <input name="email_id" id="memberEmail" type="text"
                                                       class="inputE" placeholder="이메일">
                                                   <span id="conClear" class="delBtn" style="display:none;"></span>
                                               </div>
                                               <span class="emaillinep">@</span>
                                               <div class="userSelect">
                                                   <select name="email_domain" id="memberEmail2">
                                                       <option value="">선택</option>
                                                       <option value="gmail.com">gmail.com</option>
                                                       <option value="hanmail.net">hanmail.net</option>
                                                       <option value="hotmail.com">hotmail.com</option>
                                                       <option value="nate.com">nate.com</option>
                                                       <option value="naver.com">naver.com</option>
                                                       <option value="yahoo.co.kr">yahoo.co.kr</option>
                                                       <option value="yahoo.com">yahoo.com</option>
                                                   </select>
                                               </div>
                                               <div class="codesendInput">
                                                   <button type="button" id="mailBtn">인증번호 요청</button>
                                                   
                                               </div>
                                              
                                           </div>
                                           <div class="codenumInput" id="code-show-input">
                                                       <input id="codeNum" class="inputC" type="text"
                                                           placeholder="인증번호 네자리를 입력해주세요">
                                                       <input type="button" class="codebtn" id="numBtn" value="인증번호 확인">
                                                       <!-- <button type="button" class="codebtn" id="numBtn">인증번호 확인</button> -->
                                                       <div id="code-timer"></div>
                                                   
                                           </div>
                                           <p class="error" id="emailerror" style="display:none;"></p>
                                           <div class="btnArea">
                                               <button type="button" onclick="pwBtn()" class="btnYellow">확인</button>
                                           </div>
                                       </div>
                                   </form>
                               </div>
                           </div>
                       </li>
                   </ul>
               </div>
           </div>
       </div>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="${path}/resources/js/account/account_pw.js"></script>
	<script type="text/javascript">
    	let pwUrl = "<c:url value='/account/pw'/>";
    	let pwMailUrl = "<c:url value='/account/pw/mail?email='/>";
    	let pwMailCodeUrl = "<c:url value='/account/pw/mail/code?mail_code='/>";
    </script>
<jsp:include page="../base/footer.jsp"/>
</body>

</html>