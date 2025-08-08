package epic_energy_services.bw2.payloads;

public record IndirizzoRespDTO(
        Long id,
        String via,
        String civico,
        String località,
        String cap,
        String nomeComune
) {
}
