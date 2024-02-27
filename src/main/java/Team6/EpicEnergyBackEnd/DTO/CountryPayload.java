package Team6.EpicEnergyBackEnd.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountryPayload {

    String abbreviation;
    String countryName;
    String region;

    public CountryPayload(String abbreviation, String countryName, String region) {
        this.abbreviation = abbreviation;
        this.countryName = countryName;
        this.region = region;
    }
}
