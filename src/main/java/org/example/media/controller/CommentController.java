package org.example.media.controller;

import lombok.RequiredArgsConstructor;
import org.example.media.model.Comment;
import org.example.media.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Comment> createComment(@Valid @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.createComment(comment));
    }

    @PostMapping("/{parentId}/reply")
    public ResponseEntity<Comment> replyToComment(@PathVariable String parentId, @Valid @RequestBody Comment reply) {
        return ResponseEntity.ok(commentService.addReply(parentId, reply));
    }

    @GetMapping("/media/{mediaId}")
    public ResponseEntity<List<Comment>> getCommentsByMediaId(@PathVariable String mediaId) {
        return ResponseEntity.ok(commentService.getCommentsByMediaId(mediaId));
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable String commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{commentId}/reply/{replyId}")
    public ResponseEntity<Void> deleteReply(@PathVariable String commentId, @PathVariable String replyId) {
        commentService.deleteReply(commentId, replyId);
        return ResponseEntity.noContent().build();
    }
}
