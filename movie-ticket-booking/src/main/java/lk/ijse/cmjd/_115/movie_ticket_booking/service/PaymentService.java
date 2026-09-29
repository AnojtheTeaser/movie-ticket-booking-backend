package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.PaymentDTO;

import java.util.List;

public interface PaymentService {
    PaymentDTO processPayment(PaymentDTO paymentDTO);
    PaymentDTO getPaymentById(Long id);
    PaymentDTO getPaymentByBookingId(Long bookingId);
    List<PaymentDTO> getAllPayments();
}