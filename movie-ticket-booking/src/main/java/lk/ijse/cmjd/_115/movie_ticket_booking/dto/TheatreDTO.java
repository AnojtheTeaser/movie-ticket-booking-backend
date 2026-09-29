package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TheatreDTO {
    private Long theatreId;
    private String name;
    private String city;
    private String address;
    private Integer totalSeats;
}