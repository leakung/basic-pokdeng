import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        PokDeng pokDeng;
        pokDeng = new PokDeng();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress enter for play again or E for Exit");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if ((input.equals("E")) || (input.equals("e"))) {
                break;
            } else {
                pokDeng = new PokDeng();
            }
            System.out.println("\nPress enter for play again or E for Exit");
        }
    }
}
