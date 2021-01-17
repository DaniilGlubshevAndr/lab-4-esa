package ssau.esa.notice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;
import ssau.esa.model.BasicModel;
import ssau.esa.model.Email;
import ssau.esa.model.Event;

import java.util.Map;
import java.util.UUID;

@Service
public class JmsSender {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JmsSender(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendObjectUpdate(BasicModel object, String changeType) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> objectMap = objectMapper.convertValue(object, Map.class);
        for (String fieldName : objectMap.keySet()) {
            Event event = new Event(UUID.randomUUID().toString(), changeType, object.getTableName(), fieldName);
            jmsTemplate.convertAndSend("events",event);
        }
    }

    public void sendEmail(Email email){
        jmsTemplate.convertAndSend("emails",email);
    }
}
