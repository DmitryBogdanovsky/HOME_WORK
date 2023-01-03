import beans.Car;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class Task2Test {
    @Test
    public void testBeanExists() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Car car =  context.getBean("car", Car.class);
        assertNotNull(car);
        System.out.println(car );
        context.close();
    }
}
