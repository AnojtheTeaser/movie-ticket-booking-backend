package lk.ijse.cmjd._115.movie_ticket_booking.dto;

import lk.ijse.cmjd._115.movie_ticket_booking.dto.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long userId;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;
}