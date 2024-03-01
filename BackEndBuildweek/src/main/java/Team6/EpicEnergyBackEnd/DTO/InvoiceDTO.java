package Team6.EpicEnergyBackEnd.DTO;

import java.time.LocalDate;
import java.util.UUID;

public record InvoiceDTO(LocalDate date, double amount, String state,int year ,UUID id) {
}
