package lexer;

import customExceptions.LexerException;
import lexeme.Lexeme;
import lexeme.LexemeType;

import java.io.IOException;
import java.io.Reader;

public class Lexer {
    private Reader reader;
    private int current;

    public Lexer(Reader reader) {
        this.reader = reader;
        try {
            current = reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Lexeme getLexeme() throws IOException {
        Lexeme res;
        current = reader.read();
        while (Character.isWhitespace(current)) {
            current = reader.read();
        }
        switch (current) {
            case '+':
                res = new Lexeme(LexemeType.PLUS, "+");
                break;
            case '-':
                res = new Lexeme(LexemeType.MINUS, "-");
                break;
            case '/':
                res = new Lexeme(LexemeType.DIV, "/");
                break;
            case '*':
                res = new Lexeme(LexemeType.MUL, "*");
                break;
            case '^':
                res = new Lexeme(LexemeType.POW, "^");
                break;
            case '(':
                res = new Lexeme(LexemeType.OPEN, "(");
                break;
            case ')':
                res = new Lexeme(LexemeType.CLOSE, ")");
                break;
            case -1:
                res = new Lexeme(LexemeType.EOF, "EOF");
                break;
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
                StringBuilder sb = new StringBuilder();
                do {
                    sb.append((char) current);
                    current = reader.read();
                } while (current >= '0' && current <= '9');
                res = new Lexeme(LexemeType.NUM, sb.toString());
                break;
            default:
                throw new LexerException("Unknown lexeme");
        }
        if (res.type != LexemeType.NUM) current = reader.read();
        return res;
    }
}
