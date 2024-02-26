package Team6.EpicEnergyBackEnd.service;

import Team6.EpicEnergyBackEnd.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;
}
