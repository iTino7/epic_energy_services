package epic_energy_services.bw2.tools;


import epic_energy_services.bw2.entities.Cliente;
import kong.unirest.core.HttpResponse;
import kong.unirest.core.JsonNode;
import kong.unirest.core.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MailSender {
    private String apiKey;
    private String domain;


    public MailSender(@Value("${mailgun.apikey}") String apiKey, @Value("${mailgun.domain}") String domain) {
        this.apiKey = apiKey;
        this.domain = domain;
    }


    public void sendRegistrationEmail(Cliente recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "postmaster@sandbox1234567890.mailgun.org")
                .queryString("to", "mhanzdnd@gmail.com")
                .queryString("subject", "Registrazione completata!")
                .queryString("text", "Benvenuto " + recipient.getNomeContatto() + " sulla nostra piattaforma!")
                .asJson();
        System.out.println(response.getBody());
    }

    public void sendBillingEmail(){}

}
