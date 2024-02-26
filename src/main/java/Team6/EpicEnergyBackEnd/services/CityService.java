package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.DAO.CityDAO;
import Team6.EpicEnergyBackEnd.models.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CityService {
    @Autowired
    CityDAO cityDAO;

    public City saveNewElement(City city) {
        return cityDAO.save(city);
    }
}
