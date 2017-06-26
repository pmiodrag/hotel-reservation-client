/**
 * 
 */
package com.twinsoft.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.twinsoft.dto.HotelReservation;



/**
 * Implementation of hotel reservation service.
 * 
 * @author Miodrag Pavkovic
 */
@Service
public class HotelReservationServiceImpl implements HotelReservationService {
	
	@Value("${hotelserver.url}")
	private String SERVER_URL;

	private final RestTemplate restTemplate;
	
	/**
	 * @param hotelReservationRepository
	 */
	@Inject
	public HotelReservationServiceImpl() {
		restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());
	}	

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelReservationService#findByHoteReservationlId(java.lang.Long)
	 */
/*	@Override
	public HotelReservation findByHotelReservationId(Long id) {
		return this.restTemplate.getForObject("/{id}/details", HotelReservation.class);
	}*/

	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelReservationService#findAll()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<HotelReservation> findAll() {
		final HotelReservation[] reservations = (HotelReservation[]) this.restTemplate.getForObject(SERVER_URL+"/hotelreservations", HotelReservation[].class);
		return Arrays.asList(reservations);
	}

	
	@Override
	public HotelReservation save(final HotelReservation hotelReservation) {
		return this.restTemplate.postForObject(SERVER_URL+"/hotelreservations", hotelReservation, HotelReservation.class);
	}

	
	/* (non-Javadoc)
	 * @see com.twinsoft.service.HotelReservationService#delete(java.lang.Long)
	 */
	@Override
	public void delete(final Long id) {
		//hotelReservationRepository.delete(id);		
	}
	

	
}
