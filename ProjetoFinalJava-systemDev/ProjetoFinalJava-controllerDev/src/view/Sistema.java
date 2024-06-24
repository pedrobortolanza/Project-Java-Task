package view;

import controller.GerenciadorDeProdutos;
import controller.HistoricoDeCompras;
import model.Compra;
import model.Produto;
import model.Usuario;

import java.util.ArrayList;

public class Sistema {

    public Usuario usuario;

    public Sistema(String nome, String senha) {
        this.usuario = new Usuario(nome, senha);
    }

    public void cadastrarProduto(String nome, double valor, int quantidade) {
        try {
            Produto produto = new Produto(nome, valor, quantidade);
            GerenciadorDeProdutos.cadastrarProduto(produto);
            System.out.println("Produto cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar produto: " + e.getMessage());
        }
    }

    public void listarProdutos() {
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

    public void realizarCompra() {
        try {
            Compra compra = new Compra(0, new ArrayList<>());

            while (true) {
                exibirMenuCompra();
                int opcao = Console.lerInt();

                switch (opcao) {
                    case 1:
                        System.out.print("Nome do Produto: ");
                        String nomeProduto = Console.lerString();
                        adicionarProdutoNoCarrinho(nomeProduto);
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

    public void buscarProduto(String nomeProduto) {
        try {
            Produto produto = GerenciadorDeProdutos.buscarProduto(nomeProduto);
            System.out.println("\nProduto encontrado:\n" + produto);
        } catch (Exception e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
    }

    public void excluirProduto(String nomeProduto) {
        try {
            GerenciadorDeProdutos.excluirProduto(nomeProduto);
            System.out.println("Produto excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
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

    private void exibirMenuCompra() {
        System.out.println("\n--- Menu de Compra ---");
        System.out.println("1. Adicionar Produto ao Carrinho");
        System.out.println("2. Remover Produto do Carrinho");
        System.out.println("3. Finalizar Compra");
        System.out.println("4. Cancelar");
        System.out.print("Escolha uma opção: ");
    }

    private void adicionarProdutoNoCarrinho(String nomeProduto) {
        try {
            Compra.adicionarNoCarrinho(nomeProduto);
        } catch (Exception e) {
            System.out.println("Erro ao adicionar produto ao carrinho: " + e.getMessage());
        }
    }
}
