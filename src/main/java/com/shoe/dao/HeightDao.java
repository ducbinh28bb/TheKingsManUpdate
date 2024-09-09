package com.shoe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.entity.Height;

@Repository
public interface HeightDao extends JpaRepository<Height, Integer>{
	@Query(nativeQuery = true, value = "SELECT TOP 4 * FROM Height o ORDER BY o.id ASC")
	List<Height> findTop4Img();
	
	@Query("SELECT o FROM Height o WHERE o.name LIKE ?1")
	Height findByName(String name);
	

	@Query("SELECT o FROM Height o WHERE o.status = true")
	List<Height> getAllStatus();

		@Query("SELECT o FROM Height o WHERE o.name LIKE ?1 and status = ?2")
	List<Height> findByName(String name, Boolean status);
	
	@Query("SELECT o FROM Height o WHERE o.status = ?1")
	List<Height> findByStatus(Boolean status);

	@Query("SELECT o FROM Height o WHERE o.name LIKE ?1")
	List<Height> findByName1(String name);
}
