package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.InvoiceDTO;
import Team6.EpicEnergyBackEnd.models.Invoice;
import Team6.EpicEnergyBackEnd.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Invoice> createInvoice(@RequestBody InvoiceDTO invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceNumber) {
        return invoiceService.getInvoice(invoiceNumber)
                .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{invoiceNumber}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long invoiceNumber, @RequestBody Invoice updatedInvoice) {
        Invoice invoice = invoiceService.updateInvoice(invoiceNumber, updatedInvoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceNumber}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceNumber) {
        invoiceService.deleteInvoice(invoiceNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable UUID clientId) {
        List<Invoice> invoices = invoiceService.getInvoicesByClientId(clientId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/state/{state}")
    public ResponseEntity<List<Invoice>> getInvoicesByState(@PathVariable String state) {
        List<Invoice> invoices = invoiceService.getInvoicesByState(state);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Invoice>> getInvoicesByDate(@PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<Invoice> invoices = invoiceService.getInvoicesByDate(parsedDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/date-range/{startDate}/{endDate}")
    public ResponseEntity<List<Invoice>> getInvoicesByDateRange(@PathVariable String startDate, @PathVariable String endDate) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        LocalDate parsedEndDate = LocalDate.parse(endDate);
        List<Invoice> invoices = invoiceService.getInvoicesByDateRange(parsedStartDate, parsedEndDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/year/{year}")
    public ResponseEntity<List<Invoice>> getInvoicesByYear(@PathVariable int year) {
        List<Invoice> invoices = invoiceService.getInvoicesByYear(year);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/amount-range/{minAmount}/{maxAmount}")
    public ResponseEntity<List<Invoice>> getInvoicesByAmountRange(@PathVariable double minAmount, @PathVariable double maxAmount) {
        List<Invoice> invoices = invoiceService.getInvoicesByAmountRange(minAmount, maxAmount);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
