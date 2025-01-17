package com.shoe.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.entity.Feedback;

@Repository
public interface FeedbackDao extends JpaRepository<Feedback, Integer>{

	@Query("SELECT o FROM Feedback o WHERE o.product.productId=?1")
	List<Feedback> findByProductID(Integer id, Sort sort);


}