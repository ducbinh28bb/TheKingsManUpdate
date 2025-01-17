package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.WishList;
import org.springframework.data.domain.Pageable;

public interface WishListService {

	WishList getById(Integer id);

	void delete(WishList entity);

	void deleteById(Integer id);

	Optional<WishList> findById(Integer id);

	List<WishList> findAll();

	<S extends WishList> S save(S entity);
	
	//List<WishList> findByUsername(String username);

	WishList findBy(int productId, Long accountId);

	Object findByUsername1(String username);
	
	Object findByUsername(String username, Pageable pageable);
	
	List<WishList> findByUserId(Long id);

	int deleteByIdSp(Integer id, Long idac);
	

}
