package Team6.EpicEnergyBackEnd.DTO;

import java.time.LocalDate;

public record ClientDTO(String addressId, String businessName, Long vatNumber, String email, LocalDate startDate,
                        LocalDate lastContact,  Double annualTurnover, String pec, Long numberOfCompany, String emailReferee,
                        String nameReferee, String lastNameReferee, Long numberOfReferee, String typeOfCompany) {
}
