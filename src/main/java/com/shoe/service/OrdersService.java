package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Orders;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.databind.JsonNode;

public interface OrdersService {
	List<Orders> findAll();

	Orders create(JsonNode orders);

	Orders getById(Integer id);

	void delete(Orders entity);

	void deleteById(Integer id);

	Optional<Orders> findById(Integer id);

	<S extends Orders> S save(S entity);
	
	List<Orders> findByUsername(String username);
	
	List<Orders> findByUsername(String username, Pageable pageable);

	List<Orders> getByIdVoucher(Long accountId);

	List<Orders> findByUserId(Long id);
	
	Page<Orders> getOrderByUserId(Long id, Pageable pageable);
	

//	Orders update(Orders product);
//
//	void delete(Integer id);
}
