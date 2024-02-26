package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
