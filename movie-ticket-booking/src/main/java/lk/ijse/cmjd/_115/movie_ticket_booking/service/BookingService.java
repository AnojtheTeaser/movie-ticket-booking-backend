package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.BookingDTO;

import java.util.List;

public interface BookingService {
    BookingDTO createBooking(BookingDTO bookingDTO);
    BookingDTO getBookingById(Long id);
    List<BookingDTO> getAllBookings();
    List<BookingDTO> getBookingsByUserId(Long userId);
    void cancelBooking(Long id);
}