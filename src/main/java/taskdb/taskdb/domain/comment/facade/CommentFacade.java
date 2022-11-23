package taskdb.taskdb.domain.comment.facade;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.domain.CommentRepository;
import taskdb.taskdb.domain.comment.exception.CommentException;
import taskdb.taskdb.domain.comment.exception.CommentExceptionType;

@Component
@RequiredArgsConstructor
public class CommentFacade {
    private final CommentRepository commentRepository;

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(()-> new CommentException(CommentExceptionType.NOT_FOUND_COMMENT));
    }
}
