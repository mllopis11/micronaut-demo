package mike.demo.company.organization.commons.exception;

public class EntityAlreadyExistException extends RuntimeException {

    private static final long serialVersionUID = 4878084950075855430L;

    public EntityAlreadyExistException(String message, Object... args) {
        super(String.format(message, args));
    }
    
    public EntityAlreadyExistException(Throwable throwable, String message, Object... args) {
        super(String.format(message, args), throwable);
    }
}
