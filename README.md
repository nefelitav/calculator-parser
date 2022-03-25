## LL(1) Calculator Parser

This program calculates arithmetic expressions supporting :
- bitwise AND
- bitwise XOR
- parentheses

The interesting fact about this calculator is that it is based on a recursive descent parser.

These are the steps followed to reach this goal:
- First of all, I had to convert the given grammar of the calculator language into an LL(1) grammar, so that it is not ambiguous and thus the calculator produces correct results always. This was accomplished by defining priority between the two operators, changing the order of their execution, and by removing the left recursion for LL parsing, using more nonterminals.
- Then, I found the FIRST+ & FOLLOW sets for the LL(1) grammar, which helped me produce a lookahead table, to consult while building the calculator.
- Finally, I built the calculator, according to the LL(1) grammar productions, showing parse error messages when needed.

### Compile & Run
```
$ javac Main.java && java Main
```