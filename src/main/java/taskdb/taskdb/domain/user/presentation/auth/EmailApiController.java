package taskdb.taskdb.domain.user.presentation.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import taskdb.taskdb.domain.user.presentation.dto.user.request.EmailDto;
import taskdb.taskdb.domain.user.service.auth.EmailService;

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
