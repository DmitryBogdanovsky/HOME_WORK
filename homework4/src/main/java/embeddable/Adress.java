package embeddable;

import lombok.*;

import javax.persistence.Embeddable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Adress {
    private int house;
    private String street;
    private String city;
    private int postalCode;
}
