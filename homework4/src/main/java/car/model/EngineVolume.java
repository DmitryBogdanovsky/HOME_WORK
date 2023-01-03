package car.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "VOLUME")
@PrimaryKeyJoinColumn(name = "CAR_ID")
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@Getter
@Setter
@SuperBuilder
public class EngineVolume extends Car{
    private static final long serialVersionUID = 3L;
    private int volume;
    private String vin;
}
