package ssau.esa.model;

import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name="emails")
@Data
public class Email {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "email")
    private String email;

    @Column(name = "body")
    private String body;

    public Email() { }

    public Email(String id, String body) {
        this.id = id;
        this.body = body;
    }
}
