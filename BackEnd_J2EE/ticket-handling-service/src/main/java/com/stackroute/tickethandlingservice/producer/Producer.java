package com.stackroute.tickethandlingservice.producer;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.stackroute.tickethandlingservice.model.TicketHandling;

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

    public void sendMessageToRabbitMq(TicketHandling handling)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"ticket_routing",handling);
    }

    public void sendMessageToRabbitMqTe(TicketHandling handling)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"te_routing",handling);

    }
    
    public void sendMessageToRabbitMqTes(TicketHandling handling)
    {
        rabbitTemplate.convertAndSend(exchange.getName(),"tes_routing",handling);

    }
    
}
