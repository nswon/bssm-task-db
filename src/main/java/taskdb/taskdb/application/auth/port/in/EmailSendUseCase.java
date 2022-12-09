package taskdb.taskdb.application.auth.port.in;

import taskdb.taskdb.application.auth.dto.EmailRequestDto;

public interface EmailSendUseCase {
    void send(EmailRequestDto requestDto);
}
