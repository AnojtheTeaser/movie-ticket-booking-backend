package lk.ijse.cmjd._115.movie_ticket_booking.dao;

import lk.ijse.cmjd._115.movie_ticket_booking.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<ShowEntity, Long> {
    List<ShowEntity> findByMovieMovieId(Long movieId);
}