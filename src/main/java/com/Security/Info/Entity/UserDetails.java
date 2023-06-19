package com.Security.Info.Entity;


import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="USER_MASTER")
public class UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	private String fName;
	private String lName;
	private String Email;
	private Double pNumber;
	private LocalDate dob;
	private String gender;
	
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
	
	private String userPassword;
	private String accStatus; 
	
	

}
