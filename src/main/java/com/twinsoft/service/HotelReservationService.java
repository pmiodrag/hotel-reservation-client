package com.twinsoft.service;

import org.springframework.http.ResponseEntity;

import com.twinsoft.dto.HotelReservation;


/**
 * The hotel reservation service.
 * 
 * @author Miodrag Pavkovic
 */
public interface HotelReservationService {
	/**
	 * Find specific hotel.
	 * 
	 * @param id to find the hotel with requested id
	 * @return hotel entity with requested id
	 */
	//HotelReservation findByHotelReservationId(Long id);
	/**
	 * Retrieves the list of all hotel reservations.
	 * @return list of hotel reservations
	 */
	 ResponseEntity<HotelReservation[]>  findAll(String token);
	
	/**
	 * Create and save new hotel reservation.
	 * @param token 
	 * @return HotelReservation entity
	 */
	HotelReservation save(HotelReservation hotel, String token);
	
	/**
	 * Delete hotel reservation entity.
	 * @param id to delete hotel reservation entity with requested id
	 */
	void delete(Long id);

	/**
	 * Update hotel reservation.
	 * @return HotelReservation entity
	 */
	HotelReservation update(HotelReservation hotelReservation);
}
