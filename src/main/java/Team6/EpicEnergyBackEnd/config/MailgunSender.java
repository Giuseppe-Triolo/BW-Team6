package Team6.EpicEnergyBackEnd.config;


import Team6.EpicEnergyBackEnd.models.Client;
import Team6.EpicEnergyBackEnd.models.User;
import jakarta.servlet.ServletException;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunSender {

    private String domainName;
    private String mailGunAPIKey;

    private String mailFeed;

    public MailgunSender(@Value("${mailgun.apikey}") String mailGunAPIKey, @Value("${mailgun.domain}") String domainName, @Value("${mailgun.feed}") String mailFeed) {
        this.mailGunAPIKey = mailGunAPIKey;
        this.domainName = domainName;
        this.mailFeed = mailFeed;
    }

    public void sendRegistrationEmail(User userRegister){
            Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                    .basicAuth("api", mailGunAPIKey)
                    .queryString("from", "EpicEnergy <EpicEnergySRL@gmail.com>")
                    .queryString("to", userRegister.getEmail())
                    .queryString("subject", "Registrazione completata")
                    .queryString("text", "Complimenti per esserti registrato")
                    .asJson();
    }

    public void sendRegistrationClient(Client clientRegister){
        Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailGunAPIKey)
                .queryString("from", "EpicEnergy <EpicEnergySRL@gmail.com>")
                .queryString("to", clientRegister.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Grazie per .......")
                .asJson();
    }

    public void deleteAccountClient(Client client){
        Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailGunAPIKey)
                .queryString("from", "EpicEnergy <EpicEnergySRL@gmail.com>")
                .queryString("to", client.getEmail())
                .queryString("subject", "Cancellazione account completata")
                .queryString("text", "Ci dispiace per .......; lasciaci un feed per migliorare /n" + mailFeed)
                .asJson();
    }

}
