import java.util.InputMismatchException;
import java.util.Scanner;

public class Fibo implements  Command {
    @Override
    public String name() {
        return "fibo";
    }

    @Override
    public Boolean run(Scanner scanner) {
        int nb = 0;
        while(true) {
            try {
                System.out.println("Veuillez entrer un nombre :");
                nb = scanner.nextInt();
                break;
            } catch (InputMismatchException e) {
                System.err.println("Mauvaise entrée !");
                scanner.nextLine();
            }
        }
        int res = fiboCalcul(nb);
        System.out.println("F(" + nb + ") = " + res);
        scanner.nextLine();
        return false;
    }

    private static int fiboCalcul(int nb) {
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
}
