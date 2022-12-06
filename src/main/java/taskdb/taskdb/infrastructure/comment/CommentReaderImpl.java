package taskdb.taskdb.infrastructure.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.exception.CommentNotFoundException;
import taskdb.taskdb.domain.comment.port.CommentReader;
import taskdb.taskdb.domain.comment.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CommentReaderImpl implements CommentReader {
    private final CommentRepository commentRepository;

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }
}
