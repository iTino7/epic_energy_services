package epic_energy_services.bw2.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("messaggio " + id);
    }

    public NotFoundException(String msg) {
        super(msg);
    }
}
