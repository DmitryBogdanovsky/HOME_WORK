package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Recipient implements Serializable{
    int id;
    String recipient_name;
}
