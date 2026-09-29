package lk.ijse.cmjd._115.movie_ticket_booking.service.impl;

import lk.ijse.cmjd._115.movie_ticket_booking.dao.UserDAO;
import lk.ijse.cmjd._115.movie_ticket_booking.dto.UserDTO;
import lk.ijse.cmjd._115.movie_ticket_booking.entity.UserEntity;
import lk.ijse.cmjd._115.movie_ticket_booking.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceIMPL implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        UserEntity savedUser = userDAO.save(userEntity);

        UserDTO responseDTO = modelMapper.map(savedUser, UserDTO.class);
        responseDTO.setPassword(null); // Response එකේ password එක hide කිරීම
        return responseDTO;
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        UserEntity existingUser = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));

        existingUser.setName(userDTO.getName());
        existingUser.setEmail(userDTO.getEmail());
        existingUser.setPhone(userDTO.getPhone());
        existingUser.setRole(userDTO.getRole());

        // Password එක වෙනස් කිරීමට අවශ්‍ය නම් පමණක් update කිරීම
        if (userDTO.getPassword() != null && !userDTO.getPassword().isEmpty()) {
            existingUser.setPassword(userDTO.getPassword());
        }

        UserEntity updatedUser = userDAO.save(existingUser);
        UserDTO responseDTO = modelMapper.map(updatedUser, UserDTO.class);
        responseDTO.setPassword(null);
        return responseDTO;
    }

    @Override
    public void deleteUser(Long id) {
        if (!userDAO.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userDAO.deleteById(id);
    }

    @Override
    public UserDTO getUserById(Long id) {
        UserEntity userEntity = userDAO.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        UserDTO dto = modelMapper.map(userEntity, UserDTO.class);
        dto.setPassword(null);
        return dto;
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userDAO.findAll().stream()
                .map(user -> {
                    UserDTO dto = modelMapper.map(user, UserDTO.class);
                    dto.setPassword(null);
                    return dto;
                })
                .collect(Collectors.toList());
    }
}