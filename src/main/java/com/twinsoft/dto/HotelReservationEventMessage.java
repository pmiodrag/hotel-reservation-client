package com.twinsoft.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HotelReservationEventMessage {

	private final String hotelName;
	private final RoomType roomType;
	private final LocalDate startDate;
	private final LocalDate endDate;
	private final EventType eventType;
	
}
