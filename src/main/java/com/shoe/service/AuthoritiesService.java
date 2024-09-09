package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.entity.Accounts;
import com.shoe.entity.Authorities;

public interface AuthoritiesService {

	Authorities getById(Long id);

	void delete(Authorities entity);

	void deleteById(Long id);

	Optional<Authorities> findById(Long id);

	List<Authorities> findAll();

	 Authorities save(Authorities entity);
	
	public List<Authorities> findAuthoritiesOfAdminstrators();

	Accounts findAcountAuthority(Long idAcc);

	int findAcountAuthorityCheck(Long idAcc);

}
