package com.Security.Info.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.Info.Entity.StateMaster;




@Repository
public interface StateMasterRepo extends JpaRepository<StateMaster, Serializable> {
	
	public List<StateMaster> findBycountryId(Integer countryId);

}
