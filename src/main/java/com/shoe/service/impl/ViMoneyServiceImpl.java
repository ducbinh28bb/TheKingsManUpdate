package com.shoe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dao.ViMoneyDao;
import com.shoe.entity.ViMoney;
import com.shoe.service.ViMoneyService;

@Service
public class ViMoneyServiceImpl implements ViMoneyService{
	@Autowired
	ViMoneyDao viMoneyDao;

	@Override
	public <S extends ViMoney> S save(S entity) {
		return viMoneyDao.save(entity);
	}

	@Override
	public List<ViMoney> findAll() {
		return viMoneyDao.findAll();
	}

	
	
	
}
