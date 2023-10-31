-- 회원정보 
CREATE TABLE user_tbl22(
  user_id VARCHAR2(20) 
    CONSTRAINT user_id_pk PRIMARY KEY,
  user_pw VARCHAR2(20) 
    CONSTRAINT user_pw_nn NOT NULL,
  user_name VARCHAR2(30) 
    CONSTRAINT user_name_nn NOT NULL,
  nickname VARCHAR2(50) 
    CONSTRAINT user_nickname_nn NOT NULL
    CONSTRAINT user_nickname_uk UNIQUE,
  email VARCHAR2(50) 
    CONSTRAINT user_email_nn NOT NULL,
  phone VARCHAR2(13) 
    CONSTRAINT user_phone_nn NOT NULL
    CONSTRAINT user_phone_uk UNIQUE,
  temperature NUMBER(3,1) DEFAULT 36.5
    CONSTRAINT user_temperature_nn NOT NULL,
  profile_img VARCHAR2(300) DEFAULT '/resources/images/profile/profile_img_default.png' ,
  join_date TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT user_join_date_nn NOT NULL
);

-- 자치구 정보
CREATE TABLE town_tbl22(
  town_code NUMBER(5) 
    CONSTRAINT town_code_pk PRIMARY KEY,
  town_name VARCHAR2(20) 
    CONSTRAINT town_name_nn NOT NULL
);

-- 카테고리 
CREATE TABLE category_tbl22(
  category_id VARCHAR2(5) 
    CONSTRAINT category_id_pk PRIMARY KEY,
  category_name VARCHAR2(30) 
    CONSTRAINT category_name_nn NOT NULL
);

-- 회원 동네 인증
CREATE TABLE address_tbl22(
  addr_id NUMBER(6) 
    CONSTRAINT addr_id_pk PRIMARY KEY,
  user_id VARCHAR2(20) 
    CONSTRAINT addr_user_id_nn NOT NULL
    CONSTRAINT addr_user_id_fk REFERENCES user_tbl22(user_id),
  town_code NUMBER(5) 
    CONSTRAINT addr_town_code_nn NOT NULL
    CONSTRAINT addr_town_code_fk REFERENCES town_tbl22(town_code),
  latitude VARCHAR2(20) 
    CONSTRAINT addr_latitude_nn NOT NULL,
  longitude VARCHAR2(20) 
    CONSTRAINT addr_longitude_nn NOT NULL,
  addr_date TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT addr_date_nn NOT NULL
);

-- 판매글 
CREATE TABLE post_tbl22(
  post_id NUMBER(6) 
    CONSTRAINT post_id_pk PRIMARY KEY,
  user_id VARCHAR2(20) 
    CONSTRAINT post_user_id_nn NOT NULL 
    CONSTRAINT post_user_id_fk REFERENCES user_tbl22(user_id),
  category_id VARCHAR2(20) 
    CONSTRAINT post_category_id_nn NOT NULL 
    CONSTRAINT post_category_id_fk REFERENCES category_tbl22(category_id),
  town_code NUMBER(5) 
    CONSTRAINT post_town_code_nn NOT NULL 
    CONSTRAINT post_town_code_fk REFERENCES town_tbl22(town_code),
  title VARCHAR2(90) 
    CONSTRAINT post_title_nn NOT NULL,
  price NUMBER(8) 
    CONSTRAINT post_price_nn NOT NULL,
  content VARCHAR2(900),
  created TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT post_created_nn NOT NULL,
  updated TIMESTAMP DEFAULT SYSTIMESTAMP,
  post_status VARCHAR2(15) DEFAULT '판매중' 
    CONSTRAINT post_status_ck CHECK(post_status IN('판매중','거래완료','삭제'))
    CONSTRAINT post_status_nn NOT NULL,
  post_img VARCHAR2(900) DEFAULT '/resources/images/post/post_img_thumbnail.png'
);

-- 채팅룸 
CREATE table chatting_room_tbl22(
  room_id VARCHAR2(40) 
    CONSTRAINT chatting_room_id_pk PRIMARY KEY,
  post_id NUMBER(6) 
    CONSTRAINT chatting_post_id_nn NOT NULL 
    CONSTRAINT chatting_post_id_fk REFERENCES post_tbl22(post_id),
  seller_id VARCHAR2(20)
    CONSTRAINT chatting_seller_id_nn NOT NULL 
    CONSTRAINT chatting_seller_id_fk REFERENCES user_tbl22(user_id),
  buyer_id VARCHAR2(20) 
    CONSTRAINT chatting_buyer_id_nn NOT NULL 
    CONSTRAINT chatting_buyer_id_fk REFERENCES user_tbl22(user_id),
  created TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT chatting_created_nn NOT NULL,
  chatting_status NUMBER(1) DEFAULT 0
    CONSTRAINT chatting_status_ck CHECK(chatting_status IN(0,1,2,3))
);

-- 채팅내용
CREATE TABLE chatting_content_tbl22(
  content_id NUMBER(5)  
    CONSTRAINT content_id_pk PRIMARY KEY,
  room_id VARCHAR2(40) 
    CONSTRAINT content_room_id_nn NOT NULL 
    CONSTRAINT content_room_id_fk REFERENCES chatting_room_tbl22(room_id),
  sender_id VARCHAR2(20) 
    CONSTRAINT content_sender_id_nn NOT NULL 
    CONSTRAINT content_sender_id_fk REFERENCES user_tbl22(user_id),
  chat_content VARCHAR2(300) 
    CONSTRAINT chatting_content_nn NOT NULL,
  send_date TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT content_send_date_nn NOT NULL, 
  read_status NUMBER(1) DEFAULT 1
    CONSTRAINT content_read_status_ck CHECK(read_status IN(0,1)) 
    CONSTRAINT content_read_status_nn NOT NULL 
);

-- 거래완료
CREATE table end_deal_tbl22 (
  deal_id NUMBER(5) 
    CONSTRAINT end_deal_id_pk PRIMARY KEY,
  room_id VARCHAR2(40) 
    CONSTRAINT end_room_id_nn NOT NULL 
    CONSTRAINT end_room_id_fk REFERENCES chatting_room_tbl22(room_id),
  end_date TIMESTAMP DEFAULT SYSTIMESTAMP 
    CONSTRAINT end_room_date_nn NOT NULL,   
  rate NUMBER(1) 
	  CONSTRAINT end_room_rate_nn NOT NULL 
    CONSTRAINT end_room_rate_ck CHECK(rate IN(1,2,3,4,5)),
  review VARCHAR2(300) 
);

-- 동네인증id 시퀀스
CREATE SEQUENCE addr_sq START WITH 100001;

-- 판매글id 시퀀스
CREATE SEQUENCE post_sq START WITH 100001;

-- 채팅룸id 시퀀스 -- 안씀
-- CREATE SEQUENCE room_sq START WITH 10001;

-- 채팅내용 시퀀스
CREATE SEQUENCE content_sq START WITH 10001;

-- 거래완료id 시퀀스
CREATE SEQUENCE deal_sq START WITH 10001;


-- 테이블 삭제
DROP TABLE end_deal_tbl22;
DROP TABLE chatting_content_tbl22;
DROP TABLE chatting_room_tbl22;
DROP TABLE post_tbl22;
DROP TABLE address_tbl22;
DROP TABLE category_tbl22;
DROP TABLE town_tbl22;
DROP TABLE user_tbl22;
-- 시퀀스 삭제
DROP SEQUENCE addr_sq;
DROP SEQUENCE post_sq;
DROP SEQUENCE room_sq;
DROP SEQUENCE content_sq;
DROP SEQUENCE deal_sq;