package taskdb.taskdb.application.comment.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import taskdb.taskdb.application.comment.port.in.CommentDeleteUseCase;
import taskdb.taskdb.application.comment.port.in.CommentSaveUseCase;
import taskdb.taskdb.application.comment.port.in.CommentUpdateUseCase;
import taskdb.taskdb.application.comment.port.in.ReCommentSaveUseCase;
import taskdb.taskdb.application.comment.port.out.DeleteCommentPort;
import taskdb.taskdb.application.comment.port.out.GetCommentPort;
import taskdb.taskdb.application.comment.port.out.SaveCommentPort;
import taskdb.taskdb.application.question.port.out.GetQuestionPort;
import taskdb.taskdb.application.user.policy.UserPolicy;
import taskdb.taskdb.domain.comment.entity.Comment;
import taskdb.taskdb.domain.comment.entity.Content;
import taskdb.taskdb.application.comment.dto.CommentCreateRequestDto;
import taskdb.taskdb.application.comment.dto.CommentUpdateRequestDto;
import taskdb.taskdb.domain.question.entity.Question;
import taskdb.taskdb.domain.user.entity.User;
import taskdb.taskdb.application.user.port.out.GetUserPort;
import taskdb.taskdb.application.comment.dto.CommentMapper;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService implements
        CommentSaveUseCase, ReCommentSaveUseCase, CommentUpdateUseCase, CommentDeleteUseCase {
    private static final String FOUR_A_M_CORN = "0 0 4 * * *";
    private final GetUserPort getUserPort;
    private final GetQuestionPort getQuestionPort;
    private final CommentMapper commentMapper;
    private final SaveCommentPort saveCommentPort;
    private final GetCommentPort getCommentPort;
    private final DeleteCommentPort deleteCommentPort;
    private final UserPolicy userPolicy;

    @Override
    public void save(UUID id, CommentCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(id);
        Comment comment = commentMapper.of(requestDto, user, question);
        saveCommentPort.save(comment);
        question.addCommentCount();
    }

    @Override
    public void save(UUID questionId, UUID parentId, CommentCreateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Question question = getQuestionPort.getQuestion(questionId);
        Comment parentComment = getCommentPort.getComment(parentId);
        Comment comment = commentMapper.of(requestDto, user, question, parentComment);
        saveCommentPort.save(comment);
        question.addCommentCount();
    }

    @Override
    public void update(UUID id, CommentUpdateRequestDto requestDto) {
        User user = getUserPort.getCurrentUser();
        Comment comment = getCommentPort.getComment(id);
        userPolicy.check(user, comment.getUser());
        updateContent(requestDto.getContent(), comment);
    }

    private void updateContent(String requestContent, Comment comment) {
        Content content = Content.of(requestContent);
        comment.update(content);
    }

    @Override
    public void delete(UUID id) {
        User user = getUserPort.getCurrentUser();
        Comment comment = getCommentPort.getComment(id);
        userPolicy.check(user, comment.getUser());
        deleteCommentPort.delete(comment);
        Question question = getQuestionPort.getQuestion(comment.getQuestion().getId());
        question.downCommentCount();
    }

    @Scheduled(cron = FOUR_A_M_CORN)
    public void syncLike() {
        getQuestionPort.getQuestions()
                        .forEach(this::syncComment);
    }

    private void syncComment(Question question) {
        int commentCount = getCommentPort.getComments(question).size();
        question.syncCommentCount(commentCount);
    }
}
