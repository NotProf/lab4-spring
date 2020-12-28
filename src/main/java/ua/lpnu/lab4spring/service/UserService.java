package ua.lpnu.lab4spring.service;

import ua.lpnu.lab4spring.DTO.UserDTO;
import java.util.List;

public interface UserService {

    UserDTO createUser(UserDTO userDTO);

    List<UserDTO> getAllUsers();

    UserDTO getUserById(long userId);

    void deleteUser(long userId);

    UserDTO updateUser(UserDTO userDTO);
}
