import java.util.ArrayList;
import java.util.Scanner;

/**
 * Command-line demos of the stack and array operations from the website.
 * Run this Java program separately; it is not used by the browser page.
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<String> stack = new ArrayList<>();
        String[] array = null;
        int arrayLength = 0;
        String mode = "stack";

        try (Scanner scanner = new Scanner(System.in)) {
            printHelp();

            while (true) {
                System.out.print("(" + mode + ") > ");
                if (!scanner.hasNextLine()) {
                    break;
                }

                String line = scanner.nextLine().trim();
                if (line.equalsIgnoreCase("exit")) {
                    break;
                } else if (line.equalsIgnoreCase("help")) {
                    printHelp();
                } else if (line.equalsIgnoreCase("stack")) {
                    mode = "stack";
                    System.out.println("Switched to stack mode.");
                } else if (line.toLowerCase().startsWith("array ")) {
                    String[] parts = line.split("\\s+");
                    try {
                        int capacity = Integer.parseInt(parts[1]);
                        if (capacity < 1 || capacity > 12) {
                            System.out.println("Array size must be between 1 and 12.");
                        } else {
                            array = new String[capacity];
                            arrayLength = 0;
                            mode = "array";
                            System.out.println("Created an empty array with size " + capacity + ".");
                        }
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Use: array <size>, for example: array 5");
                    }
                } else if (line.equalsIgnoreCase("show")) {
                    if (mode.equals("stack")) {
                        System.out.println("Stack (bottom to top): " + stack);
                    } else if (array == null) {
                        System.out.println("Create an array first with: array <size>");
                    } else {
                        System.out.println("Array (index: value):");
                        for (int i = 0; i < array.length; i++) {
                            String value = i < arrayLength ? array[i] : "empty";
                            System.out.println("  " + i + ": " + value);
                        }
                    }
                } else if (mode.equals("stack") && line.equalsIgnoreCase("pop")) {
                    if (stack.isEmpty()) {
                        System.out.println("The stack is empty (underflow).");
                    } else {
                        System.out.println("Popped: " + stack.remove(stack.size() - 1));
                    }
                } else if (mode.equals("stack") && line.toLowerCase().startsWith("push ")) {
                    String value = line.substring(5).trim();
                    if (value.isEmpty()) {
                        System.out.println("Give push a value, for example: push Mars");
                    } else {
                        stack.add(value);
                        System.out.println("Pushed: " + value);
                    }
                } else if (mode.equals("array") && line.toLowerCase().startsWith("insert ")) {
                    if (insertIntoArray(line, array, arrayLength)) {
                        arrayLength++;
                    }
                } else if (mode.equals("array") && line.toLowerCase().startsWith("delete ")) {
                    if (array == null) {
                        System.out.println("Create an array first with: array <size>");
                    } else {
                        String[] parts = line.split("\\s+");
                        try {
                            int index = Integer.parseInt(parts[1]);
                            if (index < 0 || index >= arrayLength) {
                                System.out.println("Choose an occupied index from 0 to " + (arrayLength - 1) + ".");
                            } else {
                                String removed = array[index];
                                for (int i = index; i < arrayLength - 1; i++) {
                                    array[i] = array[i + 1];
                                }
                                array[--arrayLength] = null;
                                System.out.println("Deleted " + removed + " from index " + index + ".");
                            }
                        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                            System.out.println("Use: delete <index>, for example: delete 0");
                        }
                    }
                } else {
                    System.out.println("That command is not available in " + mode + " mode. Type help to see commands.");
                }
            }
        }

        System.out.println("Demo ended.");
    }

    private static boolean insertIntoArray(String line, String[] array, int length) {
        if (array == null) {
            System.out.println("Create an array first with: array <size>");
            return false;
        }
        if (length == array.length) {
            System.out.println("The array is full. Delete an item before inserting.");
            return false;
        }

        String[] parts = line.split("\\s+", 3);
        if (parts.length < 3) {
            System.out.println("Use: insert <index> <value>, for example: insert 0 Mars");
            return false;
        }
        try {
            int index = Integer.parseInt(parts[1]);
            if (index < 0 || index > length) {
                System.out.println("Insert index must be from 0 to " + length + ".");
                return false;
            }
            for (int i = length; i > index; i--) {
                array[i] = array[i - 1];
            }
            array[index] = parts[2];
            System.out.println("Inserted " + parts[2] + " at index " + index + ".");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Index must be a number.");
            return false;
        }
    }

    private static void printHelp() {
        System.out.println("Space Data Structures - Java Demo");
        System.out.println("Commands:");
        System.out.println("  push <value>             Push a value onto the stack");
        System.out.println("  pop                      Remove the top stack value");
        System.out.println("  stack                    Switch to stack mode");
        System.out.println("  array <size>             Create an array (size 1-12) and switch to it");
        System.out.println("  insert <index> <value>   Insert a value into the array");
        System.out.println("  delete <index>           Delete an array value at an index");
        System.out.println("  show                     Display the active structure");
        System.out.println("  help                     Show these commands");
        System.out.println("  exit                     Quit the demo");
    }
}
