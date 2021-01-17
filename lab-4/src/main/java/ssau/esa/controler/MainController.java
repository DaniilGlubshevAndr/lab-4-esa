package ssau.esa.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ssau.esa.model.Citizenship;
import ssau.esa.model.Person;
import ssau.esa.repo.CitizenshipRepo;
import ssau.esa.repo.PersonRepo;

@Controller
public class MainController {

    @Autowired
    private CitizenshipRepo citizenshipRepo;

    @Autowired
    private PersonRepo personRepo;

    @RequestMapping(value = {"/","/enter"})
    public String mainPage(Model model){
        model.addAttribute("name","Enter");
        return "enter";
    }

    @RequestMapping(value = "/citizenships", method = RequestMethod.GET)
    public Iterable<Citizenship> getAllCitizenship(Model model){
        Iterable<Citizenship> citizenships = this.citizenshipRepo.findAll();
        model.addAttribute("citizenships", citizenships);
        return citizenships;
    }

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public Iterable<Person> getAllPersons(Model model){
        Iterable<Person> persons = this.personRepo.findAll();
        model.addAttribute("persons", persons);
        return persons;
    }
}