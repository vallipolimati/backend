package com.tritern.evozspecial.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tritern.evozspecial.entity.EvozEntity;

@Repository
public interface EvozRepository extends JpaRepository<EvozEntity, Long> {

	public boolean existsByEmail(String email);

	@Query(value = "SELECT * FROM evoztable WHERE emailid=?1", nativeQuery = true)
	EvozEntity findByEmail(String email);

	@Query(value = "SELECT * FROM evoztable WHERE name=?1", nativeQuery = true)
	EvozEntity findByName(String name);
	

	@Query(value = "SELECT * FROM evoztable WHERE name LIKE %?1%" + " OR username LIKE %?1%"
			+ " OR emailid LIKE %?1%", nativeQuery = true)
	public List<EvozEntity> searchUsers(String keyword, Pageable pageable);

	@Query(value = "SELECT * FROM evoztable WHERE name LIKE %?1%" + " OR username LIKE %?1%"
			+ " OR emailid LIKE %?1%", nativeQuery = true)
	public List<EvozEntity> searchLength(String keyword);

}
