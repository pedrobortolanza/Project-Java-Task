import view.Console;
import view.Sistema;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();

        while (true) {
            exibirMenuPrincipal();
            int opcao = Console.lerInt();

            switch (opcao) {
                case 1:
                    sistema.cadastrarProduto();
                    break;
                case 2:
                    sistema.listarProdutos();
                    break;
                case 3:
                    sistema.realizarCompra();
                    break;
                case 4:
                    Sistema.listarCompras();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Mercado ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Realizar Compra");
        System.out.println("4. Ver Histórico de Compras");
        System.out.println("5. Sair");
        System.out.print("Escolha uma opção: ");
    }
}
