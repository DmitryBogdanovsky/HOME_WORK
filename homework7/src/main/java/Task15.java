import config.DataConfig;
import dao.PersonDaoImpl;

import entyty.Person;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import service.PersonService;

public class Task15 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationContext
                = new AnnotationConfigApplicationContext(
                DataConfig.class,
                PersonDaoImpl.class,
                PersonService.class);

        PersonService personService =  annotationContext.getBean("personService", PersonService.class);
        Person person = new Person(null, "Semen", "Egorov");
        System.out.println(person);
        personService.addPerson(person);
        personService.findAll().forEach(System.out::println);
        annotationContext.close();
    }

}
