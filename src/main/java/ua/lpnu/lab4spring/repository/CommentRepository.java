package ua.lpnu.lab4spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lpnu.lab4spring.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment findById(long id);
}
