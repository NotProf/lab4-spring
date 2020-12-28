package ua.lpnu.lab4spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lpnu.lab4spring.DTO.CommentDTO;
import ua.lpnu.lab4spring.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("commentController")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/add")
    public ResponseEntity<CommentDTO> createComment (@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.addComment(commentDTO));
    }

    @GetMapping("/getByPostId/{id}")
    public ResponseEntity<List<CommentDTO>>  getCommentByPostId(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getByPostId(id));
    }

    @PutMapping("/edit")
    public ResponseEntity<CommentDTO> editComment(@RequestBody CommentDTO commentDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.updateComment(commentDTO));
    }

    @DeleteMapping("/delete/{commentId}/{userId}")
    public ResponseEntity<?> deleteComment(@PathVariable long commentId,
                                        @PathVariable long userId) {
        commentService.delete(commentId, userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
