package Team6.EpicEnergyBackEnd.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue
    private Long number;

    private LocalDate date;

    private double amount;

    private String state;

    private int year;

    private UUID clientId;

    public Invoice(LocalDate date, double amount, String state, int year, UUID clientId) {
        this.date = date;
        this.amount = amount;
        this.state = state;
        this.year = year;
        this.clientId = clientId;
    }
}