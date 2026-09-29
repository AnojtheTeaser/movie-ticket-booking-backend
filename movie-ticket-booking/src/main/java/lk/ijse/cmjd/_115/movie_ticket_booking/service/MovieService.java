package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.MovieDTO;

import java.util.List;

public interface MovieService {
    MovieDTO saveMovie(MovieDTO movieDTO);
    MovieDTO updateMovie(Long id, MovieDTO movieDTO);
    void deleteMovie(Long id);
    MovieDTO getMovieById(Long id);
    List<MovieDTO> getAllMovies();
}