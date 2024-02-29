package Team6.EpicEnergyBackEnd;

import Team6.EpicEnergyBackEnd.DTO.CountryDTO;
import Team6.EpicEnergyBackEnd.models.City;
import Team6.EpicEnergyBackEnd.models.Country;
import Team6.EpicEnergyBackEnd.services.CityService;
import Team6.EpicEnergyBackEnd.services.CountryService;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

@Component
public class CSVRunner implements CommandLineRunner {
    @Autowired
    CountryService countryService;
    @Autowired
    CityService cityService;

    @Override
    public void run(String... args) throws Exception {
        String path;

        //Inserimento delle province nel DB
        if (!countryService.presenceOfRecords()) {
            path = new File("./src/main/java/Team6/EpicEnergyBackEnd/CSVs/province-italiane.csv").getAbsolutePath();
            System.out.println(path);
            try (CSVReader reader
                         = new CSVReader(new FileReader(path))) {
                String[] nextLine;
                int count = 0;
                //Read one line at a time
                while ((nextLine = reader.readNext()) != null) {
                    if (count > 0) {
                        String[] countryCamps = Arrays.toString(nextLine).split(";");
                        countryCamps = Arrays.stream(countryCamps).map(el -> el.replace("[", "")).map(el -> el.replace("]", "")).toList().toArray(new String[0]);
                        System.out.println(Arrays.toString(countryCamps));
                        Country country = new Country(countryCamps[0], countryCamps[1], countryCamps[2]);
                        System.out.println(country);
                        countryService.saveNewElement(country);
                    }
                    count++;
                }
                countryService.updateOfProvince("Monza-Brianza", new CountryDTO("MB", "Monza e della Brianza", "Lombardia"));
                countryService.updateOfProvince("Bolzano", new CountryDTO("BZ", "Bolzano/Bozen", "Veneto"));
                countryService.updateOfProvince("La-Spezia", new CountryDTO("SP", "La Spezia", "Liguria"));
                countryService.updateOfProvince("Reggio-Emilia", new CountryDTO("RE", "Reggio nell'Emilia", "Emilia-Romagna"));
                countryService.updateOfProvince("Forli-Cesena", new CountryDTO("FC", "Forlì-Cesena", "Emilia-Romagna"));
                countryService.updateOfProvince("Pesaro-Urbino", new CountryDTO("PU", "Pesaro e Urbino", "Marche"));
                countryService.updateOfProvince("Ascoli-Piceno", new CountryDTO("AP", "Ascoli Piceno", "Marche"));
                countryService.updateOfProvince("Reggio-Calabria", new CountryDTO("RC", "Reggio Calabria", "Calabria"));
                countryService.updateOfProvince("Vibo-Valentia", new CountryDTO("VV", "Vibo Valentia", "Calabria"));
                countryService.updateOfProvince("Verbania", new CountryDTO("VCO", "Verbano-Cusio-Ossola", "Piemonte"));
                countryService.updateOfProvince("Aosta", new CountryDTO("AO", "Valle d'Aosta/Vallée d'Aoste", "Valle d'Aosta/Vallée d'Aoste"));
                countryService.creationOfSouthSardinia();
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
        //Inserimento dei comuni nel DB
        if (!cityService.presenceOfRecords()) {
            path = new File("./src/main/java/Team6/EpicEnergyBackEnd/CSVs/comuni-italiani.csv").getAbsolutePath();
            System.out.println(path);
            try (CSVReader reader
                         = new CSVReader(new FileReader(path))) {
                String[] nextLine;

                int count = 0;
                //Read one line at a time
                while ((nextLine = reader.readNext()) != null) {
                    if (count > 0) {
                        String[] cityCamps = Arrays.toString(nextLine).split(";");
                        cityCamps = Arrays.stream(cityCamps).map(el -> el.replace("[", "")).map(el -> el.replace("]", "")).toList().toArray(new String[0]);
                        System.out.println(cityCamps[3]);
                        City city;
                        city = new City(cityCamps[0], cityCamps[1], cityCamps[2], countryService.findByCountryName(cityCamps[3]));
                        cityService.saveNewElement(city);
                    }
                    count++;
                }
                cityService.modifyProgressive("#RIF!");
            } catch (IOException | CsvValidationException e) {
                e.printStackTrace();
            }
        }
    }
}
