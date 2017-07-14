package com.twinsoft.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * HotelRoomType entity.
 * 
 * @author Miodrag Pavkovic
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelRoomType implements Serializable {

	private static final long serialVersionUID = 1038141457256654585L;
	
	/** The room id. */
    private Long id;
    
    /** The hotel. */
    private Hotel hotel;
    
    /** The room type. */
    private RoomType roomType;
    
    /** The room price. */
    private BigDecimal price;
    
}
