import beans.Car;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Task2 {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Car car = context.getBean("car", Car.class);
        System.out.println(car);
        context.close();
    }
}

