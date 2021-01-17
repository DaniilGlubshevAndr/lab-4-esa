package ssau.esa.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "citizenship")
@Table(name = "CITIZENSHIP")
@Data
public class Citizenship implements BasicModel{
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    public Citizenship() { }

    @JsonIgnore
    public int getId() {
        return id;
    }

    @JsonIgnore
    public String getTableName() {
        return "citizenship";
    }
}
