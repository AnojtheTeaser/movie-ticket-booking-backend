package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.ShowStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShowDTO {
    private Long id;
    private Long movieId;
    private Long theatreId;
    private LocalDate showDate;
    private LocalTime showTime;
    private Double ticketPrice;
    private ShowStatus status;
}