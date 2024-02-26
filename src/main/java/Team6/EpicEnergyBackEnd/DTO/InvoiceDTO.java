package Team6.EpicEnergyBackEnd.DTO;

import java.time.LocalDate;

public record InvoiceDTO(Long number, LocalDate date, double amount, String state) {
}
