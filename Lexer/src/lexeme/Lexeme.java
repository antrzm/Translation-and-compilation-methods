package lexeme;

public class Lexeme{
    public LexemeType type;
    public String text;

    public Lexeme(LexemeType type, String text){
        this.text = text;
        this.type = type;
    }
}