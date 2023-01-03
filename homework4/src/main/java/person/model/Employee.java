package person.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE")
@DiscriminatorValue("E")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
@SuperBuilder
public class Employee extends Person {
    private static final long serialVersionUID = 4L;
    private String company;
    private int salary;

}
