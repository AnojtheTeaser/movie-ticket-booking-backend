package lk.ijse.cmjd._115.movie_ticket_booking.controller;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.ShowDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shows")
@CrossOrigin
public class ShowController {

    @Autowired
    private ShowService showService;


    @PostMapping
    public ResponseEntity<ShowDTO> addShow(@RequestBody ShowDTO showDTO) {
        ShowDTO savedShow = showService.saveShow(showDTO);
        return new ResponseEntity<>(savedShow, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ShowDTO> updateShow(@PathVariable Long id, @RequestBody ShowDTO showDTO) {
        ShowDTO updatedShow = showService.updateShow(id, showDTO);
        return ResponseEntity.ok(updatedShow);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ShowDTO> getShowById(@PathVariable Long id) {
        ShowDTO showDTO = showService.getShowById(id);
        return ResponseEntity.ok(showDTO);
    }


    @GetMapping
    public ResponseEntity<List<ShowDTO>> getAllShows() {
        List<ShowDTO> shows = showService.getAllShows();
        return ResponseEntity.ok(shows);
    }


    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowDTO>> getShowsByMovieId(@PathVariable Long movieId) {
        List<ShowDTO> shows = showService.getShowsByMovieId(movieId);
        return ResponseEntity.ok(shows);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long id) {
        showService.deleteShow(id);
        return ResponseEntity.noContent().build();
    }
}