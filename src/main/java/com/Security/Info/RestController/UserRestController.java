package com.Security.Info.RestController;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Security.Info.Binding.LoginForm;
import com.Security.Info.Binding.UserForm;
import com.Security.Info.Binding.unlockAccountForm;
import com.Security.Service.UserManagementService;

@RestController
@RequestMapping("/UserManagement")
public class UserRestController {
	
	
	@Autowired
	UserManagementService userservice;
	
	
	@PostMapping("/Login")
	public ResponseEntity<String> Login(
			@RequestBody LoginForm login
			){
		String response=userservice.login(login);
		return new ResponseEntity<String>(response,HttpStatus.OK);
	}
	
	
	@GetMapping("/countries")
	public Map<Integer, String> loadCountries(){
		return userservice.getCountries();
	}
	
	
	@GetMapping("/states/{id}")
	public Map<Integer, String> loadStates(
			@PathVariable("id") Integer Countryid
			){
		return userservice.getStates(Countryid);
	}
	
	
	@GetMapping("/cities/{id}")
	public Map<Integer, String> loadCities(
			@PathVariable("id") Integer Stateid
			){
		return userservice.getCities(Stateid);
	}
	
	
	@GetMapping("/email")
	public String emailCheck(
			@PathVariable String Email
			) {
		return userservice.checkEmail(Email);
	}
	
	
	@PostMapping("/userReg")
	public ResponseEntity<String> userRegistration(
			@RequestBody UserForm userform
			){
		String Status=userservice.registerUser(userform);
		return new ResponseEntity<String>(Status, HttpStatus.CREATED);
	}
	
	
	@PostMapping("/UnlockAccount")
	public ResponseEntity<String> unlockAccount(
			@RequestBody unlockAccountForm unlockacc
			){
		String status=userservice.unlockAccount(unlockacc);
	return new ResponseEntity<String>(status, HttpStatus.OK);
	}
	
	@GetMapping("/forgotpassword/{email}")
	public ResponseEntity<String> forgotPassword(
			@PathVariable("email") String email
			){
		String status=userservice.forgotPwd(email);
		return new ResponseEntity<String>(status,HttpStatus.OK);
		
	}
	

}
