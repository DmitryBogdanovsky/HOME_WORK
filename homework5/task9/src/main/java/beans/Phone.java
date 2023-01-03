package beans;


import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@ToString
@AddressAnnotated
public class Phone implements IAddress {

@Value("1")
    private long id;

@Value("+375 17 123-45-67")
    private String phone;

@Value("+375 29 555-55-55")
    private String mobilePhone;

    public Phone() {

    }


}
