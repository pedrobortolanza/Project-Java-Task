import view.Console;
import view.Sistema;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Realizando login
        Sistema sistema = realizarLogin();

        while (true) {
            exibirMenuPrincipal();
            int opcao = Console.lerInt();

            switch (opcao) {
                case 1:
                    cadastrarProduto(sistema);
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
                    buscarProduto(sistema);
                    break;
                case 6:
                    excluirProduto(sistema);
                    break;
                case 7:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    private static Sistema realizarLogin() {
        while (true) {
            System.out.print("Nome de usuário: ");
            String nome = Console.lerString();
            System.out.print("Senha: ");
            String senha = Console.lerString();

            Sistema sistema = new Sistema(nome, senha);
            try {
                if (sistema.usuario.login(nome, senha)) {
                    System.out.println("Login realizado com sucesso!");
                    return sistema;
                } else {
                    System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao realizar login: " + e.getMessage());
            }
        }
    }

    private static void cadastrarProduto(Sistema sistema) {
        try {
            System.out.print("Nome do Produto: ");
            String nome = Console.lerString();
            System.out.print("Valor do Produto: ");
            double valor = Console.lerFloat();
            System.out.print("Quantidade em Estoque: ");
            int quantidade = Console.lerInt();

            sistema.cadastrarProduto(nome, valor, quantidade);
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    private static void buscarProduto(Sistema sistema) {
        try {
            System.out.print("Nome do Produto a buscar: ");
            String nomeProduto = Console.lerString();
            sistema.buscarProduto(nomeProduto);
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
    }

    private static void excluirProduto(Sistema sistema) {
        try {
            System.out.print("Nome do Produto a excluir: ");
            String nomeProduto = Console.lerString();
            sistema.excluirProduto(nomeProduto);
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\n--- Sistema de Mercado ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Realizar Compra");
        System.out.println("4. Ver Histórico de Compras");
        System.out.println("5. Buscar Produto");
        System.out.println("6. Excluir Produto");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }
}



