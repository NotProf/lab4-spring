package ua.lpnu.lab4spring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.lpnu.lab4spring.DTO.PostDTO;
import ua.lpnu.lab4spring.entity.Post;
import ua.lpnu.lab4spring.entity.User;
import ua.lpnu.lab4spring.exception.ServiceException;
import ua.lpnu.lab4spring.mapper.PostMapper;
import ua.lpnu.lab4spring.repository.PostRepository;
import ua.lpnu.lab4spring.repository.UserRepository;
import ua.lpnu.lab4spring.service.PostService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(UserRepository userRepository, PostRepository postRepository, PostMapper postMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }

    @Override
    public List<PostDTO> getAllByUserEmail(String email) {
        try {
            User user = userRepository.findByEmail(email);
            return user.getPosts().stream().map(postMapper::toDTO).collect(Collectors.toList());

        } catch (Exception ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
        }

    }

    @Override
    public List<PostDTO> createNewPost(PostDTO postDTO) {

            User author = userRepository.findById(postDTO.getAuthorId());
            if (author == null) {
                throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
            }

            Post postFromDTO = postMapper.toEntity(postDTO, author);

            Post saved = postRepository.save(postFromDTO);
            List<Post> posts = author.getPosts();

            posts.add(saved);
            author.setPosts(posts);
            User save = userRepository.save(author);

            return save.getPosts().stream().filter((p) -> p.getAuthor().getId() == author.getId())
                    .map(postMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public PostDTO editPost(PostDTO postDTO) {
        User author = userRepository.findById(postDTO.getAuthorId());

        if (author == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
        }

        Optional<Post> findedPost = author.getPosts().stream().filter((post) -> post.getId() == postDTO.getPostId())
                .findFirst();
        if (!findedPost.isPresent()) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Post not found");
        }

        Post post = postMapper.toEntity(postDTO, author);
        System.out.println(post);
        Post save = postRepository.save(post);
        return postMapper.toDTO(save);

    }

    @Override
    public void deletePost(long userId, long postId) {

            Post post = postRepository.findById(postId);
            if (post == null) {
                throw new ServiceException(HttpStatus.NOT_FOUND, "Post not found");
            }

            if (post.getAuthor().getId() == userId) {
                postRepository.delete(post);
            } else {
                throw new ServiceException(HttpStatus.FORBIDDEN, "Access denied");
            }
    }

    @Override
    public PostDTO getPostById(long postId) {
        try {
            return postMapper.toDTO(postRepository.getOne(postId));
        } catch (Exception ex) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }
}
