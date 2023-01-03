package beans;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString

public class Buyer {

    @Value("1")
    private long id;

    @Value("PROSTOR")
    private String buyer;

    @Autowired
    private Provider provider;
}
