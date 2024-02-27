package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> {

    City findByNameOfCity(String name);
}
