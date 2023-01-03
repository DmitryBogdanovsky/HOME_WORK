package beans;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Child {

    private String name;
    private int year;

    public static Child getInstance() {
        return new Child();
    }
}
