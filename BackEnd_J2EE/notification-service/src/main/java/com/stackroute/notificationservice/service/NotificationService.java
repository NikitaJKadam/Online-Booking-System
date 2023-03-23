package com.stackroute.notificationservice.service;

import com.stackroute.notificationservice.model.Notification;

public interface NotificationService {
	
    String sendSimpleMail(Notification notification);


}
