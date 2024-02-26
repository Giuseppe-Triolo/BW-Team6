package Team6.EpicEnergyBackEnd.DAO;

import Team6.EpicEnergyBackEnd.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> {
}
