package ssau.esa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "persons")
public class PersonXml {

    @XmlElement
    List<Person> person = new ArrayList<>();

    public PersonXml(){ }

    public PersonXml(List<Person> persons) {
        this.person = persons;
    }

    public List<Person> getPersons() {
        return person;
    }

}
