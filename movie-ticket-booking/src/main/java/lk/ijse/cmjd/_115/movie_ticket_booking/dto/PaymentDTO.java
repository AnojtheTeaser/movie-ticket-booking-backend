package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.PaymentMethod;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Long paymentId;
    private Long bookingId;
    private Double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;
    private LocalDateTime transactionTime;
}