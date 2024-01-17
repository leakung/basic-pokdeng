import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        PokDeng pokDeng;
        pokDeng = new PokDeng();
        Scanner sc = new Scanner(System.in);
        System.out.println("\nPress P for Play or E for Exit");
        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if ((input.equals("P")) || (input.equals("p"))) {
                pokDeng = new PokDeng();
            } else if ((input.equals("E")) || (input.equals("e"))) {
                break;
            }
            System.out.println("\nPress P for Play or E for Exit");
        }
    }
}
