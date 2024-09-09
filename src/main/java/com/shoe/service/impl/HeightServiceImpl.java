package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoe.dao.HeightDao;
import com.shoe.entity.Height;
import com.shoe.service.HeightService;

@Service
public class HeightServiceImpl implements HeightService {
	
	@Autowired
	private HeightDao heightDao;

	@Override
	public <S extends Height> S save(S entity) {
		return heightDao.save(entity);
	}

	@Override
	public Page<Height> findAll(Pageable pageable) {
		return heightDao.findAll(pageable);
	}

	@Override
	public List<Height> findAll() {
		return heightDao.findAll();
	}

	@Override
	public Optional<Height> findById(Integer id) {
		return heightDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		heightDao.deleteById(id);
	}

	@Override
	public void delete(Height entity) {
		heightDao.delete(entity);
	}

	@Override
	public Height getById(Integer id) {
		return heightDao.getById(id);
	}

	public List<Height> findTop4Img() {
		// TODO Auto-generated method stub
		return heightDao.findTop4Img();
	}

	@Override
	public Height findByName(String name) {
		// TODO Auto-generated method stub
		return heightDao.findByName(name);
	}
	
	@Override
	public List<Height> getAllStatus() {
		// TODO Auto-generated method stub
		return heightDao.getAllStatus();
	}

	@Override
	public List<Height> findByName1(String name) {
		// TODO Auto-generated method stub
		return heightDao.findByName1(name);
	}

	@Override
	public List<Height> findByName(String name, Boolean status) {
		// TODO Auto-generated method stub
		return heightDao.findByName(name,status);
	}

	@Override
	public List<Height> findByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return heightDao.findByStatus(status);
	}
	
}
