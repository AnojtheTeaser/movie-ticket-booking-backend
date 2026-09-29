package lk.ijse.cmjd._115.movie_ticket_booking.dao;

import lk.ijse.cmjd._115.movie_ticket_booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<BookingEntity, Long> {
    List<BookingEntity> findByUserUserId(Long userId);
}