
public class Calculator {
    private char[] input;
    private this.result;

    public Calculator(char[] input) {
        this.input = input;
        this.result = 0;
    }

    private boolean validInput() {
        for (int i = 0; i < this.input.length; i++) {
            if ((this.input[i] >= 'a' && this.input[i] <= 'z') || (this.input[i] >= 'A' && this.input[i] <= 'Z')) {
                return false;
            }
        }
        return true;
    }

    private int Exp(int result) {
        if (this.input[0] == '+' || this.input[0]=='-' || this.input[0] == '*' || this.input[0] == '/' || this.input[0] == ')')
            throw new ParseError();
        result = Term(result);
        result = Exp2(result);
        return result;
    }

}