package com.Security.Info.Repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Security.Info.Entity.UserDetails;


@Repository
public interface UserDetailsRepo extends JpaRepository<UserDetails, Serializable> {

	public UserDetails findbyEmail(String email);
	public UserDetails findbyEmailanduserPassword(String email, String password);
}
