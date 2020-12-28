package ua.lpnu.lab4spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.lpnu.lab4spring.DTO.UserDTO;
import ua.lpnu.lab4spring.entity.User;
import ua.lpnu.lab4spring.exception.ServiceException;
import ua.lpnu.lab4spring.mapper.UserMapper;
import ua.lpnu.lab4spring.repository.UserRepository;
import ua.lpnu.lab4spring.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        User byEmail = userRepository.findByEmail(userDTO.getEmail());
        if (byEmail != null) {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "User with email" + userDTO.getEmail() + "already Exist");
        }
        User user = userMapper.toEntity(userDTO);
        return userMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        return allUsers.stream().map(userMapper::toDTO).collect(Collectors.toList());
    }

    public UserDTO getUserById(long userId) {
        try {
            User user = userRepository.getOne(userId);
            return userMapper.toDTO(user);
        } catch (Exception ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public void deleteUser(long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    public UserDTO updateUser(UserDTO userDTO) {
        if (userDTO.getId() != 0) {
           try {
               User user = userMapper.toEntity(userDTO);
               userRepository.update(user.getEmail(),
                       user.getFirstname(),
                       user.getLastname(),
                       user.getBirthday(),
                       user.getId());
               return userMapper.toDTO(userRepository.getOne(userDTO.getId()));
           } catch (Exception ex) {
               throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
           }
        } else {
            throw new ServiceException(HttpStatus.BAD_REQUEST, "User should have an id");
        }
    }
}
