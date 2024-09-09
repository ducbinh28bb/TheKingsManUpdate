package com.shoe.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dao.RolesDao;
import com.shoe.entity.Roles;
import com.shoe.service.RolesService;

@Service
public class RoleServiceImpl implements RolesService {
	@Autowired
	RolesDao rolesDao;

	@Override
	public <S extends Roles> S save(S entity) {
		return rolesDao.save(entity);
	}

	@Override
	public List<Roles> findAll() {
		return rolesDao.findAll();
	}

	@Override
	public Optional<Roles> findById(Integer id) {
		return rolesDao.findById(id);
	}

	@Override
	public void deleteById(Integer id) {
		rolesDao.deleteById(id);
	}

	@Override
	public void delete(Roles entity) {
		rolesDao.delete(entity);
	}

	@Override
	public Roles getById(Integer id) {
		return rolesDao.getById(id);
	}

	public Roles findByName(String name) {
		return rolesDao.findByName(name);
	}

}
