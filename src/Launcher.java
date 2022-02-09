import java.util.Scanner;

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
            }else {
                System.out.println("Unknown command");
            }
            System.out.println("Entrez votre commande :");
            command = scanner.nextLine();
        }
    }
}
