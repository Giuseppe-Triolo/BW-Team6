package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface AddressRepository extends JpaRepository<Address, UUID> {
}
