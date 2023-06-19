package com.Security.Service;

import java.util.Map;

import com.Security.Info.Binding.LoginForm;
import com.Security.Info.Binding.UserForm;
import com.Security.Info.Binding.unlockAccountForm;

public interface UserManagementService {


		public String checkEmail(String email);

		public Map<Integer, String> getCountries ( ) ;

		public Map<Integer, String> getStates (Integer countryId);

		public Map<Integer, String> getCities (Integer stateId);

		public String registerUser (UserForm userform);

		public String unlockAccount (unlockAccountForm accForm);

		public String login (LoginForm loginForm);

		public String forgotPwd (String email);
		

}
