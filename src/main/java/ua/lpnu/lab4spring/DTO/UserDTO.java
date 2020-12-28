package ua.lpnu.lab4spring.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class UserDTO {

    private long id;
    private String email;
    private String firstname;
    private String lastname;
    private LocalDate birthday;

}
