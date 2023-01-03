import beans.Person;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;


public class TestTask8 {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void wiredTask8() {
        Person person = context.getBean("person", Person.class);
        assertNotNull(person.getChild());
        System.out.println(person);
    }
}
