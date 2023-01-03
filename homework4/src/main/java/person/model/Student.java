package person.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "STUDENT")
@DiscriminatorValue("S")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper=true)
public class Student extends Person {
    private static final long serialVersionUID = 3L;
    private String faculty;
    private Double mark;

}
