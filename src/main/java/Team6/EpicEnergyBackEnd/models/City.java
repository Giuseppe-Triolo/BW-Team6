package Team6.EpicEnergyBackEnd.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    int id;
    @Column(name = "country_code")
    String countryCode;
    @Column(name = "progressive_of_City")
    String progressiveOfCity;
    @Column(name = "name_of_city")
    String nameOfCity;
    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    public City(String countryCode, String progressiveOfCity, String nameOfCity, Country country) {
        this.countryCode = countryCode;
        this.progressiveOfCity = progressiveOfCity;
        this.nameOfCity = nameOfCity;
        this.country = country;
    }
}
