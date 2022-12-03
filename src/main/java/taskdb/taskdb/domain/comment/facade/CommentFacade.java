package taskdb.taskdb.domain.comment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.repository.CommentRepository;
import taskdb.taskdb.domain.comment.exception.CommentNotFoundException;

@Component
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentRepository commentRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
}
