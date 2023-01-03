

import beans.Warehouse;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class Task10 {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        Warehouse warehouse = context.getBean("warehouse", Warehouse.class);
        System.out.println(warehouse);
        context.close();

    }

}
