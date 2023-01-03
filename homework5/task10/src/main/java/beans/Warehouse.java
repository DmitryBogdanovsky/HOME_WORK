package beans;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
public class Warehouse {

    @Value("WAREHOUSE")
    private String nameWarehouse;

    @Autowired
    private Buyer buyer;

}
