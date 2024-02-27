package Team6.EpicEnergyBackEnd.service;

import Team6.EpicEnergyBackEnd.models.Invoice;
import Team6.EpicEnergyBackEnd.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    public List<Invoice> getInvoicesByClientId(String clientId) {
        return invoiceRepository.findByClientId(clientId);
    }

    public List<Invoice> getInvoicesByState(String state) {
        return invoiceRepository.findByState(state);
    }

    public List<Invoice> getInvoicesByDate(LocalDate date) {
        return invoiceRepository.findByDate(date);
    }

    public List<Invoice> getInvoicesByDateRange(LocalDate startDate, LocalDate endDate) {
        return invoiceRepository.findByDateBetween(startDate, endDate);
    }

    public List<Invoice> getInvoicesByYear(int year) {
        return invoiceRepository.findByYear(year);
    }

    public List<Invoice> getInvoicesByAmountRange(double minAmount, double maxAmount) {
        return invoiceRepository.findByAmountBetween(minAmount, maxAmount);
    }
}
