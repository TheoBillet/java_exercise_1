import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String clientCommand;
        boolean shouldStop = false;
        List<Command> commands = List.of(
                new Quit(),
                new Fibo(),
                new Freq(),
                new Predict()
        );

        do {
            System.out.println("Entrez votre commande :");
            clientCommand = scanner.nextLine();
            boolean found = false;
            for (Command command : commands) {
                if (command.name().equals(clientCommand)) {
                    shouldStop = command.run(scanner);
                    found = true;
                }
            }
            if (!found){
                System.out.println("Unknown command");
            }
        } while (!shouldStop);
    }
}
