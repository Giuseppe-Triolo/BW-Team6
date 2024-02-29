package Team6.EpicEnergyBackEnd.DTO;

import java.time.LocalDate;
import java.util.UUID;

public record ClientDTO(UUID id, String businessName, Long vatNumber, String email, LocalDate startDate,
                        LocalDate lastContact,  Double annualTurnover, String pec, Long number, String emailReferee,
                        String nameReferee, String surnameReferee, Long numberReferee, String logo) {
}
