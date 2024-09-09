package com.shoe.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shoe.entity.News;

@Repository
public interface NewsDao extends JpaRepository<News, Integer>{
	
	
	
}
