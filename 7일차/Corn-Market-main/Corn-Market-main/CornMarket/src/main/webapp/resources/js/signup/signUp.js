window.addEventListener("load", function() {

      $("#joinbtn").click(save);

   function save(){
      var haveto1 = $("#haveto1").is(":checked");
      var haveto2 = $("#haveto2").is(":checked");
      var haveto3 = $("#haveto3").is(":checked");
      
      let name = $("#member_name").val();
      let id = $("#member_id").val();
      let nm = $("#member_nm").val();
      let pw = $("#member_pw").val();
      let pwd = $("#member_pw_db").val();
      let email_f = $("#member_email_f").val();
      let phone = $("#member_ph").val();
   
       if(haveto1, haveto2, haveto3 == false){
         alert("필수약관에 동의해주세요");
         $("#haveto1").focus();
      } else if(name == ""){
         alert("이름을 입력하세요");
         $("#member_name").focus();
      } else if(id == ""){
         alert("아이디를 입력하세요");
         $("#member_id").focus();
      } else if(nm == ""){
         alert("닉네임을 입력하세요");
         $("#member_nm").focus();
      } else if(pw == ""){
         alert("비밀번호를 입력하세요");
         $("#member_pw").focus();
      } else if(pwd == ""){
         alert("비밀번호 확인 란을 입력하세요");
         $("#member_pw_db").focus();
      } else if(email_f == ""){
         alert("이메일을 입력하세요");
         $("#member_email_f").focus();
      }else if(phone == ""){
         alert("전화번호를 입력하세요");
         $("#member_ph").focus();
      } else{
         member.submit();
         alert("회원가입 성공");
      }
      
   }
      
} );
   
   //비밀번호 중복확인
   function pw_dbcheck(){

      let pw = $("#member_pw").val();
      let pwd = $("#member_pw_db").val();
      
      if(pw != '' && pwd != '') {
           if(pw == pwd) {
               document.getElementById("pw_error").innerHTML="비밀번호가 일치합니다.";
               document.getElementById("pw_error").style.color="blue";
           }
           else {
               document.getElementById("pw_error").innerHTML="비밀번호가 일치하지 않습니다.";
               document.getElementById("pw_error").style.color="red";
           }
       }
       
       
   } 
   
 
//아이디  중복확인
function idcheck(){
	let user_id= $('#member_id').val();
	let url=$('#url').val();
	if(user_id == ''){
		alert("사용할 아이디를 입력해주세요.");
		return;
	};
	$.ajax({
	type: 'POST',
 	url: url + '/idcheck',
    data: user_id,
    contentType: 'text/plain; charset=utf-8',
   	success:function(data){
   		//console.log(data);
		if(data == 0){
			alert("사용 가능한 닉네임입니다.");
		}
	    else if(data == 1) {
			alert("사용중인 닉네임입니다.");
	    }
		},
		
		error: function(){
		alert("에러입니다.");
			}
		
			});
	}
	
//닉네임  중복확인
	function nickcheck(){
	   
	 let nickname= $('#member_nm').val();
	 let url=$('#url').val();
		if(nickname == ''){
			alert("사용할 아이디를 입력해주세요.");
			return;
		};

	  $.ajax({
	  	type: 'GET',
		url: url + '/nickcheck',
	  	data: nickname,
		contentType: 'text/plain; charset=utf-8',
	   	success:function(data){
	   	//console.log(data);
		if(data == 0){
		alert("사용 가능한  닉네임입니다.");
		}
	    else{
			alert("사용중인  닉네임입니다. ");
	    }
		},
		
	error: function(){
		alert("에러입니다.");
			}
		
			});

	     }
	     
	 