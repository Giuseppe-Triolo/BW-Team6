package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.models.Invoice;
import Team6.EpicEnergyBackEnd.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping("")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable Long invoiceNumber) {
        Optional<Invoice> invoiceOptional = invoiceService.getInvoice(invoiceNumber);
        return invoiceOptional.map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PutMapping("/{invoiceNumber}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long invoiceNumber, @RequestBody Invoice updatedInvoice) {
        Invoice invoice = invoiceService.updateInvoice(invoiceNumber, updatedInvoice);
        return new ResponseEntity<>(invoice, HttpStatus.OK);
    }

    @DeleteMapping("/{invoiceNumber}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable Long invoiceNumber) {
        invoiceService.deleteInvoice(invoiceNumber);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}
