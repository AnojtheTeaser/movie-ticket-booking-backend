package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.ShowDTO;

import java.util.List;

public interface ShowService {
    ShowDTO saveShow(ShowDTO showDTO);
    ShowDTO updateShow(Long id, ShowDTO showDTO);
    void deleteShow(Long id);
    ShowDTO getShowById(Long id);
    List<ShowDTO> getAllShows();
    List<ShowDTO> getShowsByMovieId(Long movieId);
}