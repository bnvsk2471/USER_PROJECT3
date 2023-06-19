package com.Security.Info.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendEmail(String to, String Subject, String body) {
		boolean isSent=false;
		
		/*We have two types of messages 
		 * 			Simple messages 							Mime Messages
		 * 1. We cannot send any attachments			1. We can send any attachments
		 * 2. Format email is not possible				2. Format email is possible
		 * 													We can style our email as per our requirement
		 */
		
		try {
			MimeMessage mimemsg= mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimemsg);
			helper.setTo(to);
			helper.setSubject(Subject);
			helper.setText(body,true);
			
			mailSender.send(mimemsg);
			isSent=true;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return isSent;
	}

}
