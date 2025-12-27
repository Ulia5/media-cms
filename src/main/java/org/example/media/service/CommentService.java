package org.example.media.service;

import lombok.RequiredArgsConstructor;
import org.example.media.model.Comment;
import org.example.media.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment createComment(Comment comment) {
        if (comment.getMediaId() == null || comment.getMediaId().isBlank()) {
            throw new IllegalArgumentException("Media ID is required for a comment.");
        }
        return commentRepository.save(comment);
    }

    public Comment addReply(String parentCommentId, Comment reply) {
        Optional<Comment> parentOpt = commentRepository.findById(parentCommentId);
        if (parentOpt.isPresent()) {
            Comment parent = parentOpt.get();
            reply.setId(null);
            parent.getReplies().add(reply);
            return commentRepository.save(parent);
        }
        throw new RuntimeException("Parent comment not found");
    }

    public List<Comment> getCommentsByMediaId(String mediaId) {
        return commentRepository.findByMediaId(mediaId);
    }

    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    public void deleteReply(String commentId, String replyId) {
        Optional<Comment> parentOpt = commentRepository.findById(commentId);
        if (parentOpt.isPresent()) {
            Comment parent = parentOpt.get();
            Iterator<Comment> iterator = parent.getReplies().iterator();
            while (iterator.hasNext()) {
                Comment reply = iterator.next();
                if (replyId.equals(reply.getId())) {
                    iterator.remove();
                    commentRepository.save(parent);
                    return;
                }
            }
            throw new RuntimeException("Reply not found");
        }
        throw new RuntimeException("Parent comment not found");
    }
}
