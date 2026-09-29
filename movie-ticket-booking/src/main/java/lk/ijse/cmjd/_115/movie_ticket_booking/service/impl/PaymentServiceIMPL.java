package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.BookingDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dao.PaymentDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.PaymentDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.PaymentStatus;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.BookingEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.PaymentEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentServiceIMPL implements PaymentService {

    @Autowired
    private PaymentDAO paymentDAO;

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaymentDTO processPayment(PaymentDTO paymentDTO) {
        BookingEntity bookingEntity = bookingDAO.findById(paymentDTO.getBookingId())
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + paymentDTO.getBookingId()));

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setBooking(bookingEntity);
        paymentEntity.setAmount(paymentDTO.getAmount());
        paymentEntity.setPaymentMethod(paymentDTO.getPaymentMethod());
        paymentEntity.setStatus(PaymentStatus.COMPLETED);
        paymentEntity.setTransactionTime(LocalDateTime.now());

        PaymentEntity savedPayment = paymentDAO.save(paymentEntity);

        PaymentDTO responseDTO = modelMapper.map(savedPayment, PaymentDTO.class);
        responseDTO.setBookingId(savedPayment.getBooking().getId());
        return responseDTO;
    }

    @Override
    public PaymentDTO getPaymentById(Long id) {
        PaymentEntity paymentEntity = paymentDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + id));
        PaymentDTO dto = modelMapper.map(paymentEntity, PaymentDTO.class);
        dto.setBookingId(paymentEntity.getBooking().getId());
        return dto;
    }

    @Override
    public PaymentDTO getPaymentByBookingId(Long bookingId) {
        List<PaymentEntity> payments = paymentDAO.findByBooking_Id(bookingId);

        if (payments.isEmpty()) {
            throw new RuntimeException("Payment not found for booking id: " + bookingId);
        }

        PaymentEntity paymentEntity = payments.get(0);
        PaymentDTO dto = modelMapper.map(paymentEntity, PaymentDTO.class);
        dto.setBookingId(paymentEntity.getBooking().getId());
        return dto;
    }
    @Override
    public List<PaymentDTO> getAllPayments() {
        return paymentDAO.findAll().stream().map(payment -> {
            PaymentDTO dto = modelMapper.map(payment, PaymentDTO.class);
            dto.setBookingId(payment.getBooking().getId());
            return dto;
        }).collect(Collectors.toList());
    }
}