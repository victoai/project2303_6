window.addEventListener("load", function () {
  let slides = document.querySelectorAll(".swiper-slide");
  let contents = []; //배열
  slides.forEach(function (slide, index) {
    contents[index] = slide.outerHTML;
  });

  let index = 0;
  function nextSlide() {
    index++;
    if (index >= contents.length) {
      index = 1;
    }

    let swiper = document.querySelector(".swiper-wrap");

    //애니메이션 효과
    swiper.animate(
      {
        transform: [
          `translateX(${(index - 1) * -100}vw)`, //시작
          `translateX(${index * -100}vw)`, //도착
        ],
      },
      {
        duration: 1500, // 위의효과가 1초간 지속되도록
        fill: "forwards",
      }
    );
  }

  setInterval(nextSlide, 5000);

});