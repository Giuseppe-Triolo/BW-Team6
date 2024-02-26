package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.DAO.CountryDAO;
import Team6.EpicEnergyBackEnd.models.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    CountryDAO countryDAO;

    public Country saveNewElement(Country country) {
        return countryDAO.save(country);
    }

    public Country findByCountryName(String countryName) {
        return countryDAO.findByCountryName(countryName).orElseThrow(() -> new RuntimeException("Non è stato trovato nessun elemento"));
    }
}
