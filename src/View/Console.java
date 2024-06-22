package View;
import java.util.Scanner;

public class Console {

    private static Scanner leitor = new Scanner(System.in);

    public static int lerInt() {
        try {
            int valor = leitor.nextInt();
            leitor.nextLine();
            return valor;
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro ao ler o inteiro: " + ex.getMessage());
            return 0; // Valor padrão em caso de erro
        }
    }

    public static String lerString() {
        try {
            return leitor.nextLine();
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro ao ler a string: " + ex.getMessage());
            return ""; // String vazia em caso de erro
        }
    }
}
