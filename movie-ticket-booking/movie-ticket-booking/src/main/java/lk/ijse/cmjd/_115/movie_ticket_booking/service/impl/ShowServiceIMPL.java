package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.MovieDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dao.ShowDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dao.TheatreDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.ShowDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.MovieEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.ShowEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.TheatreEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.ShowService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShowServiceIMPL implements ShowService {

    @Autowired
    private ShowDAO showDAO;

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private TheatreDAO theatreDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ShowDTO saveShow(ShowDTO showDTO) {
        MovieEntity movieEntity = movieDAO.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + showDTO.getMovieId()));
        TheatreEntity theatreEntity = theatreDAO.findById(showDTO.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + showDTO.getTheatreId()));

        ShowEntity showEntity = new ShowEntity();
        showEntity.setMovie(movieEntity);
        showEntity.setTheatre(theatreEntity);
        showEntity.setShowDate(showDTO.getShowDate());
        showEntity.setShowTime(showDTO.getShowTime());
        showEntity.setTicketPrice(showDTO.getTicketPrice());
        showEntity.setStatus(showDTO.getStatus());

        ShowEntity savedShow = showDAO.save(showEntity);

        return mapToDTO(savedShow);
    }

    @Override
    public ShowDTO updateShow(Long id, ShowDTO showDTO) {
        ShowEntity existingShow = showDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));

        MovieEntity movieEntity = movieDAO.findById(showDTO.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + showDTO.getMovieId()));
        TheatreEntity theatreEntity = theatreDAO.findById(showDTO.getTheatreId())
                .orElseThrow(() -> new RuntimeException("Theatre not found with id: " + showDTO.getTheatreId()));

        existingShow.setMovie(movieEntity);
        existingShow.setTheatre(theatreEntity);
        existingShow.setShowDate(showDTO.getShowDate());
        existingShow.setShowTime(showDTO.getShowTime());
        existingShow.setTicketPrice(showDTO.getTicketPrice());
        existingShow.setStatus(showDTO.getStatus());

        ShowEntity updatedShow = showDAO.save(existingShow);

        return mapToDTO(updatedShow);
    }

    @Override
    public void deleteShow(Long id) {
        if (!showDAO.existsById(id)) {
            throw new RuntimeException("Show not found with id: " + id);
        }
        showDAO.deleteById(id);
    }

    @Override
    public ShowDTO getShowById(Long id) {
        ShowEntity showEntity = showDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + id));
        return mapToDTO(showEntity);
    }

    @Override
    public List<ShowDTO> getAllShows() {
        return showDAO.findAllWithTheatreAndMovie().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShowDTO> getShowsByMovieId(Long movieId) {
        return showDAO.findByMovieMovieId(movieId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private ShowDTO mapToDTO(ShowEntity show) {
        ShowDTO dto = new ShowDTO();
        dto.setId(show.getId());
        dto.setShowDate(show.getShowDate());
        dto.setShowTime(show.getShowTime());
        dto.setTicketPrice(show.getTicketPrice());
        dto.setStatus(show.getStatus());

        if (show.getMovie() != null) {
            dto.setMovieId(show.getMovie().getMovieId());
        }

        if (show.getTheatre() != null) {
            dto.setTheatreId(show.getTheatre().getId());
            dto.setCapacity(show.getTheatre().getCapacity());
            dto.setSeatMapUrl(show.getTheatre().getSeatMapUrl());
        }

        return dto;
    }
}