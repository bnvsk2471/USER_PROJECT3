package com.Security.Info.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.Info.Entity.CountryMaster;


@Repository
public interface CountryMasterRepo extends JpaRepository<CountryMaster, Serializable> {

}
