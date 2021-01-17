package ssau.esa.notice;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class EmailSender {
    private String sendEmail = "daniel.glubshev99@gmail.com";
    private final JavaMailSender mailSender;

    @Autowired
    public EmailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void send(String changeType, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmail);
        message.setFrom("Spring");
        message.setSubject(changeType);
        message.setText(body);
        mailSender.send(message);
    }
}
