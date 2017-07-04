package com.twinsoft.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Miodrag Pavkovic
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelReservation implements Serializable {

	private static final long serialVersionUID = 2117514065166400017L;
	
	private Long id;

	private Hotel hotel;
    
    /** Hotel room type. */
    private RoomType roomType;
    
    /** Reservation start date */
    private LocalDate startDate;
    
    /** Reservation end date */
    private LocalDate endDate;
    
    /** Reservation price calculated by date of reservation. */
    private BigDecimal reservationPrice;
    
}
