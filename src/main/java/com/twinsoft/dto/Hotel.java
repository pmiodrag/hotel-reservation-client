package com.twinsoft.dto;

import java.io.Serializable;
import java.util.List;


import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author miodrag
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {
	
private static final long serialVersionUID = 8690390389555199353L;
    
    /** The hotel name. */
       
    private String name;
    
    /** Number of rooms **/
    private Integer totalRooms;
    
    /** Hotel rating - stars **/
    private HotelRating rating;
    
    /** The hotel room types. */
    private List<HotelRoomType> hotelRoomTypes;

}
