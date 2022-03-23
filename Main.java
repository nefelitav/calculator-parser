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
            System.out.println("eval");
            throw new ParseError();
        }
        return result;
    }

    public void consume(int symbol) throws IOException, ParseError {
        if (this.lookahead != symbol) {
            System.out.println("consume");
            throw new ParseError();
        }
        this.lookahead = in.read();

    }

    public int evalDigit(int c) {
        return c - '0';
    }

    public int exp() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            System.out.println("exp");
            throw new ParseError();
        }
        int term = term();
        int exp2 = exp2(term);
        System.out.println("exp "+ exp2);
        return exp2;
    }

    public int exp2(int term) throws IOException, ParseError {
        if (this.lookahead == '^') {
            consume('^');
            int result = term ^ term();
            int exp2 = exp2(result);
            System.out.println("exp2 "+exp2);
            return exp2;
        }
        System.out.println("exp2 "+term);
        return term;
    }

    public int term() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            System.out.println("term");
            throw new ParseError();
        }
        int factor = factor();
        int term2 = term2(factor);
        System.out.println("term "+term2);
        return term2;
    }
    
    public int term2(int factor) throws IOException, ParseError {
        consume(this.lookahead);
        if (this.lookahead == '&') {
            consume('&');
            int result = factor & factor();
            int term2 = term2(result);
            System.out.println("term2 "+term2);
            return term2;
        }
        System.out.println("term2 "+factor);
        return factor;
    }

    public int factor() throws IOException, ParseError {
        if (!num(this.lookahead) && this.lookahead != '(') {
            System.out.println("factor");
            throw new ParseError();
        }
        if (this.lookahead == '(') {
            consume('(');
            int exp = exp();
            if (this.lookahead == ')') {
                System.out.println("factor "+exp);
                return exp;
            }
            else {
                System.out.println("factor");
                throw new ParseError();
            }
        }
        System.out.println("factor "+ evalDigit(this.lookahead));
        return evalDigit(this.lookahead);
    }

    public boolean num(int c) {
        return ('0' <= c && c <= '9');
    }
    
}

class ParseError extends Exception {
    public String getMessage() {
	    return "parse error";
    }
}

class Main {
    public static void main(String[] args) {
        try {
            Calculator c = new Calculator(System.in);
            System.out.println("result = " + c.eval());
        } catch (IOException | ParseError e) {
            System.err.println(e.getMessage());
        }
    }
}