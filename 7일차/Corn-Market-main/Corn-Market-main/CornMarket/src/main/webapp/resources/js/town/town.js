function mytown(){
	//window. open("popup_profile_mytown.jsp", "popup01", "width=680, height=700");
	//팝업창 화면 가운데로 띄우기
    let popupWidth = 680;
    let popupHeight = 700;
    let popupX = Math.ceil((window.screen.width - popupWidth) / 2);
    let popupY = Math.ceil((window.screen.height - popupHeight) / 2);

    window.open(
      '/market/town',
      '동네인증',
      'width=' + popupWidth + ',height=' + popupHeight + ',left=' + popupX + ', top=' + popupY
    );
}
