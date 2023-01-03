package beans;


import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Provider {

    @Value("3")
    private long id;

    @Value("PANASONIC")
    private String provider;
}
