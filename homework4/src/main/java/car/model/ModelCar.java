package car.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


@Entity
@Table(name = "MODEL")
@PrimaryKeyJoinColumn(name = "CAR_ID")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@SuperBuilder
public class ModelCar extends Car{
    private static final long serialVersionUID = 4L;
    private String model;
    private int year;
}
