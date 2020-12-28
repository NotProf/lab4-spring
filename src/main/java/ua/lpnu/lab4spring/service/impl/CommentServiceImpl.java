package ua.lpnu.lab4spring.service.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ua.lpnu.lab4spring.DTO.CommentDTO;
import ua.lpnu.lab4spring.entity.Comment;
import ua.lpnu.lab4spring.entity.Post;
import ua.lpnu.lab4spring.entity.User;
import ua.lpnu.lab4spring.exception.ServiceException;
import ua.lpnu.lab4spring.mapper.CommentMapper;
import ua.lpnu.lab4spring.repository.CommentRepository;
import ua.lpnu.lab4spring.repository.PostRepository;
import ua.lpnu.lab4spring.repository.UserRepository;
import ua.lpnu.lab4spring.service.CommentService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final CommentMapper commentMapper;
    private final UserRepository userRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository,
                              CommentMapper commentMapper, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.commentMapper = commentMapper;
        this.userRepository = userRepository;
    }

    @Override
    public CommentDTO getById(long id) {
        Comment byId = commentRepository.findById(id);

        if (byId == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        return commentMapper.toDTo(byId);
    }

    @Override
    public List<CommentDTO> getByPostId(long postId) {
        Post post = postRepository.findById(postId);

        if (post == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Post not found");
        }

        return post.getComments().stream().map(commentMapper::toDTo).collect(Collectors.toList());
    }

    @Override
    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment saved = commentRepository.save(this.getFullCommentEntity(commentDTO));
        return commentMapper.toDTo(saved);
    }

    @Override
    public CommentDTO updateComment(CommentDTO commentDTO) {
        Comment commentById = commentRepository.findById(commentDTO.getCommentID());
        if (commentById == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        Comment saved = commentRepository.save(this.getFullCommentEntity(commentDTO));
        return commentMapper.toDTo(saved);
    }

    @Override
    public void delete(long commentId, long userId) {
        Comment comment = commentRepository.findById(commentId);

        if (comment == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Comment not found");
        }

        if (comment.getAuthor().getId() != userId) {
            throw new ServiceException(HttpStatus.FORBIDDEN, "Access denied");
        }

        commentRepository.delete(comment);
    }

    private Comment getFullCommentEntity(CommentDTO commentDTO) {
        User author = userRepository.findById(commentDTO.getAuthorId());
        Post post = postRepository.findById(commentDTO.getPostId());

        if (post == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "Post not found");
        }

        if (author == null) {
            throw new ServiceException(HttpStatus.NOT_FOUND, "User not found");
        }

        return commentMapper.toEntity(commentDTO, author, post);

    }
}
