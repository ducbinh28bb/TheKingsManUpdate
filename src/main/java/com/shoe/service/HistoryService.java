package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.History;

public interface HistoryService {

	History getById(Integer id);

	void delete(History entity);

	void deleteById(Integer id);

	Optional<History> findById(Integer id);

	List<History> findAll();

	<S extends History> S save(S entity);


}
