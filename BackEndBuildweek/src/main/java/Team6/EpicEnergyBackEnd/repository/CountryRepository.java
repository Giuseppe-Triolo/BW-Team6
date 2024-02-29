package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
    Optional<Country> findByCountryName(String countryName);
}
