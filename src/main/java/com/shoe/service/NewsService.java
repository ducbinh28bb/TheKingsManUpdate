package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.News;

public interface NewsService {

	News getById(Integer id);

	void delete(News entity);

	void deleteById(Integer id);

	Optional<News> findById(Integer id);

	List<News> findAll();

	<S extends News> S save(S entity);

}
