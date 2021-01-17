package ssau.esa.notice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import ssau.esa.model.Email;
import ssau.esa.model.Event;
import ssau.esa.repo.EmailRepo;
import ssau.esa.repo.EventRepo;

@Component
public class JmsReceiver {
    private final EmailSender emailSender;
    private final EventRepo eventRepo;
    private final EmailRepo emailRepo;

    @Autowired
    public JmsReceiver(EmailSender emailSender, EventRepo eventRepo, EmailRepo emailRepo) {
        this.emailSender = emailSender;
        this.eventRepo = eventRepo;
        this.emailRepo = emailRepo;
    }

    @JmsListener(destination = "events", containerFactory = "myFactory")
    public void receiveChange(Event event) {
        eventRepo.save(event);
    }

    @JmsListener(destination = "emails", containerFactory = "myFactory")
    public void receiveEmail(Email email) {
        email.setEmail(emailSender.getSendEmail());
        emailRepo.save(email);
        emailSender.send(email.getBody(), email.toString());
    }
}
