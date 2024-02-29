package Team6.EpicEnergyBackEnd.config;


import Team6.EpicEnergyBackEnd.models.User;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MailgunSender {

    private String domainName;
    private String mailGunAPIKey;

    public MailgunSender(@Value("${mailgun.apikey}") String mailGunAPIKey, @Value("${mailgun.domain}") String domainName) {
        this.mailGunAPIKey = mailGunAPIKey;
        this.domainName = domainName;
    }

    public void sendRegistrationEmail(User userRegister){
        Unirest.post("https://api.mailgun.net/v3/" + domainName + "/messages")
                .basicAuth("api", mailGunAPIKey)
                .queryString("from", "Giuseppe Triolo <giuseppe.triolo99@gmail.com>")
                .queryString("to", userRegister.getEmail())
                .queryString("subject", "Registrazione completata")
                .queryString("text", "Complimenti per esserti registrato")
                .asJson();
    }
}
