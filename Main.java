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
        this.result = 0;
    }

    public int eval() throws IOException, ParseError {
        if (!exp() ){//|| this.lookahead != '\n') {
            throw new ParseError();
        }
        return this.result;
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

    public boolean exp() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        return (term() && exp2());
    }

    public boolean exp2() throws IOException, ParseError {
        if (this.lookahead == '^') {
            consume('^');
            return (term() && exp2());
        }
        return true;
    }

    public boolean term() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        return (factor() && term2());
    }

    public boolean factor() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            throw new ParseError();
        }
        if (this.lookahead == '(') {
            consume('(');
            if (exp() && this.lookahead == ')') {
                return true;
            }
            else {
                System.out.println("Expected )");
                return false;
            }
        }
        return true;
    }

    public boolean term2() throws IOException, ParseError {
        if (this.lookahead == '&') {
            consume('&');
            return (factor() && term2());
        }
        return true;
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