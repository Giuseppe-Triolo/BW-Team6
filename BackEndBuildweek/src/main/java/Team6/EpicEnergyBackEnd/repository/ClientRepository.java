package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {


    List<Client> findByOrderByBusinessName();

    List<Client> findByOrderByAnnualTurnover();

    List<Client> findByOrderByStartDate();

    List<Client> findByOrderByLastContact();

    List<Client> findByAnnualTurnover(Double AnnualTurnover);

    List<Client> findByStartDate(LocalDate StartDate);

    List<Client> findByLastContact(LocalDate lastContact);

    List<Client> findByBusinessNameContaining(String BusinessName);


}
