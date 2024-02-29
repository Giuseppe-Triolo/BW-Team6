package Team6.EpicEnergyBackEnd.controller;

import Team6.EpicEnergyBackEnd.DTO.AddressDTO;
import Team6.EpicEnergyBackEnd.models.Address;
import Team6.EpicEnergyBackEnd.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address create(@RequestBody AddressDTO addressDTO) {
        return addressService.create(addressDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Address updateByID(@RequestBody AddressDTO addressDTO, @PathVariable UUID id) {
        return addressService.findByIdAndUpdate( id, Address.fromDTO(addressDTO));
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable UUID id ) {
        return addressService.getById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteById(@PathVariable UUID id) {
        addressService.deleteById(id);
    }


}
