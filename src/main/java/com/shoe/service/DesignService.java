package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Design;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DesignService {

	Design getById(Integer id);

	void delete(Design entity);

	void deleteById(Integer id);

	Optional<Design> findById(Integer id);

	List<Design> findAll();

	Page<Design> findAll(Pageable pageable);

	<S extends Design> S save(S entity);

	List<Design> findTop4Img();

	Design findByName(String name);
	
	List<Design> getAllStatus();
	
	List<Design> findByName1(String string);

	List<Design> findByName(String name, Boolean status);

	List<Design> findByStatus(Boolean status);

}
