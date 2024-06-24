package view;

import controller.GerenciadorDeProdutos;
import controller.HistoricoDeCompras;
import model.Compra;
import model.Produto;
import model.Usuario;

import java.util.ArrayList;

public class Sistema {

    private static Usuario usuario = new Usuario("admin", "123");

    public static boolean loginSistema() {
        while (true) {
            System.out.print("Nome de usuário: ");
            String nome = Console.lerString();
            System.out.print("Senha: ");
            String senha = Console.lerString();

            try {
                if (usuario.login(nome, senha)) {
                    System.out.println("Administrador logado com sucesso!");
                    return true;
                } else {
                    System.out.println("Usuário logado com sucesso!.");
                    return false;
                }
            } catch (Exception e) {
                System.out.println("Erro ao realizar login: " + e.getMessage());
            }
        }
    }

    public static void cadastrarProduto() {
        try {
            System.out.print("Nome do Produto: ");
            String nome = Console.lerString();
            System.out.print("Valor do Produto: ");
            double valor = Console.lerFloat();
            System.out.print("Quantidade em Estoque: ");
            int quantidade = Console.lerInt();

            Produto produto = new Produto(nome, valor, quantidade);
            GerenciadorDeProdutos.cadastrarProduto(produto);

            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public static void listarProdutos() {
        try {
            ArrayList<Produto> produtos = GerenciadorDeProdutos.listarProdutos();
            System.out.println("\n--- Lista de Produtos ---");

            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    private static void excluirProduto() 
    {
        System.out.println("\nExcluir Produto");
        System.out.print("Nome do Produto: ");
        String nomeProduto = Console.lerString();
        try 
        {

            GerenciadorDeProdutos.excluirProduto(nomeProduto);
            System.out.println("\nProduto excluído com sucesso!");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void buscarProduto() {

        System.out.println("\nBuscar Produtos");
        System.out.print("Nome do Produto: ");
        String nomeProduto = Console.lerString();

        try {

            Produto produto = GerenciadorDeProdutos.buscarProduto(nomeProduto);

            if (produto != null) {
               System.out.println("\nProduto: " + produto);
            } else {
                System.out.println("\nProduto não encontrado.\n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void realizarCompra() {
        try {
            Compra compra = new Compra(0, new ArrayList<>());

            while (true) {
                exibirMenuCompra();
                int opcao = Console.lerInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Nome do Produto: ");
                        String nomeProduto = Console.lerString();
                        compra.adicionarNoCarrinho(nomeProduto);
                        break;
                    case 2:
                        System.out.print("Nome do Produto: ");
                        String nomeProdutoRemover = Console.lerString();
                        compra.removerDoCarrinho(nomeProdutoRemover);
                        break;
                    case 3:
                        compra.comprar(usuario);
                        HistoricoDeCompras.salvarCompra(compra);
                        System.out.println("Compra realizada com sucesso!");
                        return;
                    case 4:
                        System.out.println("Compra cancelada.");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao realizar compra: " + e.getMessage());
        }
    }

    public static void listarCompras() {
        System.out.println("\n--- Histórico de Compras ---");
        try {
            System.out.println(HistoricoDeCompras.listarCompras());

        } catch (Exception e) {
            System.out.println("\nErro ao listar as Compras");
        }
    }

    private static void exibirMenuCompra() {
        System.out.println("\n--- Menu de Compra ---");
        System.out.println("1. Adicionar Produto ao Carrinho");
        System.out.println("2. Remover Produto do Carrinho");
        System.out.println("3. Finalizar Compra");
        System.out.println("4. Cancelar");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirMenuAdmin() {
        System.out.println("\n--- Sistema de Mercado ---");
        System.out.println("1. Cadastrar Produto");
        System.out.println("2. Buscar Produtos");
        System.out.println("3. Excluir Produtos");
        System.out.println("4. Listar Produtos");
        System.out.println("5. Realizar Compra");
        System.out.println("6. Ver Histórico de Compras");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void exibirMenuUsuario() {
        System.out.println("\n--- Sistema de Mercado ---");
        System.out.println("1. Buscar Produtos");
        System.out.println("2. Listar Produtos");
        System.out.println("3. Realizar Compra");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }

    public static void executar() {
        if (loginSistema()) {
            while (true) {
                exibirMenuAdmin();
                int opcao = Console.lerInt();

                switch (opcao) {
                    case 1:
                        cadastrarProduto();
                        break;
                    case 2:
                        buscarProduto();
                        break;
                    case 3:
                        excluirProduto();
                        break;
                    case 4:
                        listarProdutos();
                        break;
                    case 5:
                        realizarCompra();
                        break;
                    case 6:
                        listarCompras();
                        break;
                    case 7:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        } else {
            while (true) {
                exibirMenuUsuario();
                int opcao = Console.lerInt();

                switch (opcao) {
                    case 1:
                        buscarProduto();
                        break;
                    case 2:
                        listarProdutos();
                        break;
                    case 3:
                        realizarCompra();
                        break;
                    case 4:
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }

}
