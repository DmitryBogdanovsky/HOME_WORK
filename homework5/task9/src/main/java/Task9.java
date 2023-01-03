import beans.Person;
import beans.Phone;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Task9 {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationContext
                = new AnnotationConfigApplicationContext(Person.class, Phone.class);
        Person person = annotationContext.getBean("person", Person.class);
        System.out.println(person);
        annotationContext.close();

    }

}
