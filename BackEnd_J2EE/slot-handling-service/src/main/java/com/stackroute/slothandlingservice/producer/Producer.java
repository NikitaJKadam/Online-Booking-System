package com.stackroute.slothandlingservice.producer;

import com.stackroute.slothandlingservice.model.UserBookingDetails;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Producer {
    private RabbitTemplate rabbitTemplate;
    private DirectExchange exchange;
    @Autowired
    public Producer(RabbitTemplate rabbitTemplate, DirectExchange exchange) {
        super();
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
    }
    public void sendMessageToRabbitMq(UserBookingDetails userBookingDetails)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"be_routing",userBookingDetails);
    }
    public void sendMessageToRabbitMqTe(UserBookingDetails userBookingDetails)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"bes_routing",userBookingDetails);
    }

}
