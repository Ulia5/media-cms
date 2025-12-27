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

    // Создание нового корневого комментария
    public Comment createComment(Comment comment) {
        comment.setId(null); // Убедимся, что новый документ
        return commentRepository.save(comment);
    }

    // Ответ на существующий комментарий (вложенность)
    public Comment addReply(String parentCommentId, Comment reply) {
        Optional<Comment> parentOpt = commentRepository.findById(parentCommentId);
        if (parentOpt.isPresent()) {
            Comment parent = parentOpt.get();
            reply.setId(null); // Для вложенных комментариев ID задастся MongoDB
            parent.getReplies().add(reply);
            return commentRepository.save(parent); // пересохраняем родителя
        }
        throw new RuntimeException("Parent comment not found");
    }

    // Получить все комментарии по mediaId
    public List<Comment> getCommentsByMediaId(String mediaId) {
        return commentRepository.findByMediaId(mediaId);
    }

    // Удаление корневого комментария (вместе с вложенными)
    public void deleteComment(String commentId) {
        commentRepository.deleteById(commentId);
    }

    // Удаление вложенного ответа по ID
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

