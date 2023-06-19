package com.Security.Info.Binding;

import lombok.Data;

@Data
public class unlockAccountForm {
	
	private String email;
	private String tempPassword;
	private String chooseNewPassword;
	private String confirmPassword;
}
