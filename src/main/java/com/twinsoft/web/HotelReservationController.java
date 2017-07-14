/**
 * 
 */
package com.twinsoft.web;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.twinsoft.dto.HotelReservation;
import com.twinsoft.service.HotelReservationService;

import lombok.extern.slf4j.Slf4j;
/**
 * Rest controller for exposing hotel reservation endpoints.
 *
 * @author Miodrag Pavkovic
 */
@Slf4j
@RestController
@RequestMapping("hotelreservations")
public class HotelReservationController { 
	private static final String RESOURCE_NOT_FOUND_MESSAGE = null;
	/** The hotel service */
	private final HotelReservationService hotelReservationService;

	@Inject
	public HotelReservationController(final HotelReservationService hotelReservationService) {
		this.hotelReservationService = hotelReservationService;
	}
	

	/**
	 * Rest endpoint for retrieving all hotel reservations.
	 *
	 * @return ResponseEntity<Page<Hotel>>
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<HotelReservation[]> findAll(@RequestHeader(value="Authorization") String token) {
		return hotelReservationService.findAll(token);
	}
	
	/**
	 * Rest endpoint for creating and saving hotel reservation.
	 * 
	 * @param hotelReservation
	 * @param builder
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void create(@Valid @RequestBody final HotelReservation hotelReservation, final UriComponentsBuilder builder, @RequestHeader(value="Authorization") String token) {		
		try {	
			hotelReservationService.save(hotelReservation, token);
		} catch (final Exception e) {
			log.error("Hotel reservation not created hotel, Cause:",e);
		}
	}
	

	/**
	 * Rest endpoint for updating a hotel reservation with reservation id.
	 *
	 * @param hotelReservationId
	 * @param hotelReservation
	 * @return
	 */
	@PutMapping(value="/{hotelReservationId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void update(@PathVariable final Long hotelReservationId, @Valid @RequestBody final HotelReservation hotelReservation) {		
		try {
			hotelReservation.setId(hotelReservationId);
			final HotelReservation updateHotelReservation  = hotelReservationService.update(hotelReservation);
		} catch (final Exception e) {
			log.error("Exception occurred while updating hotel reservation with id {}. Cause: ", hotelReservationId, e);
		}
	}
	/**
	 * Rest endpoint for deleting a hotel reservation with reservation id.
	 *
	 * @param hotelReservationId
	 */
	@DeleteMapping(value = "/{hotelReservationId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("hotelReservationId") final Long hotelReservationId) {
		try {
			hotelReservationService.delete(hotelReservationId);
		} catch (final Exception e) {
			log.error("Exception occurred while deleting hotel reservation with reservation id {}. Cause: ", hotelReservationId, e);
		}
	}
}
