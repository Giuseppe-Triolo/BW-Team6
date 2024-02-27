package Team6.EpicEnergyBackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "countries")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    int id;
    String abbreviation;
    @Column(name = "country_name")
    String countryName;
    String region;
    @OneToMany(mappedBy = "country")
    @JsonIgnore
    List<City> cities;

    public Country(String abbreviation, String countryName, String region) {
        this.abbreviation = abbreviation;
        this.countryName = countryName;
        this.region = region;
    }

    @Override
    public String toString() {
        return "Country{" +
                "abbreviation='" + abbreviation + '\'' +
                ", countryName='" + countryName + '\'' +
                ", region='" + region + '\'' +
                '}';
    }
}
