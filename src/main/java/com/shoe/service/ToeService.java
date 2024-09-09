package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Toe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ToeService {

	Toe getById(Integer id);

	void delete(Toe entity);

	void deleteById(Integer id);

	Optional<Toe> findById(Integer id);

	List<Toe> findAll();

	Page<Toe> findAll(Pageable pageable);

	<S extends Toe> S save(S entity);

	List<Toe> findTop4Img();

	Toe findByName(String name);
	
	List<Toe> getAllStatus();
	
	List<Toe> findByName1(String string);

	List<Toe> findByName(String name, Boolean status);

	List<Toe> findByStatus(Boolean status);

}
