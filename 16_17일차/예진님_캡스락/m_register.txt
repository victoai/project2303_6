function checkid(){
	 $.ajax(
			 {
				 type:"post",
				 data: $("#userid").serialize(), 
				 dataType:"json",
				 url:"/Medi3/idcheck",	    
				 success: function( data, textStatus){
					if (data.result==1) {
						alert("사용 가능 한 아이디 입니다.");
						$('input[name=useridchk]').attr('value',"1");
					} else if ($('input[name=userid]')== ""){
						alert("아이디를 입력해주세요.");
					} else {
						alert("사용 불가능 한 아이디 입니다.");
						$('input[name=useridchk]').attr('value',"0");
					}
					
				 }
				 ,
				 error: function( data, textStatus){		
					 alert("아이디 로드 실패, 관리자에게 문의하세요.");
				 },		 
				 complete:function(data, textStatus){
				 }		 
			}
		);
};

function chkCharCode(event) {
  let regExp = /[^0-9a-zA-Z!@#$%^&*()_+=?,.<>-]/g;
  let ele = event.target;
  if (regExp.test(ele.value)) {
	alert("영어대소문자와 숫자, 특수문자들만 입력가능합니다.");
    ele.value = ele.value.replace(regExp, '');
  }
};

function chkNameCode(event) {
 	let regExp = /[^ㄱ-ㅎ가-힣a-zA-z]/g;
 	let ele = event.target;
  if (regExp.test(ele.value)) {
	alert("한글과 영어만 입력해주세요.");
    ele.value = ele.value.replace(regExp, '');
  }
};

function chkNumCode(event) {
  let regExp = /[^0-9-]/g;
  let ele = event.target;
  if (regExp.test(ele.value)) {
	alert("숫자와 대쉬만 입력해주세요.");
    ele.value = ele.value.replace(regExp, '');
  }
};

function checkaddr(){
	 new daum.Postcode({
         oncomplete: function(data) {
                
                let addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("addr").value = extraAddr;
                
                } else {
                    document.getElementById("addr").value = '';
                }

                //주소 정보를 해당 필드에 넣는다.
                document.getElementById("addr").value = addr;
           }
     }).open();
}

function check(){
	let form =document.regfrm;
	let id = form.userid;
	let idck= form.useridchk;
	let pw = form.userpw;
	let pwck = form.userpwcheck;
	let name = form.name;
	let num = form.num;
	let addr = form.addr;
	let tel = form.tel;
	
	//let reg_num="/[^0-9-]{14}/g";
	//let reg_tel="/[^0-9-]{13}/;
	
	let reg_num=/\d{6}[-][1-4]\d{6}/g;
	let reg_tel= /^01([0|1|6|7|8|9])-?([0-9]{3,4})-?([0-9]{4})$/
	
	if(id.value==""){
		alert("아이디를 입력하세요.");
		id.focus();
	}else if(idck.value!="1" ){
		alert("아이디 확인을 해주세요.");
		id.focus();
	}else if(pw.value==""){
		alert("비밀번호를 입력하세요.");
		pw.focus();
	}else if(pwck.value==""){
		alert("비밀번호 확인를 입력하세요.");
		pwck.focus();
	}else if(pw.value!=pwck.value){
		alert("비밀번호와 비밀번호 확인 부분을 확인하세요.");
		pw.focus();
	}else if(name.value==""){
		alert("이름을 입력하세요.");
		name.focus();
	}else if(num.value==""){
		alert("주민번호를 입력하세요.");
		num.focus();
	}else if(addr.value==""){
		alert("주소를 입력하세요.");
		addr.focus();
	}else if(tel.value==""){
		alert("전화번호를 입력하세요.");
		tel.focus();
	}else if (!reg_num.test(num.value)){
		alert("주민번호에 오류가 있습니다. 예시와 양식을 맞춰주세요");
	}else if (!reg_tel.test(tel.value)){
		alert("전화번호에 오류가 있습니다. 예시와 양식을 맞춰주세요");
	}else {form.submit();}
};