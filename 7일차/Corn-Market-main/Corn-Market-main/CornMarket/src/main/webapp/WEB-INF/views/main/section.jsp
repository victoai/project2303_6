<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Document</title>
    <link rel="stylesheet" href="${path}/resources/css/main/section.css"/>
  </head>

  <body>
    <div class="swiper">
      <div class="swiper-wrap">
        <div class="swiper-slide slide01">
          <div>
            <h1 class="home-main-title">당신 근처의<br />콘마켓</h1>
            <p class="text-m">
              중고 거래부터 동네 정보까지, 이웃과 함께해<br />
              가깝고 따뜻한 당신의 근처를 만들어요.
            </p>
          </div>

          <div class="home-main-slide-img-01"></div>

        </div>

        <div class="swiper-slide slide02">
          <div class="home-main-slide-img-02"></div>
          <div>
            <h1 class="home-main-title">
              우리동네<br />
              중고 직거래 마켓
            </h1>
            <p class="text-m">
              동네 주민들과 가깝고 따뜻한 거래를 지금 경험해보세요.
            </p>
          </div>
        </div>

        <div class="swiper-slide slide03">
          <div>
            <h1 class="home-main-title">
              이웃과 함께 하는<br />
              동네생활
            </h1>
            <p class="text-m">
              우리 동네의 다양한 이야기를 이웃과 함께 나누어요.
            </p>

            <ul class="home-story-list">
              <li class="home-story-list-item">

                <div class="icon-story1"></div>

                <div class="text-s text-bold">우리동네질문</div>
                <div class="text-xs">
                  궁금한 게 있을 땐 이웃에게 물어보세요.
                </div>
              </li>
              <li class="home-story-list-item">

                <div class="icon-story2"></div>

                <div class="text-s text-bold">동네분실센터</div>
                <div class="text-xs">
                  무언가를 잃어버렸을 때, 함께 찾을 수 있어요.
                </div>
              </li>
              <li class="home-story-list-item">

                <div class="icon-story3"></div>
                <div class="text-s text-bold">동네모임</div>
                <div class="text-xs">
                  관심사가 비슷한 이웃과 온오프라인으로 만나요.
                </div>
              </li>
            </ul>
          </div>
          <div class="home-main-slide-img-03"></div>
        </div>
        <div class="swiper-slide slide04">

          <div class="home-main-slide-img-04"></div>

          <div>
            <h1 class="home-main-title">
              내 근처에서 찾는<br />
              동네가게
            </h1>
            <p class="text-m">
              우리 동네 가게를 찾고 있나요?<br />
              동네 주민이 남긴 진짜 후기를 함께 확인해보세요!
            </p>
          </div>
        </div>

        <div class="swiper-slide slide01">
          <div>
            <h1 class="home-main-title">당신 근처의<br />콘마켓</h1>
            <p class="text-m">
              중고 거래부터 동네 정보까지, 이웃과 함께해<br />
              가깝고 따뜻한 당신의 근처를 만들어요.
            </p>
          </div>

          <div class="home-main-slide-img-01"></div>

        </div>
      </div>
    </div>
    <script src="${path}/resources/js/main/mainslide.js"></script>
  </body>
</html>

