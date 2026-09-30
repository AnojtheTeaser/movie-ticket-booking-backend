package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.MovieDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.MovieDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.MovieEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieServiceIMPL implements MovieService {

    @Autowired
    private MovieDAO movieDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MovieDTO saveMovie(MovieDTO movieDTO) {
        MovieEntity movieEntity = modelMapper.map(movieDTO, MovieEntity.class);
        MovieEntity savedMovie = movieDAO.save(movieEntity);
        return modelMapper.map(savedMovie, MovieDTO.class);
    }

    @Override
    public MovieDTO updateMovie(Long id, MovieDTO movieDTO) {
        MovieEntity existingMovie = movieDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        existingMovie.setTitle(movieDTO.getTitle());
        existingMovie.setGenre(movieDTO.getGenre());
        existingMovie.setDuration(movieDTO.getDurationMinutes());
        existingMovie.setLanguage(movieDTO.getLanguage());
        existingMovie.setReleaseDate(movieDTO.getReleaseDate());
        existingMovie.setDescription(movieDTO.getDescription());
        existingMovie.setPosterUrl(movieDTO.getPosterUrl());
        existingMovie.setStatus(movieDTO.getStatus());



        MovieEntity updatedMovie = movieDAO.save(existingMovie);
        return modelMapper.map(updatedMovie, MovieDTO.class);
    }

    @Override
    public void deleteMovie(Long id) {
        if (!movieDAO.existsById(id)) {
            throw new RuntimeException("Movie not found with id: " + id);
        }
        movieDAO.deleteById(id);
    }

    @Override
    public MovieDTO getMovieById(Long id) {
        MovieEntity movieEntity = movieDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));
        return modelMapper.map(movieEntity, MovieDTO.class);
    }

    @Override
    public List<MovieDTO> getAllMovies() {
        return movieDAO.findAll().stream()
                .map(movie -> modelMapper.map(movie, MovieDTO.class))
                .collect(Collectors.toList());
    }
}