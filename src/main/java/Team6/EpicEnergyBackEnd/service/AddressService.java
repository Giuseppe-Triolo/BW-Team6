package Team6.EpicEnergyBackEnd.service;

import Team6.EpicEnergyBackEnd.DTO.AddressDTO;
import Team6.EpicEnergyBackEnd.models.Address;
import Team6.EpicEnergyBackEnd.models.City;
import Team6.EpicEnergyBackEnd.repository.AddressRepository;
import Team6.EpicEnergyBackEnd.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityDAO cityDAO;

    public Address create(AddressDTO addressDTO) {
        Address address =  Address.fromDTO(addressDTO);
        City city = cityDAO.findByNameOfCity(addressDTO.city());
        address.setCity(city);
        return addressRepository.save(address);
    }


    public Address getById(UUID id) {
        return addressRepository.findById(id).orElseThrow();
    }
    public Address findByIdAndUpdate(UUID id,Address address
    ) {
        if (!addressRepository.existsById(id)) {
            return null;
        }
        if (!id.equals(address.getId())) {
            return null;
        }
        return addressRepository.save(address);
    }

    public void deleteById(UUID id) {
       addressRepository.deleteById(id);
    }

    public Page<Address> getAddresses(int pageNumber, int size, String orderBy) {
        if (size > 100) size = 100;
        Pageable pageable = PageRequest.of(pageNumber, size, Sort.by(orderBy));
        return addressRepository.findAll(pageable);
    }




}

