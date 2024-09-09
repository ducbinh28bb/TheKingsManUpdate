package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface SizeService {

	Size getById(Integer id);

	void delete(Size entity);

	void deleteById(Integer id);

	Optional<Size> findById(Integer id);

	List<Size> findAll();

	Page<Size> findAll(Pageable pageable);

	<S extends Size> S save(S entity);

	List<Size> findTop4Img();

	Size findByName(String name);
	
	List<Size> getAllStatus();
	
	List<Size> findByName1(String string);

	List<Size> findByName(String name,Boolean status);

	List<Size> findByStatus(Boolean status);

}
