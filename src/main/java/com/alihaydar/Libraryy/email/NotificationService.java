package com.alihaydar.Libraryy.email;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.alihaydar.Libraryy.model.User;

@Service
public class NotificationService {
	@Autowired
	private static JavaMailSender javaMailSender;

	public static void sendEmail(User userModel) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		String link = "<a href=\"http://localhost:8080/api/user/active/\">CLICK HERE TO ACTIVATE YOUR ACCOUNT</a>";
		helper.setTo(userModel.getEmail());
		helper.setText("Your Activation Code Is : " + userModel.getActivationKey() + "<br>" + link, true);
		helper.setSubject("HELLO " + userModel.getUsername());

		javaMailSender.send(message);
	}
}
