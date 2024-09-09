package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dao.HistoryDao;
import com.shoe.entity.History;
import com.shoe.service.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService{
	@Autowired
	HistoryDao historyDao;

	@Override
	public <S extends History> S save(S entity) {
		return historyDao.save(entity);
	}

	@Override
	public List<History> findAll() {
		return historyDao.findAll();
	}

	@Override
	public Optional<History> findById(Integer id) {
		return historyDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		historyDao.deleteById(id);
	}

	@Override
	public void delete(History entity) {
		historyDao.delete(entity);
	}

	@Override
	public History getById(Integer id) {
		return historyDao.getById(id);
	}

	
	
}
