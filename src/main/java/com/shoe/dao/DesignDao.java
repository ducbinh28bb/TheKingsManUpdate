package com.shoe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.entity.Design;

@Repository
public interface DesignDao extends JpaRepository<Design, Integer>{
	@Query(nativeQuery = true, value = "SELECT TOP 4 * FROM Design o ORDER BY o.id ASC")
	List<Design> findTop4Img();
	
	@Query("SELECT o FROM Design o WHERE o.name LIKE ?1")
	Design findByName(String name);
	

	@Query("SELECT o FROM Design o WHERE o.status = true")
	List<Design> getAllStatus();

		@Query("SELECT o FROM Design o WHERE o.name LIKE ?1 and status = ?2")
	List<Design> findByName(String name, Boolean status);
	
	@Query("SELECT o FROM Design o WHERE o.status = ?1")
	List<Design> findByStatus(Boolean status);

	@Query("SELECT o FROM Design o WHERE o.name LIKE ?1")
	List<Design> findByName1(String name);
}
