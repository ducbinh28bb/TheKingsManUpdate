package com.shoe.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.shoe.entity.Material;

@Repository
public interface MaterialDao extends JpaRepository<Material, Integer>{
	@Query(nativeQuery = true, value = "SELECT TOP 4 * FROM Material o ORDER BY o.id ASC")
	List<Material> findTop4Img();
	
	@Query("SELECT o FROM Material o WHERE o.name LIKE ?1")
	Material findByName(String name);
	

	@Query("SELECT o FROM Material o WHERE o.status = true")
	List<Material> getAllStatus();

		@Query("SELECT o FROM Material o WHERE o.name LIKE ?1 and status = ?2")
	List<Material> findByName(String name, Boolean status);
	
	@Query("SELECT o FROM Material o WHERE o.status = ?1")
	List<Material> findByStatus(Boolean status);

	@Query("SELECT o FROM Material o WHERE o.name LIKE ?1")
	List<Material> findByName1(String name);
}
