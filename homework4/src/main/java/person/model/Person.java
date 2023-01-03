package person.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import embeddable.Adress;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;

@Entity
@Table(name = "PERSON")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@SuperBuilder
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "PERSON_TYPE", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue(value = "P")
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(
            name = "increment",
            strategy = "org.hibernate.id.IncrementGenerator")
    private long id;

    @Column
    private int age;

    @Column
    private String name;

    @Column
    private String surname;

    Adress adress;
}
