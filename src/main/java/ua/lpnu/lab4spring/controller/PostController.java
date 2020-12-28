package ua.lpnu.lab4spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.lab4spring.DTO.PostDTO;
import ua.lpnu.lab4spring.service.PostService;
import java.util.*;

@RestController
@RequestMapping("postController")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/getAllByEmail/{email}")
    public ResponseEntity<List<PostDTO>> getPostsByEmail(@PathVariable String email) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.getAllByUserEmail(email));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(postService.getPostById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<List<PostDTO>> createPost (@RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.createNewPost(postDTO));
    }

    @PutMapping("/update")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(postService.editPost(postDTO));
    }

    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable long userId,
                                        @PathVariable long postId) {
        postService.deletePost(userId, postId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
