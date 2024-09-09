package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shoe.dao.MaterialDao;
import com.shoe.entity.Material;
import com.shoe.service.MaterialService;

@Service
public class MaterialServiceImpl implements MaterialService {
	
	@Autowired
	private MaterialDao materialDao;

	@Override
	public <S extends Material> S save(S entity) {
		return materialDao.save(entity);
	}

	@Override
	public Page<Material> findAll(Pageable pageable) {
		return materialDao.findAll(pageable);
	}

	@Override
	public List<Material> findAll() {
		return materialDao.findAll();
	}

	@Override
	public Optional<Material> findById(Integer id) {
		return materialDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		materialDao.deleteById(id);
	}

	@Override
	public void delete(Material entity) {
		materialDao.delete(entity);
	}

	@Override
	public Material getById(Integer id) {
		return materialDao.getById(id);
	}

	public List<Material> findTop4Img() {
		// TODO Auto-generated method stub
		return materialDao.findTop4Img();
	}

	@Override
	public Material findByName(String name) {
		// TODO Auto-generated method stub
		return materialDao.findByName(name);
	}
	
	@Override
	public List<Material> getAllStatus() {
		// TODO Auto-generated method stub
		return materialDao.getAllStatus();
	}

	@Override
	public List<Material> findByName1(String name) {
		// TODO Auto-generated method stub
		return materialDao.findByName1(name);
	}

	@Override
	public List<Material> findByName(String name, Boolean status) {
		// TODO Auto-generated method stub
		return materialDao.findByName(name,status);
	}

	@Override
	public List<Material> findByStatus(Boolean status) {
		// TODO Auto-generated method stub
		return materialDao.findByStatus(status);
	}
	
}
