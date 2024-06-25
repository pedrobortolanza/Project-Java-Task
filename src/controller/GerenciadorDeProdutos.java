package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import model.Produto;

public abstract class GerenciadorDeProdutos 
{
    private static final String ARQUIVO = "produtos.txt";

    public static void cadastrarProduto(Produto produto) throws IOException {

        try (FileWriter fw = new FileWriter(ARQUIVO, true);
             BufferedWriter bw = new BufferedWriter(fw)) {

            bw.write(produto.toString());

        }
    }

    public static void reescreverArquivo(ArrayList<Produto> listaProdutos) throws IOException 
    {

        try (FileWriter fw = new FileWriter(ARQUIVO); BufferedWriter bw = new BufferedWriter(fw)) 
        {
            for(Produto produto : listaProdutos)
            {
                bw.write(produto.toString());
            }
        }
    }

    public static ArrayList<Produto> listarProdutos() throws IOException, Exception {

        ArrayList<Produto> listaProdutos = new ArrayList<>();

        try (FileReader fr = new FileReader(ARQUIVO);
             BufferedReader br = new BufferedReader(fr)) {

                String linha;
                while ((linha = br.readLine()) != null) {
                    
                    Produto produto = Produto.fromString(linha);
                    listaProdutos.add(produto);
                }
        } 

        if (listaProdutos.isEmpty()) {
            throw new Exception("\nNão há produtos cadastrados");
        }

        return listaProdutos;

    }

    public static Produto buscarProduto(String nomeProduto) throws Exception {

        ArrayList<Produto> listaProdutos = listarProdutos();

        for (Produto produto : listaProdutos) {

            if(produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {

                return produto;
            }
        }

        throw new Exception("\nProduto com o nome '" + nomeProduto + "' não localizado!\n");

    }

    public static void excluirProduto(String nomeProduto) throws Exception{

        ArrayList<Produto> listaProdutos = listarProdutos();       
    
        boolean encontrou = false;
        for (Produto produto : listaProdutos) {

            if(produto.getNomeProduto().equalsIgnoreCase(nomeProduto)) {
                listaProdutos.remove(produto);
                encontrou = true;
                break;
            }
        }

        if (!encontrou) {
            throw new Exception("\nProduto com o nome '" + nomeProduto + "' não localizado!\n");
        }

        try (FileWriter fw = new FileWriter(ARQUIVO);
             BufferedWriter bw = new BufferedWriter(fw)) {

            for (Produto produto : listaProdutos) {

                bw.write(produto.getNomeProduto() + "," +
                         produto.getValorProduto() + "\n");
            }
        }
    }

}