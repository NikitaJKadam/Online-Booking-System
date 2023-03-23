package com.stackroute.notificationservice.consumer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.notificationservice.bookingdto.BookingDto;
import com.stackroute.notificationservice.model.Notification;
import com.stackroute.notificationservice.service.serviceimpl.NotificationServiceImpl;
import com.stackroute.notificationservice.tickethandlingdto.TicketHandlingDto;
@Component
public class Consumer {
   @Autowired
   NotificationServiceImpl impl;
   @RabbitListener(queues = "te_queue")
   public void getTicketdetails(TicketHandlingDto dto)
   {
      Notification notification=new Notification();
      notification.setToUser(dto.getUserEmail());
      notification.setToCenter(dto.getCenterEmail());
      notification.setSubject("Ticket Created");
      String msg="Hello, \n\nThank you, for raising the ticket (";
      notification.setMsgBody(msg+dto.getTicketId()+").\n\nThe details of your following ticket are:-\n\n>Ticket Description:- "+dto.getTicketDesceription()+"\n>Model Name:- "+dto.getModelName()+"\n>ProductCategorey:- "+dto.getProductCategory()+"\n\nThanks & Regards,\nSwiftQ Solution");
      impl.sendSimpleMail(notification);
   }
   @RabbitListener(queues = "tes_queue")
   public void getTicketdetailsClosure(TicketHandlingDto dto)
   {
      Notification notification=new Notification();
      notification.setToUser(dto.getUserEmail());
      notification.setToCenter(dto.getCenterEmail());
      notification.setSubject("Ticket Closed");
      notification.setMsgBody("Hello,\n\nThank you for using our service, your ticket ("+dto.getTicketId()+") has been closed.\n\nThanks & Regards,\nSwiftQ Solution");
      impl.sendSimpleMail(notification);
   }
   @RabbitListener(queues = "be_queue")
   public void getBookingdetails(BookingDto dto)
   {
      Notification notification=new Notification();
      notification.setToUser(dto.getUserEmail());
      notification.setToCenter(dto.getServiceEmail());
      notification.setSubject("Slot Booked");
      notification.setMsgBody("Hello,\n\nThank you for booking our service with bookingID ("+dto.getBookingId()+").\n\nThe Description for your booking is :-\n\t>Issue Description: "+dto.getUserIssueDescription()+"\n\t>Model Name: "+dto.getModelName()
              +"\n\t>Product Categorey: "+dto.getProductCategory()+"\n\t>Date: "+dto.getSlotDate()+"\n\t>Time: "+dto.getSlotStartTime()+"-"+dto.getSlotEndTime()+"\n\nThanks & Regards,\nSwiftQ Solution");
      impl.sendSimpleMail(notification);
   }
   @RabbitListener(queues = "bes_queue")
   public void getBookingdetailsClosure(BookingDto dto)
   {
      Notification notification=new Notification();
      notification.setToUser(dto.getUserEmail());
      notification.setToCenter(dto.getServiceEmail());
      notification.setSubject("Booking Closed");
      notification.setMsgBody("Hello,\n\nThank you for using our service, your booked slot ("+dto.getBookingId()+") has been closed.\nThe final price for booked slot is:-"+dto.getPrice()+"\n\nThanks & Regards,\nSwiftQ Solution");
      impl.sendSimpleMail(notification);
   }
}