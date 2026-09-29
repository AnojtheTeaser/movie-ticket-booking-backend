package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.MovieStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieDTO {
    private Long movieId;
    private String title;
    private String genre;
    private Integer durationMinutes;
    private String language;
    private LocalDate releaseDate;
    private String description;
    private String posterUrl;
    private MovieStatus status;
}