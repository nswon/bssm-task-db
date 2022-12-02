package taskdb.taskdb.domain.auth.presentation;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.auth.presentation.dto.request.EmailDto;
import taskdb.taskdb.domain.auth.service.EmailService;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailApiController {

    private final EmailService emailService;

    @PostMapping("/join")
    public String sendSimpleMessage(@RequestBody @Valid EmailDto request) throws Exception {
        emailService.sendSimpleMessage(request.getEmail());
        return "성공";
    }
}
