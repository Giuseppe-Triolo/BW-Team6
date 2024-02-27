package Team6.EpicEnergyBackEnd.models;

import Team6.EpicEnergyBackEnd.DTO.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    private City city;

    public static Address fromDTO(AddressDTO addressDTO) {
        Address address = new Address();
        address.street = addressDTO.street();
        address.houseNumber = addressDTO.houseNumber();
        address.country = addressDTO.country();
        address.postalCode = addressDTO.postalCode();

        return address;
    }

}
