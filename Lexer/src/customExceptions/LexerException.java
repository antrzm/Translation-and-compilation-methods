package customExceptions;

public class LexerException extends RuntimeException{
    public LexerException(String errorMessage){
        super(errorMessage);
    }
}
