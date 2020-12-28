package ua.lpnu.lab4spring.mapper;

import org.springframework.stereotype.Component;
import ua.lpnu.lab4spring.DTO.UserDTO;
import ua.lpnu.lab4spring.entity.User;

@Component
public class UserMapper {

    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();


        userDTO.setId(user.getId());
        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setEmail(user.getEmail());
        userDTO.setBirthday(user.getBirthday());

        return userDTO;
    }

    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setId(userDTO.getId());
        user.setFirstname(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setEmail(userDTO.getEmail());
        user.setBirthday(userDTO.getBirthday());


        return user;
    }
}
