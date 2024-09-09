package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MaterialService {

	Material getById(Integer id);

	void delete(Material entity);

	void deleteById(Integer id);

	Optional<Material> findById(Integer id);

	List<Material> findAll();

	Page<Material> findAll(Pageable pageable);

	<S extends Material> S save(S entity);

	List<Material> findTop4Img();

	Material findByName(String name);
	
	List<Material> getAllStatus();
	
	List<Material> findByName1(String string);

	List<Material> findByName(String name, Boolean status);

	List<Material> findByStatus(Boolean status);

}
