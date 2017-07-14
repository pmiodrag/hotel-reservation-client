package com.twinsoft.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import com.twinsoft.dto.HotelReservationEventMessage;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Miodrag Pavkovic
 */
@Service
@Slf4j
public class HotelReservationListener {

    @RabbitListener(queues  = "#{@hotelreservationQueue}")
    public void receiveMessage(final HotelReservationEventMessage message) {
        log.info("Received message for hotel reservation {}, for hotel {}", message.getEventType(), message.getHotelName());
    }
}