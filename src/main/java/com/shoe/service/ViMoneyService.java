package com.shoe.service;

import java.util.List;

import com.shoe.entity.ViMoney;

public interface ViMoneyService {

	List<ViMoney> findAll();

	<S extends ViMoney> S save(S entity);

}
