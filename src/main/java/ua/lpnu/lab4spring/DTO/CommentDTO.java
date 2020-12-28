package ua.lpnu.lab4spring.DTO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentDTO {

    private long commentID;
    private LocalDate commentDate;
    private String text;
    private long postId;
    private long authorId;
    private String authorFirstname;
    private String authorLastname;
}
