import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Freq implements  Command {
    @Override
    public String name() {
        return "freq";
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Veuillez entrer un chemin vers un fichier :");
        String path = scanner.nextLine();
        Path filePath = Paths.get(path);
        try {
            String content = Files.readString(filePath);
            if (content.isBlank()) {
                System.out.println("Votre fichier est vide !");
            } else {
                String[] words = content.replaceAll("[.!?\\-'\"\\n]", " ").toLowerCase().split("\\s");
                List<String> occ = Arrays.stream(words)
                        .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                        .entrySet().stream()
                        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                        .map(Map.Entry::getKey)
                        .limit(3)
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
        return false;
    }
}
