package com.stackroute.authenticationservice.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfiguration {
    private String exchangeName = "user_exchange";
    private String registerQueue = "user_queue";
    private String registerQueue2 = "user_queue2";


    @Bean
    public Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean
    public Queue registerQueue() {
        return new Queue(registerQueue, false);
    }

    @Bean
    public Queue registerQueue2() {
        return new Queue(registerQueue2, false);
    }

    @Bean
    Binding bindingUser(Queue registerQueue, DirectExchange exchange) {
        return BindingBuilder.bind(registerQueue()).to(exchange).with("user_routing");
    }

    @Bean
    Binding bindingUser2(Queue registerQueue2, DirectExchange exchange) {
        return BindingBuilder.bind(registerQueue2()).to(exchange).with("user_passwordrouting");
    }
}
