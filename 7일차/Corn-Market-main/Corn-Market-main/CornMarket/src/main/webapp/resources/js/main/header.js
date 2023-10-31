$(document).ready(function () {
  var url = $("#headerjs").val();
  isShow = false; //변수 하나를 설정, 화면에 보이면 true, 보이지 않으면 false;

  $("#menubtn").click(function () {
    if (isShow) {
      //보이니까 숨기고
      isShow = false;
      $("#sidemenu").hide();
      //메뉴바 활성화 안됐을 때는 메뉴바 사진
      $showimg = document.querySelector(".nav-wrap > img");

      $showimg.src = `./img/menubtn.png`;
      $(`.nav-wrap > img`).attr({
        src: url + "/resources/images/header/menubtn.png",
      });
    } else {
      //안보이니까 보이게 한다
      isShow = true;
      $("#sidemenu").show();
      //메뉴바 활성화 되면 닫는 사진
      $hideimg = document.querySelector(".nav-wrap > img");

      $hideimg.src = `./img/closebtn.png`;
      $(`.nav-wrap > img`).attr({
        src: url + "/resources/images/header/closebtn.png",
      });
    }
  });
});
