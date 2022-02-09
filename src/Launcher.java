import java.util.Scanner;

public class Launcher {
    public static void main(String[] args) {
        System.out.println("Bienvenue");
        Scanner scanner = new Scanner(System.in);
        while (!scanner.nextLine().equals("quit")) {
            System.out.println("Unknown command");
        }
    }
}
