package com.corn.market.town.service;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.corn.market.town.dao.TownDao;
import com.corn.market.town.domain.TownCertification;
import com.corn.market.town.domain.TownInfo;

@Service
public class TownService {
	
	@Autowired
	private TownDao townDao;
	@Autowired
	private DataSource dataSource;
	
	//town code 가져오기
	public String getTownCode(TownInfo townInfo) {
		String town_code = townDao.selectTownCode(townInfo);
		return town_code;
	}
	
	//동네인증 이전기록 삭제하고 새로운 인증 등록
	public void deleteAndInputAddress(TownCertification town) {
		//트랜잭션
		PlatformTransactionManager tm = new DataSourceTransactionManager(dataSource);
		TransactionStatus status = tm.getTransaction(new DefaultTransactionDefinition());
		//이전 인증기록 개수
		int count = townDao.selectCountAddress(town.getUser_id());
		try {
			if(count==0) {
				townDao.insertNewAddress(town);
				tm.commit(status);
			}else {
				townDao.deletePrevAddress(town.getUser_id());
				townDao.insertNewAddress(town);
				tm.commit(status);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tm.rollback(status);
		}
	}

}
