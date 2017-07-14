package com.twinsoft;

import java.text.SimpleDateFormat;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;


/**
 * RabbitMQ configuration class
 *
 * @author Miodrag Pavkovic
 */
@Configuration
public class RabbitMqConfig implements RabbitListenerConfigurer{

	public static final String TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";
	
	@Value("${hotelclient.amqp.queue}")
	private String hotelreservationQueue;

	@Bean
	public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
	   return new MappingJackson2MessageConverter();
	}
	 
	@Bean
	public DefaultMessageHandlerMethodFactory messageHandlerMethodFactory() {
	   DefaultMessageHandlerMethodFactory factory = new DefaultMessageHandlerMethodFactory();
	   factory.setMessageConverter(consumerJackson2MessageConverter());
	   return factory;
	}
	
	@Bean(name = "hotelreservationQueue")
	public Queue hotelreservationQueue() {
		return new Queue(hotelreservationQueue);
	}
	 
	@Override
	public void configureRabbitListeners(final RabbitListenerEndpointRegistrar registrar) {
	   registrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
	}
	
    @Bean(name = "eventMessageConverter")
    public MessageConverter messageConverter() {
        final Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        // Jackson deserialization point issue
        final ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        jsonObjectMapper.registerModule(new JavaTimeModule());
        jsonObjectMapper.setDateFormat(new SimpleDateFormat(TIMESTAMP_FORMAT));
        converter.setJsonObjectMapper(jsonObjectMapper);
        return converter;
    }
}
