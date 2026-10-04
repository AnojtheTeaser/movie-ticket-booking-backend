package lk.ijse.cmjd._115.movie_ticket_booking.dao;

import lk.ijse.cmjd._115.movie_ticket_booking.entity.ShowEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDAO extends JpaRepository<ShowEntity, Long> {

    @Query("SELECT s FROM ShowEntity s JOIN FETCH s.theatre JOIN FETCH s.movie WHERE s.movie.movieId = :movieId")
    List<ShowEntity> findByMovieMovieId(@Param("movieId") Long movieId);


    @Query("SELECT s FROM ShowEntity s JOIN FETCH s.theatre JOIN FETCH s.movie")
    List<ShowEntity> findAllWithTheatreAndMovie();
}