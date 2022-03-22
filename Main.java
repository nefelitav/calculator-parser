import java.util.Scanner;
import java.io.IOException;
import java.io.InputStream;

class Calculator {
    private final InputStream in;
    private int lookahead;
    private int result;

    public Calculator(InputStream in) throws IOException {
        this.in = in;
        this.lookahead = in.read();
    }

    public int eval() throws IOException, ParseError {
        int result = exp();
        if (this.lookahead != '\n') {
            throw new ParseError();
        }
        return result;
    }

    public void consume(int symbol) throws IOException, ParseError {
        if (this.lookahead != symbol) {
            throw new ParseError();
        }
        this.lookahead = in.read();

    }

    public int evalDigit(int c) {
        return c - '0';
    }

    public int exp() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        int term = term();
        int exp2 = exp2(term);
        return exp2;
    }

    public int exp2(int term) throws IOException, ParseError {
        if (this.lookahead == '^') {
            consume('^');
            int result = term ^ term();
            int exp2 = exp2(result);
            return exp2;
        }
        return term;
    }

    public int term() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        int factor = factor();
        int term = term2(factor);
        return term;
    }

    public int factor() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        if (this.lookahead == '(') {
            consume('(');
            int exp = exp();
            if (this.lookahead == ')') {
                return exp;
            }
            else {
                throw new ParseError();
            }
        }
        return this.lookahead;
    }

    public int term2(int factor) throws IOException, ParseError {
        if (this.lookahead == '&') {
            consume('&');
            int result = factor ^ factor();
            int term2 = term2(result);
            return term2;
        }
        return factor;
    }

    public boolean num(int c) {
        return ('0' <= c && c <= '9');
    }
    
}

class ParseError extends Exception {
    public String getMessage() {
	    return "Parse Error";
    }
}

class Main {
    public static void main(String[] args) {
        try {
            Calculator c = new Calculator(System.in);
            System.out.println(c.eval());
        } catch (IOException | ParseError e) {
            System.err.println(e.getMessage());
        }
    }
}