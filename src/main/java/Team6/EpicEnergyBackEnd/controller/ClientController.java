package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.ClientDTO;
import Team6.EpicEnergyBackEnd.models.Client;
import Team6.EpicEnergyBackEnd.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
public class ClientController {

@Autowired
private ClientService clientService;

    @GetMapping("")
    public Page<Client> getAllclients(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size,
                                      @RequestParam(defaultValue = "id") String orderBy) {
        return this.clientService.getClients(page, size, orderBy);
    }

    @PostMapping("/")
    public Client create(ClientDTO clientDTO) {
       return clientService.create(clientDTO);
    }
}
