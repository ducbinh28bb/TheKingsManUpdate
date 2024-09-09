package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Height;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface HeightService {

	Height getById(Integer id);

	void delete(Height entity);

	void deleteById(Integer id);

	Optional<Height> findById(Integer id);

	List<Height> findAll();

	Page<Height> findAll(Pageable pageable);

	<S extends Height> S save(S entity);

	List<Height> findTop4Img();

	Height findByName(String name);
	
	List<Height> getAllStatus();
	
	List<Height> findByName1(String string);

	List<Height> findByName(String name, Boolean status);

	List<Height> findByStatus(Boolean status);

}
