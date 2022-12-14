package taskdb.taskdb.application.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import taskdb.taskdb.application.auth.dto.EmailRequestDto;
import taskdb.taskdb.application.auth.port.in.EmailSendUseCase;
import taskdb.taskdb.domain.auth.exception.EmailSendFailedException;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService implements EmailSendUseCase {
    private static final int RANDOM_SIZE = 10;
    private static final int RANDOM_NUMBER_COUNT = 8;
    private final JavaMailSender javaMailSender;
    private static String ePw = "empty";

    private static void createKey() {
        StringBuilder key = new StringBuilder();
        Random rnd = new Random();
        IntStream.range(0, RANDOM_NUMBER_COUNT)
                .forEach(action -> key.append(rnd.nextInt(RANDOM_SIZE)));
        ePw = String.valueOf(key);
    }

    private MimeMessage createMessage(String email) throws Exception {
        createKey();
        log.info("보내는 대상 : " + email);
        log.info("인증 번호 : " + ePw);
        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(Message.RecipientType.TO, email);
        message.setSubject("[부산소마고 과제 저장소] 회원가입 이메일 인증");
        String msgg = createScreen();
        message.setText(msgg, "utf-8", "html");
        message.setFrom(new InternetAddress("taskdb0729@gmail.com", "부산소마고 과제 저장소"));
        return message;
    }

    private String createScreen() {
        String msgg="";
        msgg+= "<div style='margin:100px;'>";
        msgg+= "<h1><span style='color:orange;'>메일인증</span> 안내입니다.</h1>";
        msgg+= "<br/>";
        msgg+= "<p>안녕하세요.</p>";
        msgg+= "<p>아래 <span style='color:orange;'>'회원가입 인증 코드'</span> 를 복사 후 회원가입 입력을 완료해주세요.</p>";
        msgg+= "감사합니다.";
        msgg+= "<br>";
        msgg+= "<br>";
        msgg+= "<div align='center'>";
        msgg+= "<div style='font-size:130%;'>";
        msgg+= "<h1>";
        msgg+= ePw+"</h1><div><br/> ";
        msgg+= "</div>";
        return msgg;
    }

    @Override
    public void send(EmailRequestDto requestDto) {
        try{
            MimeMessage message = createMessage(requestDto.getEmail());
            javaMailSender.send(message);
        } catch (Exception e) {
            throw new EmailSendFailedException();
        }
    }

    public boolean verityCode(String code) {
        return !ePw.equals(code);
    }
}
