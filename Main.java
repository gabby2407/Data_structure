import java.util.ArrayList;
import java.util.Scanner;

/**
 * A small Java command-line companion to the Space Data Structures website.
 * It demonstrates the same stack operations (push and pop) without changing
 * or being required by the HTML/CSS/JavaScript website.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> stack = new ArrayList<>();

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Space Data Structures - Java Stack Demo");
            System.out.println("Commands: push <value>, pop, show, exit");

            while (true) {
                System.out.print("> ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                } else if (line.equalsIgnoreCase("pop")) {
                    if (stack.isEmpty()) {
                        System.out.println("The stack is empty (underflow).");
                    } else {
                        System.out.println("Popped: " + stack.remove(stack.size() - 1));
                    }
                } else if (line.equalsIgnoreCase("show")) {
                    System.out.println("Stack (bottom to top): " + stack);
                } else if (line.toLowerCase().startsWith("push ")) {
                    String value = line.substring(5).trim();
                    if (value.isEmpty()) {
                        System.out.println("Give push a value, for example: push Mars");
                    } else {
                        stack.add(value);
                        System.out.println("Pushed: " + value);
                    }
                } else {
                    System.out.println("Unknown command. Try push <value>, pop, show, or exit.");
                }
            }
        }

        System.out.println("Demo ended.");
    }
}
