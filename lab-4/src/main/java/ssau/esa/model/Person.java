package ssau.esa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "person")
@Table(name = "PERSON")
@Data
public class Person implements BasicModel{
    @Id
    @Column(name = "mobile_phone", nullable = false, length = 100)
    private int mobile;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private  String surname;

    @Column(name = "passport", nullable = false)
    private int passport;

    @ManyToOne(optional = false, cascade = {CascadeType.ALL})
    @JoinColumn(name = "citizenship_id")
    private Citizenship citizenship;

    public Person(){}

    @Override
    @JsonIgnore
    public int getId() {
        return mobile;
    }

    @Override
    @JsonIgnore
    public String getTableName() {
        return "persons";
    }
}
