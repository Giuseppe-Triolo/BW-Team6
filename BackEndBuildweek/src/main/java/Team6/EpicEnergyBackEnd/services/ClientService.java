package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.DTO.ClientDTO;
import Team6.EpicEnergyBackEnd.config.MailgunSender;
import Team6.EpicEnergyBackEnd.exceptions.NotFoundException;
import Team6.EpicEnergyBackEnd.models.Address;
import Team6.EpicEnergyBackEnd.models.Client;
import Team6.EpicEnergyBackEnd.models.Type;
import Team6.EpicEnergyBackEnd.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MailgunSender mailgunSender;

    @Autowired
    private AddressService addressService;

    public Client create(ClientDTO clientDTO) {
        List<Address> addressList = new ArrayList<>();
        Address newAddress = addressService.getById(clientDTO.id());
        addressList.add(newAddress);
        Client newClient = Client.fromDTO(clientDTO);
        newClient.setAdresses(addressList);
        if (clientDTO.type().toLowerCase().equals(Type.PA.toString().toLowerCase())){
            newClient.setType(Type.PA);
        } else if (clientDTO.type().toLowerCase().equals(Type.SAS.toString().toLowerCase())) {
            newClient.setType(Type.SAS);
        }else if (clientDTO.type().toLowerCase().equals(Type.SPA.toString().toLowerCase())) {
            newClient.setType(Type.SPA);
        }else if (clientDTO.type().toLowerCase().equals(Type.SRL.toString().toLowerCase())) {
            newClient.setType(Type.SRL);
        }
        newClient.setLogo("https://ui-avatars.com/api/?" + clientDTO.businessName());
        clientRepository.save(newClient);
        mailgunSender.sendRegistrationClient(newClient);
        return newClient;
    }

    public Client getBbyId(UUID id) {
        return clientRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
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

    public void deleteById(UUID id) {
        Client found = this.getBbyId(id);
        mailgunSender.deleteAccountClient(found);
        clientRepository.deleteById(id);
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
