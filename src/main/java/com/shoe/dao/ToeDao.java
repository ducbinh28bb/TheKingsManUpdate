package com.shoe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.entity.Toe;

@Repository
public interface ToeDao extends JpaRepository<Toe, Integer>{
	@Query(nativeQuery = true, value = "SELECT TOP 4 * FROM Toe o ORDER BY o.id ASC")
	List<Toe> findTop4Img();
	
	@Query("SELECT o FROM Toe o WHERE o.name LIKE ?1")
	Toe findByName(String name);
	

	@Query("SELECT o FROM Toe o WHERE o.status = true")
	List<Toe> getAllStatus();

		@Query("SELECT o FROM Toe o WHERE o.name LIKE ?1 and status = ?2")
	List<Toe> findByName(String name, Boolean status);
	
	@Query("SELECT o FROM Toe o WHERE o.status = ?1")
	List<Toe> findByStatus(Boolean status);

	@Query("SELECT o FROM Toe o WHERE o.name LIKE ?1")
	List<Toe> findByName1(String name);
}
