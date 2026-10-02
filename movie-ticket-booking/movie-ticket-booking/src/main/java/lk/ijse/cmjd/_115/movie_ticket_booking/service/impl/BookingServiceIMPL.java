package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.BookingDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dao.ShowDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dao.UserDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.BookingDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.BookingStatus;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.BookingEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.ShowEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.UserEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingServiceIMPL implements BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ShowDAO showDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public BookingDTO createBooking(BookingDTO dto) {
        UserEntity userEntity = userDAO.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));

        ShowEntity showEntity = showDAO.findById(dto.getShowId())
                .orElseThrow(() -> new RuntimeException("Show not found with id: " + dto.getShowId()));

        BookingEntity entity = new BookingEntity();
        entity.setUser(userEntity);
        entity.setShow(showEntity);
        entity.setSeatNumbers(dto.getSeatNumbers());
        entity.setNumberOfTickets(dto.getNumberOfTickets());
        entity.setTotalAmount(dto.getTotalAmount());
        entity.setBookingTime(dto.getBookingTime() != null ? dto.getBookingTime() : LocalDateTime.now());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : BookingStatus.PENDING);

        BookingEntity savedEntity = bookingDAO.save(entity);
        return mapToDTO(savedEntity);
    }

    @Override
    public BookingDTO updateBooking(Long id, BookingDTO dto) {
        BookingEntity entity = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));

        if (dto.getUserId() != null) {
            UserEntity userEntity = userDAO.findById(dto.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found with id: " + dto.getUserId()));
            entity.setUser(userEntity);
        }

        if (dto.getShowId() != null) {
            ShowEntity showEntity = showDAO.findById(dto.getShowId())
                    .orElseThrow(() -> new RuntimeException("Show not found with id: " + dto.getShowId()));
            entity.setShow(showEntity);
        }

        if (dto.getSeatNumbers() != null) entity.setSeatNumbers(dto.getSeatNumbers());
        if (dto.getNumberOfTickets() != null) entity.setNumberOfTickets(dto.getNumberOfTickets());
        if (dto.getTotalAmount() != null) entity.setTotalAmount(dto.getTotalAmount());
        if (dto.getBookingTime() != null) entity.setBookingTime(dto.getBookingTime());
        if (dto.getStatus() != null) entity.setStatus(dto.getStatus());

        BookingEntity updatedEntity = bookingDAO.save(entity);
        return mapToDTO(updatedEntity);
    }

    @Override
    public BookingDTO getBookingById(Long id) {
        BookingEntity entity = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        return mapToDTO(entity);
    }

    @Override
    public List<BookingDTO> getAllBookings() {
        return bookingDAO.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingDTO> getBookingsByUserId(Long userId) {
        List<BookingEntity> bookings = bookingDAO.findBookingsByUserId(userId);
        return bookings.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBookedSeatsByShowId(Long showId) {
        List<BookingEntity> bookings = bookingDAO.findByShowId(showId);
        List<String> bookedSeats = new ArrayList<>();

        for (BookingEntity booking : bookings) {
            if (booking.getStatus() != BookingStatus.CANCELLED && booking.getSeatNumbers() != null) {
                for (String seat : booking.getSeatNumbers()) {
                    if (seat != null) {
                        bookedSeats.add(seat.trim());
                    }
                }
            }
        }
        return bookedSeats;
    }

    @Override
    public void cancelBooking(Long id) {
        BookingEntity entity = bookingDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found with id: " + id));
        entity.setStatus(BookingStatus.CANCELLED);
        bookingDAO.save(entity);
    }

    private BookingDTO mapToDTO(BookingEntity entity) {
        BookingDTO dto = modelMapper.map(entity, BookingDTO.class);
        if (entity.getUser() != null) {
            dto.setUserId(entity.getUser().getId());
        }
        if (entity.getShow() != null) {
            dto.setShowId(entity.getShow().getId());
        }
        return dto;
    }
}