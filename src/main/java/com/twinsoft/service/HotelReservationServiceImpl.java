/**
 * 
 */
package com.twinsoft.service;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

	@SuppressWarnings("unchecked")
	@Override
	public List<HotelReservation> findAll() {
		final HotelReservation[] reservations = (HotelReservation[]) this.restTemplate.getForObject(SERVER_URL+"/hotelreservations", HotelReservation[].class);
		return Arrays.asList(reservations);
	}

	
	@Override
	public HotelReservation save(final HotelReservation hotelReservation, final String token) {
		HttpHeaders headers = new HttpHeaders();
		 
		//HttpAuthentication httpAuthentication = new HttpBasicAuthentication("username", "password");
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.set("Authorization", token);
		HttpEntity<HotelReservation> entity = new HttpEntity<HotelReservation>(hotelReservation, headers);
		return this.restTemplate.postForObject(SERVER_URL+"/hotelreservations", entity, HotelReservation.class);
	}

	@Override
	public void delete(final Long id) {
		this.restTemplate.delete(SERVER_URL+"/hotelreservations/"+id);	
	}

	@Override
	public HotelReservation update(final HotelReservation hotelReservation) {
		this.restTemplate.put(SERVER_URL+"/hotelreservations/"+hotelReservation.getId(), hotelReservation);
		return hotelReservation;
	}
	

	
}
