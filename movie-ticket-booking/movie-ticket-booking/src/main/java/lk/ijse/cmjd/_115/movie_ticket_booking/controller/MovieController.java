package lk.ijse.cmjd._115.movie_ticket_booking.controller;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.MovieDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
@CrossOrigin
public class MovieController {

    @Autowired
    private MovieService movieService;


    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO) {
        MovieDTO savedMovie = movieService.saveMovie(movieDTO); // ✅ saveMovie ලෙස නිවැරදි කරන ලදී
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable Long id, @RequestBody MovieDTO movieDTO) {
        MovieDTO updatedMovie = movieService.updateMovie(id, movieDTO);
        return ResponseEntity.ok(updatedMovie);
    }


    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovieById(@PathVariable Long id) {
        MovieDTO movieDTO = movieService.getMovieById(id);
        return ResponseEntity.ok(movieDTO);
    }


    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<MovieDTO> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }
}