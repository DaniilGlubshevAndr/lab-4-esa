package ssau.esa.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "citizenships")
public class CitizenshipXml {

    @XmlElement
    List<Citizenship> citizenship = new ArrayList<>();

    public CitizenshipXml(){ }

    public CitizenshipXml(List<Citizenship> citizenships) {
        this.citizenship = citizenships;
    }

    public void setCitizenships(List<Citizenship> citizenships) {
        this.citizenship = citizenships;
    }

}
