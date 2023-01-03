import beans.Person;
import beans.Phone;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

public class TestTask9 {

    AnnotationConfigApplicationContext annotationContext
            = new AnnotationConfigApplicationContext(Person.class, Phone.class);

    @Test
    public void qualifyninTest() {
        Person person = annotationContext.getBean("person", Person.class);
        assertNotNull(person);
   //    System.out.println(person);
        annotationContext.close();

    }


}

