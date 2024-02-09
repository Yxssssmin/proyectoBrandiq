package brandiq.brandiq.exception;

public class ResourceNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;
    String msg;

    public ResourceNotFoundException(String msg) {
        super();
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}