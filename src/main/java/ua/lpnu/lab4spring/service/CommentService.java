package ua.lpnu.lab4spring.service;

import ua.lpnu.lab4spring.DTO.CommentDTO;

import java.util.List;

public interface CommentService {

    CommentDTO getById(long id);

    List<CommentDTO> getByPostId(long postId);

    CommentDTO addComment(CommentDTO commentDTO);

    CommentDTO updateComment(CommentDTO commentDTO);

    void delete(long commentId, long userId);


}
