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
        String content = contentFile(path);
        if (Objects.isNull(content)) {
            return false;
        }
        String[] words = wordsFile(content);
        List<String> popularWords = mostWordUse(words);
        System.out.println("Mots les plus utilisés :");
        popularWords.forEach(word -> {
            System.out.println(word);
        });
        return false;
    }

    public static String contentFile(String path) {
        Path filePath = Paths.get(path);
        try {
            return Files.readString(filePath);
        }
        catch (Exception e) {
            System.out.println("Unreadable file: " + e.getClass().getSimpleName() + " " + e.getMessage());
            return null;
        }
    }

    public static String[] wordsFile(String content) {
        String[] res = new String[0];
        if (content.isBlank()) {
            System.out.println("Votre fichier est vide !");
            return res;
        } else {
            res = content.replaceAll("[,.!?\\-'\"\\n]", " ").toLowerCase().split("\\s");
            return res;
        }
    }

    public static List<String> mostWordUse(String[] words) {
        List<String> occ = Arrays.stream(words)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .map(Map.Entry::getKey)
                .limit(3)
                .collect(Collectors.toList());
        return occ;
    }
}
