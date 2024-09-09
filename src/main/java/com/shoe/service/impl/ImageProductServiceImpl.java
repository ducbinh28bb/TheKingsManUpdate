package com.shoe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoe.dao.ImageProductDao;
import com.shoe.entity.ImageProduct;
import com.shoe.service.ImageProductService;
@Service
public class ImageProductServiceImpl implements ImageProductService{
	@Autowired
	ImageProductDao imageProductDao;

	@Override
	public <S extends ImageProduct> S save(S entity) {
		return imageProductDao.save(entity);
	}

	@Override
	public List<ImageProduct> findAll() {
		return imageProductDao.findAll();
	}

	@Override
	public List<ImageProduct> findAllById(Iterable<Integer> ids) {
		return imageProductDao.findAllById(ids);
	}

	@Override
	public List<ImageProduct> findById(Integer id) {
		return imageProductDao.findByIdproduct(id);
	}

	@Override
	public void deleteById(Integer id) {
		imageProductDao.deleteById(id);
	}

	@Override
	public void delete(ImageProduct entity) {
		imageProductDao.delete(entity);
	}

	@Override
	public ImageProduct getById(Integer id) {
		return imageProductDao.getById(id);
	}
	
	@Override
	public ImageProduct update(ImageProduct imageProduct) {
		// TODO Auto-generated method stub
		return imageProductDao.save(imageProduct);
	}
	
}
