package Team6.EpicEnergyBackEnd.service;

import Team6.EpicEnergyBackEnd.models.Invoice;
import Team6.EpicEnergyBackEnd.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;
    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }
    public Optional<Invoice> getInvoice(Long invoiceNumber) {
        return invoiceRepository.findById(invoiceNumber);
    }
    public Invoice updateInvoice(Long invoiceNumber, Invoice updatedInvoice) {
        Optional<Invoice> existingInvoiceOptional = invoiceRepository.findById(invoiceNumber);
        if (existingInvoiceOptional.isPresent()) {
            updatedInvoice.setNumber(invoiceNumber);
            return invoiceRepository.save(updatedInvoice);
        } else {
            throw new RuntimeException("Invoice number: " + invoiceNumber + "not found");
        }
    }
    public void deleteInvoice(Long invoiceNumber) {
        invoiceRepository.deleteById(invoiceNumber);
    }
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }
}
