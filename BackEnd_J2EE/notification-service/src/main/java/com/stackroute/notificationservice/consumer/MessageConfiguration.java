package com.stackroute.notificationservice.consumer;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class MessageConfiguration {

	private String exchangeName="ticket_exchange";
	private String registerQueue="te_queue";
	private String registerQueue2="tes_queue";
	private String registerQueue3="be_queue";
	private String registerQueue4="bes_queue";

	


	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter()
	{
		return new Jackson2JsonMessageConverter();
	}

	@Bean
	public DirectExchange directExchange()
	{
		return new DirectExchange(exchangeName);
	}

	@Bean
	public Queue registerQueue()
	{
		return new Queue(registerQueue,false);
	}

	@Bean
	public Queue registerQueue2()
	{
		return new Queue(registerQueue2,false);
	}
	
	@Bean
	public Queue registerQueue3()
	{
		return new Queue(registerQueue3,false);
	}

	@Bean
	public Queue registerQueue4()
	{
		return new Queue(registerQueue4,false);
	}

	@Bean
	Binding bindingTicket(Queue registerQueue, DirectExchange exchange)
	{
		return BindingBuilder.bind(registerQueue()).to(exchange).with("te_routing");
	}

	@Bean
	Binding bindingTicket2(Queue registerQueue2, DirectExchange exchange)
	{
		return BindingBuilder.bind(registerQueue2()).to(exchange).with("tes_routing");
	}
	
	@Bean
	Binding bindingBooking(Queue registerQueue3, DirectExchange exchange)
	{
		return BindingBuilder.bind(registerQueue3()).to(exchange).with("be_routing");
	}

	@Bean
	Binding bindingBooking2(Queue registerQueue4, DirectExchange exchange)
	{
		return BindingBuilder.bind(registerQueue4()).to(exchange).with("bes_routing");
	}

}


