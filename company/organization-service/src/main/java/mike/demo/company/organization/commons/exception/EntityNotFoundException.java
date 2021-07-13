package mike.demo.company.organization.commons.exception;

public class EntityNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -77201297889747982L;

    public EntityNotFoundException(String message, Object... args) {
        super(String.format(message, args));
    }
    
    public EntityNotFoundException(Throwable throwable, String message, Object... args) {
        super(String.format(message, args), throwable);
    }
}
