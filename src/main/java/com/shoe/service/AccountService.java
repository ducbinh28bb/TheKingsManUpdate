package com.shoe.service;

import java.util.List;
import java.util.Optional;

import com.shoe.dto.UserDto;
import com.shoe.entity.Accounts;
import com.shoe.service.impl.CustomerNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface AccountService {

	void delete(Accounts entity);

	List<Accounts> findAll();

	void loginFormOAuth2(OAuth2AuthenticationToken oauth2);

	void updatePassword(Accounts accounts, String newPassword);

	Accounts getByResetPasswordToken(String token);

	Object findByUsername1(String username);

	Accounts register(UserDto userDto);

	Boolean existsByEmail(String email);

	Boolean existsByUsername(String username);

	Accounts save(Accounts accounts);

	Optional<Accounts> findByEmail(String email);

	Optional<Accounts> findByUsername(String username);

	void updateResetPasswordToken(String token, String email) throws CustomerNotFoundException;

	Accounts getById(Long id);


	Accounts findUserByUsername(String username);

	List<Accounts> getAdminstrators();

	 List<Accounts> findByName1(String string);

	  	List<Accounts> findByName(String username,Boolean status);

	  	List<Accounts> findByStatus(Boolean status);

		List<Accounts> findAllCustomer();

		List<Accounts> findAllAccout();

		int getTrungEmail(String email);

		int getTrungPhone(String phone);

		List<Accounts> getAdminstratorsC();

		List<Accounts> findByName1C(String string);

		List<Accounts> findByNameC(String string, boolean in);

		List<Accounts> findByStatusC(Boolean status);
}
