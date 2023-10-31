function login(){

	let id = $('#user_id').val();
    let pw = $('#user_pw').val();

    if (id == '') {
      alert('아이디를 입력해주세요');
      $('#user_id').focus();
      return false;
    } else if (pw == '') {
      alert('비밀번호를 입력해주세요');
      $('#user_pw').focus();
      return false;
    } else {
      right_login();
      
    }
}

function right_login(){
	
	let user_id = $("#user_id").val();
	let user_pw = $("#user_pw").val();
	let member = {
	    	user_id:user_id,
	    	user_pw:user_pw,
	    };
	let url = $("#url").val();
	   
	$.ajax({
    type: 'POST',
    url: url + '/login/checklogin',
    headers: { 'content-type': 'application/json' }, 
    data: JSON.stringify(member),
    datatype : 'json',
    success: function (data) {
      //console.log(data);
      if (data == 1) {
        frm.submit();
      } else if (data == 0) {
		alert("아이디와 비밀번호를 확인하세요");
		location.href="/market/login";
        check=false
     }
    },
    error: function () {
    	alert("오류입니다");
    },
  }); 
  
}

