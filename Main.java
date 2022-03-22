import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner s;
        String input;
        char[] charInput;

        while(true)
        {
            System.out.println("Enter input:");
            s = new Scanner(System.in);
            input = s.nextLine();
            if (input.equals("exit")) {
                break;
            }
            charInput = input.toCharArray();
            //System.out.println("Input is: " + input + "\n");  // print user input
            
            Calculator c = new Calculator(charInput);
        }
    }
}

// System.err.println("Invalid number of arguments");
// System.exit(1);
// catch (FileNotFoundException e) {
    // System.err.println(e.getMessage());
    // System.exit(2);
// }
// catch (IOException e){
    // System.err.println(e.getMessage());
    // System.exit(2);
// }

// try{
// }
// catch(Exception ex){
//     System.out.println("Caught Exception.");
// }
// finally {
//     System.out.println("Gracefully terminating...");
// }