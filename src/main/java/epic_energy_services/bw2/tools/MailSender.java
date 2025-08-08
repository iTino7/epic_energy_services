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


    public void sendNotificationEmail(Cliente recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "epic_energy@gmail.com")
                .queryString("to", "mhanzdnd@gmail.com")
                .queryString("subject", "Fatture da saldare!")
                .queryString("text", "Caro " + recipient.getNomeContatto() + " " + recipient.getCognomeContatto() + ", " +
                                "speriamo di farle cosa grata notificandole il mancato pagamento delle ultime fatture da lei inserite." +
                        "Mhanz!")
                .asJson();
        System.out.println(response.getBody());
    }

    public void sendChristmasEmail(Cliente recipient){
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "epic_energy@gmail.com")
                .queryString("to", "mhanzdnd@gmail.com")
                .queryString("subject", "It's Tiiiiime!")
                .queryString("text", "Caro " + recipient.getNomeContatto() + ", auguri di Buon Natale! " +
                        "Da tutto il team di epic-service <3" )
                .asJson();
        System.out.println(response.getBody());
    }

    public void sendThanksEmail(Cliente recipient) {
        HttpResponse<JsonNode> response = Unirest.post("https://api.mailgun.net/v3/" + this.domain + "/messages")
                .basicAuth("api", this.apiKey)
                .queryString("from", "epic_energy@gmail.com")
                .queryString("to", "mhanzdnd@gmail.com")
                .queryString("subject", "Grazie!")
                .queryString("text", "Caro " + recipient.getNomeContatto() + ", grazie di essere ancora nostro cliente.")
                .asJson();
        System.out.println(response.getBody());
    }


}
