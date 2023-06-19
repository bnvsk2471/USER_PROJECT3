package com.Security.Info.Binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserForm {
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
