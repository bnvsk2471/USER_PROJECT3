package com.Security.Info.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.Info.Entity.CitiesMaster;


@Repository
public interface CitiesMasterRepo extends JpaRepository<CitiesMaster, Serializable> {
	public List<CitiesMaster> findBystateId(Integer stateid);
}
