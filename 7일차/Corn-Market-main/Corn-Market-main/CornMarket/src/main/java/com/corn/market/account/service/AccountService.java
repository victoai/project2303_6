package com.corn.market.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.corn.market.account.dao.AccountDao;
import com.corn.market.account.domain.AccountId;
import com.corn.market.account.domain.AccountPw;
import com.corn.market.account.domain.SearchIdMail;
import com.corn.market.account.domain.SearchIdPhone;
import com.corn.market.account.domain.SearchPw;

@Service
public class AccountService {
	
	@Autowired
	private AccountDao dao;
	
	//아이디 찾기 - 휴대폰번호로
	public AccountId searchIdPhone(SearchIdPhone phone) {
		AccountId accountId = dao.selectUserId1(phone);
		return accountId;
	}
	
	//아이디 찾기 - 이메일로
	public AccountId searchIdMail(SearchIdMail mail) {
		AccountId accountId = dao.selectUserId2(mail);
		return accountId;
	}
	
	//비밀번호 찾기
	public AccountPw searchPw(SearchPw searchPw) {
		AccountPw accountPw = dao.selectUserPw(searchPw);
		return accountPw;
	}
	
	//이메일 확인
	public int checkEmail(String email) {
		int check = dao.selectEmail(email);
		return check;
	}
}
