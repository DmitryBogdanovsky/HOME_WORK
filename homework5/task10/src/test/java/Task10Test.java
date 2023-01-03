import beans.Warehouse;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class Task10Test {

    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @Test
    public void componentTest(){

        Warehouse warehouse = context.getBean("warehouse", Warehouse.class);
        assertNotNull(warehouse.getNameWarehouse());
        System.out.println(warehouse.getNameWarehouse());

    }

}
