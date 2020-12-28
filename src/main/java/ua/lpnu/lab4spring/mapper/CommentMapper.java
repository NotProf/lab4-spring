package ua.lpnu.lab4spring.mapper;

import org.springframework.stereotype.Component;
import ua.lpnu.lab4spring.DTO.CommentDTO;
import ua.lpnu.lab4spring.entity.Comment;
import ua.lpnu.lab4spring.entity.Post;
import ua.lpnu.lab4spring.entity.User;

@Component
public class CommentMapper {

    public CommentDTO toDTo(Comment comment) {
        CommentDTO commentDTO = new CommentDTO();

        commentDTO.setCommentID(comment.getId());
        commentDTO.setCommentDate(comment.getDate());
        commentDTO.setText(comment.getText());
        commentDTO.setPostId(comment.getPost().getId());
        commentDTO.setAuthorFirstname(comment.getAuthor().getFirstname());
        commentDTO.setAuthorLastname(comment.getAuthor().getLastname());
        commentDTO.setAuthorId(comment.getAuthor().getId());

        return commentDTO;
    }

    public Comment toEntity(CommentDTO commentDTO,
                            final User author,
                            final Post post) {
        Comment comment = new Comment();

        comment.setId(commentDTO.getCommentID());
        comment.setText(commentDTO.getText());
        comment.setDate(commentDTO.getCommentDate());
        comment.setAuthor(author);
        comment.setPost(post);

        return comment;
    }
}
