package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoe.dao.DesignDao;
import com.shoe.entity.Design;
import com.shoe.service.DesignService;

@Service
public class DesignServiceImpl implements DesignService {
	
	@Autowired
	private DesignDao designDao;

	@Override
	public <S extends Design> S save(S entity) {
		return designDao.save(entity);
	}

	@Override
	public Page<Design> findAll(Pageable pageable) {
		return designDao.findAll(pageable);
	}

	@Override
	public List<Design> findAll() {
		return designDao.findAll();
	}

	@Override
	public Optional<Design> findById(Integer id) {
		return designDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		designDao.deleteById(id);
	}

	@Override
	public void delete(Design entity) {
		designDao.delete(entity);
	}

	@Override
	public Design getById(Integer id) {
		return designDao.getById(id);
	}

	public List<Design> findTop4Img() {
		// TODO Auto-generated method stub
		return designDao.findTop4Img();
	}

	@Override
	public Design findByName(String name) {
		// TODO Auto-generated method stub
		return designDao.findByName(name);
	}
	
	@Override
	public List<Design> getAllStatus() {
		// TODO Auto-generated method stub
		return designDao.getAllStatus();
	}

	@Override
	public List<Design> findByName1(String name) {
		// TODO Auto-generated method stub
		return designDao.findByName1(name);
	}

	@Override
	public List<Design> findByName(String name, Boolean status) {
		// TODO Auto-generated method stub
		return designDao.findByName(name,status);
	}

	@Override
	public List<Design> findByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return designDao.findByStatus(status);
	}
	
}
