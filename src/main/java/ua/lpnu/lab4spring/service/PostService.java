package ua.lpnu.lab4spring.service;

import ua.lpnu.lab4spring.DTO.PostDTO;
import java.util.*;

public interface PostService {

    List<PostDTO> getAllByUserEmail(String email);

    List<PostDTO> createNewPost(PostDTO postDTO);

    PostDTO editPost(PostDTO postDTO);

    void deletePost(long userId,long postId);

    PostDTO getPostById(long postId);
}
