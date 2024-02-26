package Team6.EpicEnergyBackEnd.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue
    private UUID id;

    private String street;

    private String houseNumber;

    private String country;

    private Long postalCode;

    private String city;

}
