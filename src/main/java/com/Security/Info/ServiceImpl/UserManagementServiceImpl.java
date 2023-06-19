package com.Security.Info.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Security.Info.Binding.LoginForm;
import com.Security.Info.Binding.UserForm;
import com.Security.Info.Binding.unlockAccountForm;
import com.Security.Info.Entity.CitiesMaster;
import com.Security.Info.Entity.CountryMaster;
import com.Security.Info.Entity.StateMaster;
import com.Security.Info.Entity.UserDetails;
import com.Security.Info.Repository.CitiesMasterRepo;
import com.Security.Info.Repository.CountryMasterRepo;
import com.Security.Info.Repository.StateMasterRepo;
import com.Security.Info.Repository.UserDetailsRepo;
import com.Security.Service.UserManagementService;


@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	UserDetailsRepo userrepo;
	
	@Autowired
	CountryMaster countryMaster;
	
	@Autowired
	CountryMasterRepo countrymasterrRepo;
	
	@Autowired
	StateMasterRepo statemasterRepo;
	
	@Autowired
	UserDetails userdetails;
	
	@Autowired
	CitiesMasterRepo citiesmasterRepo;

	@Override
	public String checkEmail(String email) {
		
		String Email= userrepo.findbyEmail(email);
		if(Email.isEmpty()) {
			return "UNIQUE";
		}
		return "NOT UNIQUE";
	}

	@Override
	public Map<Integer, String> getCountries() {
		List<CountryMaster> countries= countrymasterrRepo.findAll();
		return countries.stream()
				.collect(Collectors.toMap(CountryMaster::getCountryId, CountryMaster::getCountryName));
	}

	@Override
	public Map<Integer, String> getStates(Integer countryId) {
		List<StateMaster> states=statemasterRepo.findBycountryId(countryId);
		return states.stream()
				.collect(Collectors.toMap(StateMaster::getStateId, StateMaster::getStateName));
	}

	@Override
	public Map<Integer, String> getCities(Integer stateId) {
		
		List<CitiesMaster> city=citiesmasterRepo.findBystateId(stateId);
		Map<Integer, String> cities= new HashMap<>();
		city.forEach(citys->
			cities.put(countryMaster.getCountryId(), countryMaster.getCountryName()));
		return cities;
	}

	@Override
	public String registerUser(UserForm userform) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String unlockAccount(unlockAccountForm accForm) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String login(LoginForm loginForm) {
		
		Optional<String> email= Optional.ofNullable(userrepo.findbyEmail(loginForm.getEmail()));
		if(email.isPresent()) {
			String Password=userdetails.getUserPassword();
			if(loginForm.getPassword().equals(Password)) {
				return "LoggedIn";
			}else {
				return "Incorrect Password";
			}
		}
		if(!email.isPresent()) {
			return "Email is Incorrect";
		}
		return "InCorrect Credientials";
	}

	@Override
	public String forgotPwd(String email) {
		// TODO Auto-generated method stub
		return null;
	}


}
