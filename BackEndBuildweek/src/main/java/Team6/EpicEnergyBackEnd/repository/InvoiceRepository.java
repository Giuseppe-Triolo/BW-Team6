package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {

    List<Invoice> findByClientId(UUID clientId);


    List<Invoice> findByState(String state);

    List<Invoice> findByDate(LocalDate date);

    List<Invoice> findByDateBetween(LocalDate startDate, LocalDate endDate);

    List<Invoice> findByYear(int year);

    List<Invoice> findByAmountBetween(double minAmount, double maxAmount);
}
