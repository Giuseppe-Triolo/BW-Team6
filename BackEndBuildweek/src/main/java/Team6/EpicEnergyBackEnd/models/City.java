package Team6.EpicEnergyBackEnd.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@Table(name = "cities")
public class City {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    UUID id;
    @Column(name = "country_code")
    String countryCode;
    @Column(name = "progressive_of_City")
    String progressiveOfCity;
    @Column(name = "name_of_city")
    String nameOfCity;
    @ManyToOne
    @JoinColumn(name = "country_id")
    Country country;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "city", fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    List<Address> address;

    public City(String countryCode, String progressiveOfCity, String nameOfCity, Country country) {
        this.countryCode = countryCode;
        this.progressiveOfCity = progressiveOfCity;
        this.nameOfCity = nameOfCity;
        this.country = country;
    }
}
