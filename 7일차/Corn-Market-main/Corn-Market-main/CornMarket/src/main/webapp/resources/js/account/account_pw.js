//전송할 이메일 맞는지 확인
function checkEmail() {
  let email = $('#memberEmail').val() + '@' + $('#memberEmail2').val();
  //console.log(email);
  let id = $('#conInput').val();
  let name = $('#memberNm2').val();
  let searchPw = {
    user_id: id,
    user_name: name,
  };
  $.ajax({
    type: 'POST',
    url: pwUrl + '/mail/check',
    data: email,
    contentType: 'text/plain; charset=utf-8',
    success: function (data) {
      //console.log(data);
      if (data == 1) {
        codenumshow(); //인증번호 입력창 보여주기
        sendEmail(); //인증번호 메일 전송
        $('.error').hide();
      } else if (data == 0) {
      	$('.error').show();
        document.getElementById("emailerror").innerHTML = "입력하신 정보가 올바르지 않습니다. 다시 확인해주세요."
        check=false;
      }
    },
    error: function (data) {
      alert('오류 발생');
    },
  }); //ajax
}
//비밀번호 찾기 메일 인증번호 전송
function sendEmail() {
  const email = $('#memberEmail').val() + '@' + $('#memberEmail2').val();
  //console.log('이메일 주소 : ' + email);
  const codeNum = $('#codeNum');

  $.ajax({
    type: 'GET',
    url: pwMailUrl + email,
    success: function () {
      //console.log(data);
      codeNum.attr('disabled', false);
      //code = data;
      alert('인증번호가 전송되었습니다.');
    },
    error: function () {
      alert('오류 발생');
    },
  }); //ajax
}
//인증번호 받기 버튼
function mailBtnClick() {
  $('#mailBtn').click(() => {
    
    checkPw();
    mailtimer();
  }); //click
}
mailBtnClick();

function codenumshow() {
  $('#code-show-input').show();
}

//비밀번호 찾기 인증번호 확인 버튼
function numBtnClick() {
  $('#numBtn').click(() => {
    const mail_code = $('#codeNum').val(); // 인증코드 값
    //console.log('인증 코드 : ' + mail_code);

    $.ajax({
      type: 'GET',
      url: pwMailCodeUrl + mail_code,
      success: function (data) {
        //console.log(data);
        if (data == 1) {
          alert('인증번호가 확인되었습니다.');
          $('#code-show-input').hide(); //인증번호 입력창 숨기기
          $('#mailBtn').attr('disabled', true); //인증번호 전송버튼 비활성화
          $('#mailBtn').text('인증완료');
          $('#mailBtn').css('cursor', 'default');
        } else if (data == 0) {
          alert('인증번호를 다시 입력해주세요.');
        }
      },
      error: function (data) {
        alert('오류 발생');
      },
    }); //ajax
  }); //click
}
numBtnClick();

function searchdisplayemail() {
  if ($('.search-type-display-email').css('display') == 'none') {
    $('.search-type-display-email').show();
  } else {
    $('.search-type-display-email').hide();
  }
}

// timer
function mailtimer(){
	var time = 600;
	var min = '';
	var sec = '';
	/*
	 */
	var x = setInterval(function () {
	  min = parseInt(time / 60);
	  sec = time % 60;
	
	  document.getElementById('code-timer').innerHTML = min + '분' + sec + '초';
	  time--;
	
	  if (time < 0) {
	    clearInterval(x);
	    document.getElementById('code-timer').innerHTML = '시간초과';
	  }
}, 1000);
}


//errormessage

//찾는 비밀번호 있는지 확인
function checkPw() {
  let id = $('#conInput').val();
  let name = $('#memberNm2').val();
  let mail1 = $('#memberEmail').val();
  let mail2 = $('#memberEmail2').val();
  let searchPw = {
    user_id: id,
    user_name: name,
    email_id: mail1,
    email_domain: mail2,
  };
  $.ajax({
    type: 'POST',
    url: pwUrl + '/check',
    headers: { 'content-type': 'application/json' }, // 요청 헤더
    data: JSON.stringify(searchPw),
    success: function (data) {
      //console.log(data);
      if (data == 1) {
      	codenumshow(); //인증번호 입력창 보여주기
        sendEmail(); //인증번호 메일 전송
        $('.error').hide();
        
      } else if (data == 0) {
     		$('.error').show();
	        document.getElementById("emailerror").innerHTML = "입력하신 정보가 올바르지 않습니다. 다시 확인해주세요."
	        check=false
      }
    },
    error: function (data) {
      alert('오류 발생');
    },
  }); //ajax
}
//비밀번호 찾기 form 제출
function submitPw() {
  let url = '';
  let windowTargetName = 'targetName';
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 450;
  let popupHeight = 300;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  let features = 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY;
  window.open(url, windowTargetName, features);

  frmPw.action = pwUrl;
  frmPw.method = 'POST';
  frmPw.target = windowTargetName;
  frmPw.submit();
}
//비밀번호 찾기 버튼
function pwBtn() {
  if ($('#mailBtn').text() === '인증완료') submitPw();
  else alert('메일 인증을 진행해 주세요.');
}
