package lk.ijse.cmjd._115.movie_ticket_booking.controller;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.TheatreDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/theatres")
@CrossOrigin
public class TheatreController {

    @Autowired
    private TheatreService theatreService;


    @PostMapping
    public ResponseEntity<TheatreDTO> addTheatre(@RequestBody TheatreDTO theatreDTO) {
        TheatreDTO savedTheatre = theatreService.saveTheatre(theatreDTO);
        return new ResponseEntity<>(savedTheatre, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<TheatreDTO> updateTheatre(@PathVariable Long id, @RequestBody TheatreDTO theatreDTO) {
        TheatreDTO updatedTheatre = theatreService.updateTheatre(id, theatreDTO);
        return ResponseEntity.ok(updatedTheatre);
    }


    @GetMapping("/{id}")
    public ResponseEntity<TheatreDTO> getTheatreById(@PathVariable Long id) {
        TheatreDTO theatreDTO = theatreService.getTheatreById(id);
        return ResponseEntity.ok(theatreDTO);
    }


    @GetMapping
    public ResponseEntity<List<TheatreDTO>> getAllTheatres() {
        List<TheatreDTO> theatres = theatreService.getAllTheatres();
        return ResponseEntity.ok(theatres);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTheatre(@PathVariable Long id) {
        theatreService.deleteTheatre(id);
        return ResponseEntity.noContent().build();
    }
}