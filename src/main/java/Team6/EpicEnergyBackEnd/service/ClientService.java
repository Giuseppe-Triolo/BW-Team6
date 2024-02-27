package Team6.EpicEnergyBackEnd.service;

import Team6.EpicEnergyBackEnd.DTO.ClientDTO;
import Team6.EpicEnergyBackEnd.models.Client;
import Team6.EpicEnergyBackEnd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;


    public Client create(ClientDTO clientDTO) {
        return clientRepository.save(Client.fromDTO(clientDTO));
    }

    public Client getBbyId(UUID id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client findByIdAndUpdate(UUID id, Client client) {
        if (!clientRepository.existsById(id)) {
            return null;
        }
        if (!id.equals(client.getId())) {
            return null;
        }
        return clientRepository.save(client);
    }


    public List<Client> getAllOrderedByName() {
        return clientRepository.findByOrderByBusinessName();
    }

    public List<Client> getAllOrderedbyAnnualTurnover() {
        return clientRepository.findByOrderByAnnualTurnover();
    }

    public List<Client> getAllOrderedByStartDate() {
        return clientRepository.findByOrderByStartDate();
    }
    public List<Client> getAllOrderedByLastContact() {
        return clientRepository.findByOrderByLastContact();
    }

    public List<Client> getClientsByAnnualTurnover(Double annualTurnover) {
        return clientRepository.findByAnnualTurnover(annualTurnover);
    }

    public List<Client> getClientsByStartDate(LocalDate startDate) {
        return clientRepository.findByStartDate(startDate);
    }

    public List<Client> getClientsByLastContact(LocalDate lastContact) {
        return clientRepository.findByLastContact(lastContact);
    }
    public List<Client> getClientsByBusinessName(String businessName) {
        return clientRepository.findByBusinessNameContaining(businessName);
    }











    public Page<Client> getClients(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return clientRepository.findAll(pageable);
    }



}
