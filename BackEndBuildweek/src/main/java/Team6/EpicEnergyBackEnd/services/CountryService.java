package Team6.EpicEnergyBackEnd.services;

import Team6.EpicEnergyBackEnd.DTO.CountryDTO;
import Team6.EpicEnergyBackEnd.exceptions.NotFoundException;
import Team6.EpicEnergyBackEnd.models.Country;
import Team6.EpicEnergyBackEnd.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountryService {
    @Autowired
    CountryRepository countryRepository;

    public Country saveNewElement(Country country) {
        return countryRepository.save(country);
    }

    public Country findByCountryName(String countryName) {
        return countryRepository.findByCountryName(countryName).orElseThrow(() -> new NotFoundException("Not found any element"));
    }

    public void updateOfProvince(String oldName, CountryDTO newProvince) {
        Country countryToUpdate = findByCountryName(oldName);
        countryToUpdate.setCountryName(newProvince.getCountryName());
        countryToUpdate.setAbbreviation(newProvince.getAbbreviation());
        countryToUpdate.setRegion(newProvince.getRegion());
        countryRepository.save(countryToUpdate);
    }

    public void creationOfSouthSardinia() {
        deleteByProvinceName("Medio Campidano");
        Country country = findByCountryName("Carbonia Iglesias");
        country.setCountryName("Sud Sardegna");
        country.setAbbreviation("SU");
        countryRepository.save(country);
    }

    public void deleteByProvinceName(String provinceName) {
        Country country = findByCountryName(provinceName);
        countryRepository.delete(country);
    }

    public boolean presenceOfRecords() {
        if (countryRepository.count() > 0) return true;
        return false;
    }
}
