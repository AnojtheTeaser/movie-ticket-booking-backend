package lk.ijse.cmjd._115.movie_ticket_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.ShowStatus;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "shows")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long showId;

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private MovieEntity movie;

    @ManyToOne
    @JoinColumn(name = "theatre_id", nullable = false)
    private TheatreEntity theatre;

    @Column(nullable = false)
    private LocalDate showDate;

    @Column(nullable = false)
    private LocalTime showTime;

    @Column(nullable = false)
    private Double ticketPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ShowStatus status;
}