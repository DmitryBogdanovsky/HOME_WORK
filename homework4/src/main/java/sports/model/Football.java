package sports.model;


import embeddable.Country;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Football {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "football_seq")
    @SequenceGenerator(name = "football_seq", sequenceName = "t_football_seq")
    private long id;

    private String clubName;

    private Country country;
}
