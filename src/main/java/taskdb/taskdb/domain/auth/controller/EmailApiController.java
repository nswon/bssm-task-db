package taskdb.taskdb.domain.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.auth.dto.EmailRequestDto;
import taskdb.taskdb.domain.auth.dto.EmailResponseDto;
import taskdb.taskdb.domain.auth.service.EmailService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailApiController {

    private final EmailService emailService;

    @PostMapping("/join")
    public ResponseEntity<EmailResponseDto> sendSimpleMessage(@RequestBody @Valid EmailRequestDto request) {
        emailService.sendSimpleMessage(request.getEmail());
        return ResponseEntity.status(HttpStatus.OK).body(new EmailResponseDto("성공"));
    }
}
