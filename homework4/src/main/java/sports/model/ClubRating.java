package sports.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
@Getter
@Setter
public class ClubRating extends Football{
    private static final long serialVersionUID = 4L;

    private boolean ligChampion;
    private double rating;


}
