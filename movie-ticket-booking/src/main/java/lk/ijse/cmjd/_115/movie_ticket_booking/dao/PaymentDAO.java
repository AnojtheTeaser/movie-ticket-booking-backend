package lk.ijse.cmjd._115.movie_ticket_booking.dao;

import lk.ijse.cmjd._115.movie_ticket_booking.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentDAO extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByBookingBookingId(Long bookingId);
}