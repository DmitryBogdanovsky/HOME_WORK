package model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "org.hibernate.id.IncrementGenerator")
    private Long id;
    @Column
    private Integer age;
    @Column
    private String name;
    @Column
    private String surname;

}
