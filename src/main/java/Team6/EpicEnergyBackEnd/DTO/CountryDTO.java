package Team6.EpicEnergyBackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryDTO {

    String abbreviation;
    String countryName;
    String region;

    public CountryDTO(String abbreviation, String countryName, String region) {
        this.abbreviation = abbreviation;
        this.countryName = countryName;
        this.region = region;
    }
}
