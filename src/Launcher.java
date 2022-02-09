import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Launcher {

    public static int fibo(int nb) {
        int nb1 = 0;
        int nb2 = 1;
        int res = 0;
        for(int i = 0; i <= nb; i++) {
            if (i < 2) {
                res = i;
            } else {
                int temp = nb1 + nb2;
                res = temp;
                nb1 = nb2;
                nb2 = temp;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println("Bienvenue");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entrez votre commande :");
        String command = scanner.nextLine();
        while (!command.equals("quit")) {
            if (command.equals("fibo")) {
                System.out.println("Veuillez entrer un nombre :");
                int nb = scanner.nextInt();
                int res = fibo(nb);
                System.out.println("F(" + nb + ") = " + res);
                command = scanner.nextLine();
            } else if (command.equals("freq")) {
                System.out.println("Veuillez entrer un chemin vers un fichier :");
                String path = scanner.nextLine();
                Path filePath = Paths.get(path);
                try {
                    String content = Files.readString(filePath);
                    if (content.isBlank()) {
                        System.out.println("Votre fichier est vide !");
                    } else {
                        String[] words = content.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                        List<String> occ = Arrays.stream(words)
                                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                                .entrySet().stream()
                                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                                .limit(3)
                                .map(Map.Entry::getKey)
                                .collect(Collectors.toList());
                        System.out.println("Mots les plus utilisés :");
                        occ.forEach(word -> {
                            System.out.println(word);
                        });
                    }
                }
                catch (Exception e) {
                    System.out.println("Unreadable file: " + e.getClass().getSimpleName() + " " + e.getMessage());
                }
            } else {
                System.out.println("Unknown command");
            }
            System.out.println("Entrez votre commande :");
            command = scanner.nextLine();
        }
    }
}
