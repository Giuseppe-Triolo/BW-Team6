package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.DTO.CountryPayload;
import Team6.EpicEnergyBackEnd.models.Country;
import Team6.EpicEnergyBackEnd.repository.CountryDAO;
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

    public void updateOfProvince(String oldName, CountryPayload newProvince) {
        Country countryToUpdate = findByCountryName(oldName);
        countryToUpdate.setCountryName(newProvince.getCountryName());
        countryToUpdate.setAbbreviation(newProvince.getAbbreviation());
        countryToUpdate.setRegion(newProvince.getRegion());
        countryDAO.save(countryToUpdate);
    }

    public void creationOfSouthSardinia() {
        deleteByProvinceName("Medio Campidano");
        Country country = findByCountryName("Carbonia Iglesias");
        country.setCountryName("Sud Sardegna");
        country.setAbbreviation("SU");
        countryDAO.save(country);
    }

    public void deleteByProvinceName(String provinceName) {
        Country country = findByCountryName(provinceName);
        countryDAO.delete(country);
    }

    public boolean presenceOfRecords() {
        if (countryDAO.count() > 0) return true;
        return false;
    }
}
