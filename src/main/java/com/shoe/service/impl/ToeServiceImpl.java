package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoe.dao.ToeDao;
import com.shoe.entity.Toe;
import com.shoe.service.ToeService;

@Service
public class ToeServiceImpl implements ToeService {
	
	@Autowired
	private ToeDao toeDao;

	@Override
	public <S extends Toe> S save(S entity) {
		return toeDao.save(entity);
	}

	@Override
	public Page<Toe> findAll(Pageable pageable) {
		return toeDao.findAll(pageable);
	}

	@Override
	public List<Toe> findAll() {
		return toeDao.findAll();
	}

	@Override
	public Optional<Toe> findById(Integer id) {
		return toeDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		toeDao.deleteById(id);
	}

	@Override
	public void delete(Toe entity) {
		toeDao.delete(entity);
	}

	@Override
	public Toe getById(Integer id) {
		return toeDao.getById(id);
	}

	public List<Toe> findTop4Img() {
		// TODO Auto-generated method stub
		return toeDao.findTop4Img();
	}

	@Override
	public Toe findByName(String name) {
		// TODO Auto-generated method stub
		return toeDao.findByName(name);
	}
	
	@Override
	public List<Toe> getAllStatus() {
		// TODO Auto-generated method stub
		return toeDao.getAllStatus();
	}

	@Override
	public List<Toe> findByName1(String name) {
		// TODO Auto-generated method stub
		return toeDao.findByName1(name);
	}

	@Override
	public List<Toe> findByName(String name, Boolean status) {
		// TODO Auto-generated method stub
		return toeDao.findByName(name,status);
	}

	@Override
	public List<Toe> findByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return toeDao.findByStatus(status);
	}
	
}
