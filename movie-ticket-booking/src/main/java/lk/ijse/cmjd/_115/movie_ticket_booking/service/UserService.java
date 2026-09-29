package lk.ijse.cmjd._115.movie_ticket_booking.service;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUsers();
}