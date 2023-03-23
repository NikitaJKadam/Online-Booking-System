package com.stackroute.notificationservice.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.stackroute.notificationservice.model.Notification;
import com.stackroute.notificationservice.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService{

	@Autowired 
	private JavaMailSender javaMailSender;

	@Value("${spring.mail.username}") 
	private String sender;

	public String sendSimpleMail(Notification notification)
	{

		// Try block to check for exceptions
		try {

			// Creating a simple mail message
			SimpleMailMessage mailMessage
			= new SimpleMailMessage();

			// Setting up necessary details
			mailMessage.setFrom(sender);
			mailMessage.setTo(notification.getToUser());
			mailMessage.setCc(notification.getToCenter());
			mailMessage.setText(notification.getMsgBody());
			mailMessage.setSubject(notification.getSubject());

			// Sending the mail
			javaMailSender.send(mailMessage);
			return "Mail Sent Successfully...";
		}

		// Catch block to handle the exceptions
		catch (Exception e) {
			return "Error while Sending Mail";
		}
	}

}
