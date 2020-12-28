package ua.lpnu.lab4spring.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PostDTO {

    private long postId;
    private String subject;
    private String text;
    private LocalDate dateOfPostCreation;
    private long authorId;
    private String authorFirstname;
    private String authorLastname;
    private long numberOfComments;

}
