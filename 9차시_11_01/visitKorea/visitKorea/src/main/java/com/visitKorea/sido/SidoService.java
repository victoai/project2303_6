package com.visitKorea.sido;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SidoService {

	@Autowired
	SidoDAO dao;
	
	public ArrayList<SidoDTO> getSelectAll(){
		ArrayList<SidoDTO> sidoList = dao.selectAll();
		return sidoList;
	}
}
