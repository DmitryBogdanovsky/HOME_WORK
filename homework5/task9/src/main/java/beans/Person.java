package beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

@ToString
@Setter
@Getter
public class Person {

@Value("1")
    private long id;

@Value("ANGELIKA")
    private String name;

@Value("PETROVA")
    private String surname;

    @Autowired
    @AddressAnnotated
    private IAddress phone;

}
