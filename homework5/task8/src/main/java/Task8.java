import beans.Person;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task8 {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person person = context.getBean("person", Person.class);
        System.out.println(person);
        context.close();
    }

}