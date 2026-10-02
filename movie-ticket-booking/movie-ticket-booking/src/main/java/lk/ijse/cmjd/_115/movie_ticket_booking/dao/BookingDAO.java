package lk.ijse.cmjd._115.movie_ticket_booking.dao;

import lk.ijse.cmjd._115.movie_ticket_booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingDAO extends JpaRepository<BookingEntity, Long> {


    @Query("SELECT b FROM BookingEntity b WHERE b.user.id = :userId")
    List<BookingEntity> findBookingsByUserId(@Param("userId") Long userId);
    List<BookingEntity> findByShowId(Long showId);

}