package com.Security.Info.ServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
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
import com.Security.Info.util.EmailUtils;
import com.Security.Service.UserManagementService;


@Service
public class UserManagementServiceImpl implements UserManagementService {
	
	@Autowired
	private UserDetailsRepo userrepo;
	
	@Autowired
	private CountryMaster countryMaster;
	
	@Autowired
	private CountryMasterRepo countrymasterrRepo;
	
	@Autowired
	private StateMasterRepo statemasterRepo;
	
	@Autowired
	private CitiesMasterRepo citiesmasterRepo;
	
	@Autowired
	private EmailUtils emailutils; 

	@Override
	public String checkEmail(String email) {
		
		UserDetails user= userrepo.findbyEmail(email);
		if(user.getEmail().isEmpty()) {
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
		
		//copy data from binding object to data object
		UserDetails user=new UserDetails();
		BeanUtils.copyProperties(userform, user);
		
		//Generate & Set random password
		user.setUserPassword(generateRandomPassword());
	
		//Set Account status as locked
		user.setAccStatus("LOCKED");
		userrepo.save(user);

		//TODO: Set Email to unlock account
		
		String to=user.getEmail();
		String subject="Registration Email";
		String body="";
		
		emailutils.sendEmail(to, subject, body);
				
		return "USER ACCOUNT CREATED";
	}

	@Override
	public String unlockAccount(unlockAccountForm accForm) {
		String email=accForm.getEmail();
		UserDetails user= userrepo.findbyEmail(email);
		if(user.getUserPassword().equals(accForm.getTempPassword())) {
			user.setUserPassword(accForm.getChooseNewPassword());
			user.setAccStatus("UNLOCKED");
			userrepo.save(user);
			return "ACCOUNT UNLOCKED";
		}
		
		return "INVALID TEMPORARY PASSWORD";
	}

	@Override
	public String login(LoginForm loginForm) {
		UserDetails user=userrepo.findbyEmailanduserPassword(loginForm.getEmail(), loginForm.getPassword());
		
		if(user==null) {
			return "INVALID CREDENTIALS";
		}
		if(user.getAccStatus().equals("LOCKED")) {
			return "ACCOUNT LOCKED";
		}
		return "SUCCESS";
	}

	@Override
	public String forgotPwd(String email) {
		UserDetails user=userrepo.findbyEmail(email);
		if(user==null) {
			return "No ACCOUNT FOUND";
		}
		if(user!=null) {
			//TODO: We need to send email;
			
		}
		
		return null;
	}
	
	private String generateRandomPassword() {
		String text="ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder builder=new StringBuilder();
		Random random=new Random();
		int pwdLength=6;
		for(int i=1;i<=pwdLength;i++) {
			int index=random.nextInt(text.length());
			builder.append(text.charAt(index));
		}
		
		return builder.toString();
	}

}
