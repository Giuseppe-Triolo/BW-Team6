package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.AddressDTO;
import Team6.EpicEnergyBackEnd.models.Address;
import Team6.EpicEnergyBackEnd.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @GetMapping("")
    public Page<Address> getAllAddresses(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String orderBy) {
        return addressService.getAddresses(page, size, orderBy);
    }

    @PostMapping("")
    public Address create(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @PutMapping("/{id}")
    public Address updateByID(@RequestBody AddressDTO addressDTO, @PathVariable UUID id) {
        return addressService.findByIdAndUpdate( id, Address.fromDTO(addressDTO));
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable UUID id ) {
        return addressService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id) {
        addressService.deleteById(id);
    }


}
