package Team6.EpicEnergyBackEnd;

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

        String path = new File("./src/main/java/Team6/EpicEnergyBackEnd/CSVs/province-italiane.csv").getAbsolutePath();
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
                    //Use the tokens as required
                    Country country = new Country(countryCamps[0], countryCamps[1], countryCamps[2]);
                    System.out.println(country);
                    countryService.saveNewElement(country);
                }
                count++;
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }

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
                    City city = new City(cityCamps[0], cityCamps[1], cityCamps[2], countryService.findByCountryName(cityCamps[3]));
                    System.out.println(city);
                    cityService.saveNewElement(city);
                    System.out.println(Arrays.toString(nextLine));
                    System.out.println(cityCamps[3]);
                }
                count++;
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
        }
    }
}
