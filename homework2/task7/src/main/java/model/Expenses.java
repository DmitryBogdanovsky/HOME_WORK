package model;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class Expenses implements Serializable {
    int id;
    LocalDate payday;
    int recipient_id;
    double summa;
}
