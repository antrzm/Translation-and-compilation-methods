package customExceptions;

import java.io.IOException;

public class ParserException extends IOException {
    public ParserException(String error) {
        super(error);
    }
}
