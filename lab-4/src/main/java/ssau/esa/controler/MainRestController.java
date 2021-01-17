package ssau.esa.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ssau.esa.model.*;
import ssau.esa.notice.JmsSender;
import ssau.esa.repo.CitizenshipRepo;
import ssau.esa.repo.PersonRepo;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(method={RequestMethod.POST,RequestMethod.GET})
public class MainRestController {

    private final PersonRepo personRepo;
    private final CitizenshipRepo citizenshipRepo;
    private final JmsSender jmsSender;

    @Autowired
    public MainRestController(PersonRepo personRepo, CitizenshipRepo citizenshipRepo, JmsSender jmsSender) {
        this.personRepo = personRepo;
        this.citizenshipRepo = citizenshipRepo;
        this.jmsSender = jmsSender;
    }

    @GetMapping(value = "/personsJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Person> getAllPerson(){
        return this.personRepo.findAll();
    }

    @GetMapping(value = "/citizenshipsJson", produces = MediaType.APPLICATION_JSON_VALUE)
    public Iterable<Citizenship> getAllCitizenship(){
        return this.citizenshipRepo.findAll();
    }

    @GetMapping(value = "/personsXml", produces = {MediaType.APPLICATION_XML_VALUE})
    public PersonXml getAllPersonsXml(){
        return new PersonXml((List<Person>) this.personRepo.findAll());
    }

    @GetMapping(value = "/citizenshipsXml", produces = {MediaType.APPLICATION_XML_VALUE})
    public CitizenshipXml getAllCitizenshipXml(){
        return new CitizenshipXml((List<Citizenship>) this.citizenshipRepo.findAll());
    }

    @PostMapping(value = "/personsAdd")
    public String addPerson(@RequestBody @ModelAttribute("person") Person person){
        jmsSender.sendObjectUpdate(person, "ADD");
        String body = "Add Person: " + person.toString();
        Email email = new Email(UUID.randomUUID().toString(), body);
        jmsSender.sendEmail(email);
        personRepo.save(person);
        return "redirect:/persons";
    }

    @PostMapping(value = "/citizenshipsAdd")
    public String addCitizenship(@ModelAttribute("citizenship") Citizenship citizenship){
        jmsSender.sendObjectUpdate(citizenship, "ADD");
        String body = "Add Faculty: " + citizenship.toString();
        Email email = new Email(UUID.randomUUID().toString(), body);
        jmsSender.sendEmail(email);
        citizenshipRepo.save(citizenship);
        return "redirect:/citizenships";
    }

    @DeleteMapping(value = "/personsDelete/{id}")
    public String deletePerson(@PathVariable("id") int id){
        Person person = this.personRepo.findById(id).orElse(null);
        if (person==null)
            return null;
        jmsSender.sendObjectUpdate(person, "DELETE");
        String body = "Delete Person: " + person.toString();
        Email email = new Email(UUID.randomUUID().toString(), body);
        jmsSender.sendEmail(email);
        personRepo.delete(person);
        return "redirect:/persons";
    }

    @DeleteMapping(value = "/citizenshipsDelete/{id}")
    public String deleteCitizenship(@PathVariable("id") int id){
        Citizenship citizenship = this.citizenshipRepo.findById(id).orElse(null);
        if (citizenship==null)
            return null;
        jmsSender.sendObjectUpdate(citizenship, "DELETE");
        String body = "Delete Citizenship: " + citizenship.toString();
        Email email = new Email(UUID.randomUUID().toString(), body);
        jmsSender.sendEmail(email);
        citizenshipRepo.delete(citizenship);
        return "redirect:/citizenships";
    }
}
