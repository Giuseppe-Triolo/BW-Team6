package Team6.EpicEnergyBackEnd.repository;

import Team6.EpicEnergyBackEnd.models.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityDAO extends JpaRepository<City, Integer> {
    List<City> findByprogressiveOfCity(String progressiveOfCity);
}
