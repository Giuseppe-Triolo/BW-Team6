package Team6.EpicEnergyBackEnd.models;

import Team6.EpicEnergyBackEnd.DTO.ClientDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Client {
    @Id
    @GeneratedValue
    private UUID id;

    private String businessName;

    private Long vatNumber;

    private String email;

    private LocalDate startDate;

    private LocalDate lastContact;

    private double annualTurnover;

    private String pec;

    private Long number;

    private String emailReferee;

    private String nameReferee;

    private String surnameReferee;

    private Long numberReferee;

    private String logo;

    private Type type;

    @OneToMany
    private List<Address> adresses;

    @OneToMany
    private List<Invoice> invoices;

    public static Client fromDTO(ClientDTO clientDTO) {
        Client client = new Client();
        client.businessName = clientDTO.businessName();
        client.vatNumber = clientDTO.vatNumber();
        client.email = clientDTO.email();
        client.startDate = clientDTO.startDate();
        client.lastContact = clientDTO.lastContact();
        client.annualTurnover = clientDTO.annualTurnover();
        client.pec = clientDTO.pec();
        client.number = clientDTO.number();
        client.emailReferee = clientDTO.emailReferee();
        client.nameReferee = clientDTO.nameReferee();
        client.surnameReferee = clientDTO.surnameReferee();
        client.numberReferee = clientDTO.numberReferee();

        return client;
    }

}
