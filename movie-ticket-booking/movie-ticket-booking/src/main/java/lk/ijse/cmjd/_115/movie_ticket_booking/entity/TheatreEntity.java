package lk.ijse.cmjd._115.movie_ticket_booking.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.TheatreStatus;

@Entity
@Table(name = "theatres")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TheatreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "theatre_name", nullable = false)
    private String name;

    @Column(nullable = false)
    private String location;

    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TheatreStatus status;

    @Column(name = "seat_map_url", length = 500)
    private String seatMapUrl;
}