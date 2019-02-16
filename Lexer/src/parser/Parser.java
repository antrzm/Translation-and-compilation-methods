package parser;

import customExceptions.ParserException;
import lexeme.Lexeme;
import lexeme.LexemeType;
import lexer.Lexer;

import java.io.IOException;
import java.io.Reader;

public class Parser {
    private Lexeme currentLexeme;
    private Lexer lexer;

    public Parser(Reader reader) throws IOException {
        this.lexer = new Lexer(reader);
        this.currentLexeme = lexer.getLexeme();
    }

    public int calculate() throws IOException {
        int res = parseExpression();
        if (currentLexeme.type != LexemeType.EOF) throw new ParserException("Wrong expression: " + currentLexeme.text);
        return res;
    }

    private int parseExpression() throws IOException {
        int tmp = parseTerm();
        while (currentLexeme.type == LexemeType.PLUS || currentLexeme.type == LexemeType.MINUS) {
            if (currentLexeme.type == LexemeType.PLUS) {
                currentLexeme = lexer.getLexeme();
                tmp += parseTerm();
            }
            if (currentLexeme.type == LexemeType.MINUS) {
                currentLexeme = lexer.getLexeme();
                tmp -= parseTerm();
            }
        }
        return tmp;
    }

    private int parseTerm() throws IOException {
        int tmp = parseFactor();
        while (currentLexeme.type == LexemeType.MUL || currentLexeme.type == LexemeType.DIV) {
            if (currentLexeme.type == LexemeType.MUL) {
                currentLexeme = lexer.getLexeme();
                tmp *= parseTerm();
            }
            if (currentLexeme.type == LexemeType.DIV) {
                currentLexeme = lexer.getLexeme();
                tmp /= parseTerm();
            }
        }
        return tmp;
    }

    private int parseFactor() throws IOException {
        int tmp = parsePow();
        if (currentLexeme.type == LexemeType.POW) {
            currentLexeme = lexer.getLexeme();
            tmp = (int) Math.pow(tmp, parseFactor());
        }
        return tmp;
    }

    private int parsePow() throws IOException {
        if (currentLexeme.type == LexemeType.MINUS) {
            currentLexeme = lexer.getLexeme();
            return -(parseAtom());
        }
        return parseAtom();
    }

    private int parseAtom() throws IOException {
        int tmp;
        if (currentLexeme.type == LexemeType.NUM) {
            tmp = Integer.parseInt(currentLexeme.text);
            currentLexeme = lexer.getLexeme();
            return tmp;
        } else if (currentLexeme.type == LexemeType.OPEN) {
            currentLexeme = lexer.getLexeme();
            tmp = parseExpression();
            if (currentLexeme.type != LexemeType.CLOSE) throw new ParserException("Wrong braces order");
            currentLexeme = lexer.getLexeme();
        } else throw new ParserException("Wrong lexeme: " + currentLexeme.text);
        return tmp;
    }
}
