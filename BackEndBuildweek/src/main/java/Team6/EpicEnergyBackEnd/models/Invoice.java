package Team6.EpicEnergyBackEnd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Invoice {

    @Id
    @GeneratedValue
    private Long number;

    private LocalDate date;

    private double amount;

    private String state;

    private int year;

    private String clientId;
}