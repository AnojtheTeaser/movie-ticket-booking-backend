package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.TheatreDTO;

import java.util.List;

public interface TheatreService {
    TheatreDTO saveTheatre(TheatreDTO theatreDTO);
    TheatreDTO updateTheatre(Long id, TheatreDTO theatreDTO);
    void deleteTheatre(Long id);
    TheatreDTO getTheatreById(Long id);
    List<TheatreDTO> getAllTheatres();
}