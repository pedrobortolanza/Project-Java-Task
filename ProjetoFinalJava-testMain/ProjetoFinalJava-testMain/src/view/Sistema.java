package view;

import controller.GerenciadorDeProdutos;
import controller.HistoricoDeCompras;
import model.Compra;
import model.Produto;
import model.Usuario;

import java.util.ArrayList;

public class Sistema {

    private Usuario usuario;

    public Sistema() {
        while (true) {
            System.out.print("Nome de usuário: ");
            String nome = Console.lerString();
            System.out.print("Senha: ");
            String senha = Console.lerString();

            this.usuario = new Usuario(nome, senha);
            try {
                if (this.usuario.login(nome, senha)) {
                    System.out.println("Login realizado com sucesso!");
                    break;
                } else {
                    System.out.println("Nome de usuário ou senha incorretos. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro ao realizar login: " + e.getMessage());
            }
        }
    }

    public void cadastrarProduto() {
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

    public void exibirHistoricoCompras() {
        try {
            ArrayList<Compra> compras = HistoricoDeCompras.listarCompras();
            System.out.println("\n--- Histórico de Compras ---");
            for (Compra compra : compras) {
                System.out.println(compra);
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar histórico de compras: " + e.getMessage());
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
}
