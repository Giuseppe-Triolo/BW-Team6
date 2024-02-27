package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.models.City;
import Team6.EpicEnergyBackEnd.repository.CityDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CityService {
    @Autowired
    CityDAO cityDAO;

    public boolean presenceOfRecords() {
        if (cityDAO.count() > 0) return true;
        return false;
    }

    public City saveNewElement(City city) {
        return cityDAO.save(city);
    }
}
