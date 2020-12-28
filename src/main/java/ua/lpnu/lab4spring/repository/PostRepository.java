package ua.lpnu.lab4spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.lpnu.lab4spring.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long > {

    Post findById(long id);

}
