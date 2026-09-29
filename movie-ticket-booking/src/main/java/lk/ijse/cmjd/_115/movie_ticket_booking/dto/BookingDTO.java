package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long bookingId;
    private Long userId;
    private Long showId;
    private LocalDateTime bookingTime;
    private Double totalAmount;
    private BookingStatus status;
    private List<String> seatNumbers;
}