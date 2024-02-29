package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.models.City;
import Team6.EpicEnergyBackEnd.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CityService {
    @Autowired
    CityRepository cityRepository;

    public boolean presenceOfRecords() {
        if (cityRepository.count() > 0) return true;
        return false;
    }

    public City saveNewElement(City city) {
        return cityRepository.save(city);
    }

    public void modifyProgressive(String progressiveNumber) {
        List<City> cities = cityRepository.findByprogressiveOfCity(progressiveNumber);
        int count = 0;
        for (City city : cities) {
            count++;
            String zeros = "";
            if (count < 10) zeros = "00";
            else if (count >= 10) zeros = "0";
            else if (count > 99) zeros = "";
            city.setProgressiveOfCity(zeros + count);
            cityRepository.save(city);
        }
    }
}
