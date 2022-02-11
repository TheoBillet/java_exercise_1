import java.util.*;

public class Predict implements Command {
    @Override
    public String name() {
        return "predict";
    }

    @Override
    public Boolean run(Scanner scanner) {
        System.out.println("Veuillez entrer un chemin vers un fichier :");
        String path = scanner.nextLine();
        String content = Freq.contentFile(path);
        if (Objects.isNull(content)) {
            return false;
        }
        List<String> predictSentence = new ArrayList<String>();
        String[] words = Freq.wordsFile(content);
        System.out.println("Veuillez entrer un mot pour commencer la prédiction :");
        String wordStart = scanner.nextLine();
        if (!Arrays.asList(words).contains(wordStart)) {
            System.out.println("Attention votre mot n'est pas dans votre fichier !");
            return false;
        }
        predictSentence.add(wordStart);
        while(predictSentence.size() < 20) {
            String predictWord = predictWord(predictSentence.get(predictSentence.size() - 1), words);
            if (Objects.isNull(predictWord)) {
                break;
            }
            predictSentence.add(predictWord);
        }
        predictSentence.forEach(word -> {
            System.out.print(word + " ");
        });
        System.out.println();
        return false;
    }
    public String predictWord(String word, String[] listWords) {
        String res;
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < listWords.length; i++) {
            if (listWords[i].equals(word) && (i + 1) != listWords.length) {
                words.add(listWords[i]);
            }
        }
        String[] arrayWords = new String[words.size()];
        arrayWords = words.toArray(arrayWords);
        res = Freq.mostWordUse(arrayWords).get(0);
        return res;
    }
}
