package taskdb.taskdb.adapter.comment.out.persistence;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import taskdb.taskdb.application.comment.port.out.DeleteCommentPort;
import taskdb.taskdb.application.comment.port.out.GetCommentPort;
import taskdb.taskdb.application.comment.port.out.SaveCommentPort;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.comment.exception.CommentNotFoundException;
import taskdb.taskdb.domain.question.entity.Question;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CommentAdapter implements SaveCommentPort, GetCommentPort, DeleteCommentPort {
    private final CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public Comment delete(Comment comment) {
        commentRepository.delete(comment);
        return comment;
    }

    @Override
    public Comment getComment(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(CommentNotFoundException::new);
    }

    @Override
    public List<Comment> getComments(Question question) {
        return commentRepository.findByQuestion(question);
    }
}
