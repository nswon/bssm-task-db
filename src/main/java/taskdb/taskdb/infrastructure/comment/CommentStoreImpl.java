package taskdb.taskdb.infrastructure.comment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.domain.comment.domain.Comment;
import taskdb.taskdb.domain.comment.port.CommentStore;
import taskdb.taskdb.domain.comment.repository.CommentRepository;

@Component
@RequiredArgsConstructor
public class CommentStoreImpl implements CommentStore {
    private final CommentRepository commentRepository;

    @Override
    public Comment store(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
