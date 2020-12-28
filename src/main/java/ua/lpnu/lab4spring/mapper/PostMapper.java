package ua.lpnu.lab4spring.mapper;

import lombok.Data;
import org.springframework.stereotype.Component;
import ua.lpnu.lab4spring.DTO.PostDTO;
import ua.lpnu.lab4spring.entity.Post;
import ua.lpnu.lab4spring.entity.User;

@Component
@Data
public class PostMapper {
    public PostDTO toDTO(Post post) {
        PostDTO postDTO = new PostDTO();

        postDTO.setPostId(post.getId());
        postDTO.setSubject(post.getSubject());
        postDTO.setText(post.getText());
        postDTO.setDateOfPostCreation(post.getDateOfCreation());
        postDTO.setNumberOfComments(post.getComments().size());

        if (post.getAuthor() != null) {
            postDTO.setAuthorId(post.getAuthor().getId());
            postDTO.setAuthorFirstname(post.getAuthor().getFirstname());
            postDTO.setAuthorLastname(post.getAuthor().getLastname());
        }

        return postDTO;
    }

    public Post toEntity(PostDTO postDTO, final User author) {
        Post post = new Post();
        post.setId(postDTO.getPostId());
        post.setSubject(postDTO.getSubject());
        post.setText(postDTO.getText());
        post.setDateOfCreation(postDTO.getDateOfPostCreation());
        post.setAuthor(author);

        return post;
    }
}
