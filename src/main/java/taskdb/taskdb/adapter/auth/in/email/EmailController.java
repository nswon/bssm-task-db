package taskdb.taskdb.adapter.auth.in.email;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.application.auth.dto.EmailRequestDto;
import taskdb.taskdb.application.auth.dto.EmailResponseDto;
import taskdb.taskdb.application.auth.port.in.EmailSendUseCase;
import taskdb.taskdb.application.auth.service.EmailService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {
    private final EmailSendUseCase emailSendUseCase;

    @PostMapping("/join")
    public ResponseEntity<EmailResponseDto> sendSimpleMessage(@RequestBody EmailRequestDto request) {
        emailSendUseCase.send(request);
        return ResponseEntity.status(HttpStatus.OK).body(new EmailResponseDto("성공"));
    }
}
