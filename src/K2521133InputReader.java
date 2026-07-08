import java.util.Scanner;

// Small wrapper over Scanner that keeps asking until the input is valid, so the
// rest of the program never has to deal with bad menu numbers or empty names.
public class K2521133InputReader {

    private final Scanner scanner;

    public K2521133InputReader(Scanner scanner) {
        this.scanner = scanner;
    }

    public String readText(String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    public int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();
            try {
                int number = Integer.parseInt(value);
                if (number >= min && number <= max) {
                    return number;
                }
            } catch (NumberFormatException ignored) {
                // shared message below
            }
            System.out.println("Please enter a number between " + min + " and " + max + ".");
        }
    }

    public boolean readYesNo(String prompt) {
        while (true) {
            System.out.print(prompt + " (y/n): ");
            String value = scanner.nextLine().trim().toLowerCase();
            if (value.equals("y") || value.equals("yes")) {
                return true;
            }
            if (value.equals("n") || value.equals("no")) {
                return false;
            }
            System.out.println("Please answer y or n.");
        }
    }
}
