package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.ClientDTO;
import Team6.EpicEnergyBackEnd.models.Client;
import Team6.EpicEnergyBackEnd.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clients")
@CrossOrigin(origins = "http://localhost:4200")
public class ClientController {

@Autowired
private ClientService clientService;

    @GetMapping("")
    public Page<Client> getAllclients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return this.clientService.getClients(page, size, orderBy);
    }

    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client create(@RequestBody ClientDTO clientDTO) {
       return clientService.create(clientDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Client updateByID(@RequestBody ClientDTO clientDTO, @PathVariable UUID id) {
        return clientService.findByIdAndUpdate( id, Client.fromDTO(clientDTO));
    }

    @GetMapping("/{id}")
    public Client getById(@PathVariable UUID id ) {
        return clientService.getBbyId(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable UUID id) {
        clientService.deleteById(id);
    }
}
