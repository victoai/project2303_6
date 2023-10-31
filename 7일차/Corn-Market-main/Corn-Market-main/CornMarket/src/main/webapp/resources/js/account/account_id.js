function searchdisplayhp() {
  if ($('.search-type-display-hp').css('display') == 'none') {
    $('.search-type-display-hp').show();
  } else {
    $('.search-type-display-hp').hide();
  }
}
function searchdisplayemail() {
  if ($('.search-type-display-email').css('display') == 'none') {
    $('.search-type-display-email').show();
  } else {
    $('.search-type-display-email').hide();
  }
}

//errormessage

function idpopup() {
  let name = document.getElementById('memberNm1').value;
  let hp = document.getElementById('memberHp').value;
  //let id = document.getElementById("conInput").value
  if (name === '') {
    document.getElementById('nameerror').innerHTML = '이름이 올바르지 않습니다.';
    check = false;
  } else {
    document.getElementById('nameerror').innerHTML = '';
  }

  if (hp === '') {
    document.getElementById('hperror').innerHTML = '휴대폰 번호가 올바르지 않습니다.';
    check = false;
  } else {
    document.getElementById('hperror').innerHTML = '';
  }
 
}

//찾는 아이디 있는지 확인 - 휴대폰찾기
function checkIdFromPhone() {
  let name = $('#memberNm1').val();
  let phoneNume = $('#memberHp').val();
  let idPhone = {
    user_name: name,
    phone: phoneNume,
  };
  $.ajax({
    type: 'POST',
    url: idPhoneUrl + '/check',
    headers: { 'content-type': 'application/json' }, 
    data: JSON.stringify(idPhone),
    success: function (data) {
      //console.log(data);
      if (data == 1) {
        idFromPhone();
        $('.error').hide();
      } else if (data == 0) {
      	$('.error').show();
        document.getElementById("hperror").innerHTML = "입력하신 정보가 올바르지 않습니다. 다시 확인해주세요."
        check=false
     }
    },
    error: function (data) {
    },
  }); //ajax
}

//찾는 아이디 있는지 확인 - 이메일찾기
function checkIdFromMail() {
  let name = $('#memberNm2').val();
  let mail1 = $('#memberEmail').val();
  let mail2 = $('#memberEmail2').val();
  let idMail = {
    user_name: name,
    email_id: mail1,
    email_domain: mail2,
  };
  $.ajax({
    type: 'POST',
    url: idMailUrl + '/check',
    headers: { 'content-type': 'application/json' }, // 요청 헤더
    data: JSON.stringify(idMail),
    success: function (data) {
      //console.log(data);
      if (data == 1) {
        idFromMail();
        $('.error').hide();
      } else if (data == 0) {
      	$('.error').show();
       	document.getElementById("emailerror").innerHTML = "입력하신 정보가 올바르지 않습니다. 다시 확인해주세요."
        check=false
      }
    },
    error: function (data) {
      
    },
  }); //ajax
}
//아이디 찾기 form 제출
function idFromPhone() {
  let url = '';
  let windowTargetName = 'targetName';
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 450;
  let popupHeight = 300;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  let features = 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY;
  window.open(url, windowTargetName, features);

  frmPhone.action = idPhoneUrl;
  frmPhone.method = 'POST';
  frmPhone.target = windowTargetName;
  frmPhone.submit();
}
function idFromMail() {
  let url = '';
  let windowTargetName = 'targetName';
  //팝업창 화면 가운데로 띄우기
  let popupWidth = 450;
  let popupHeight = 300;
  let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
  let popupY = Math.ceil((window.screen.height - popupHeight) / 2);
  let features = 'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY;
  window.open(url, windowTargetName, features);

  frmMail.action = idMailUrl;
  frmMail.method = 'POST';
  frmMail.target = windowTargetName;
  frmMail.submit();
}
//아이디 찾기 버튼
function idFromPhoneBtn() {
  //idpopup();
  checkIdFromPhone();
}
function idFromMailBtn() {
  checkIdFromMail();
}
