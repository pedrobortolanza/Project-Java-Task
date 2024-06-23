package view;
import java.util.InputMismatchException;
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
            return 0;
        }
    }

    public static String lerString() {
        try {
            return leitor.nextLine();
        } catch (Exception ex) {
            System.out.println("Ocorreu um erro ao ler a string: " + ex.getMessage());
            return ""; 
        }
    }
    public static float lerFloat() {
        float valor = 0;

        while (true) {
            try {
                valor = leitor.nextFloat();
                leitor.nextLine();
                break;

            } catch (InputMismatchException e) {
                leitor.nextLine();
                System.out.println("O valor informado não é um 'float'. Digite novamente: ");
            }
        }
        return valor;
    }

}
