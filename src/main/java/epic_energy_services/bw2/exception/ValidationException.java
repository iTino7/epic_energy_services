package epic_energy_services.bw2.exception;

public class ValidationException extends RuntimeException {
    public ValidationException(String errorMessages) {
        super(errorMessages);
    }
}
