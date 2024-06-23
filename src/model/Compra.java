package model;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import controller.GerenciadorDeProdutos;

public class Compra 
{
    private double valorTotal;
    private ArrayList<Produto> carrinho;
    private LocalDateTime dataHoraCompra;

    public Compra(double valorTotal, ArrayList<Produto> carrinho) 
    {
        this.valorTotal = valorTotal;
        this.carrinho = carrinho;
        this. dataHoraCompra = LocalDateTime.now();
    }

    private void comprar(Usuario usuario) throws Exception
    {
        try
        {
            ArrayList<Produto> listaProdutos = GerenciadorDeProdutos.listarProdutos();
            double saldo = usuario.getSaldo();
           
            for(Produto produtoCarrinho : carrinho)
            {
                for(Produto produtoLista : listaProdutos)
                {
                    if(produtoCarrinho.equals(produtoLista))
                    {   
                        if(produtoLista.getQuantidadeEstoque() > 0)
                        {
                            if(saldo >= produtoLista.getValorProduto())
                            {
                                double valorProduto = produtoLista.getValorProduto();
                                double resultado = saldo - valorProduto;

                                usuario.setSaldo(resultado);
                                valorTotal = valorTotal + valorProduto;
                                produtoLista.setQuantidadeEstoque(produtoLista.getQuantidadeEstoque() - 1);
                            }
                            else
                            {
                                throw new Exception("Usuário está sem saldo!");
                            }
                        }
                        else
                        {
                            throw new Exception("O produto está sem estoque.\n");
                        }
                    }
                }
            }
            GerenciadorDeProdutos.reescreverArquivo(listaProdutos);
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void adicionarNoCarrinho(String nomeProduto) throws Exception
    {
        try
        {   
            ArrayList<Produto> listaProdutos = GerenciadorDeProdutos.listarProdutos();

            for(Produto produto : listaProdutos)
            {
                if(produto.getNomeProduto().equalsIgnoreCase(nomeProduto))
                {
                    carrinho.add(produto);
                    return;
                }
            }
            throw new Exception("O produto que deseja não está cadastrado ou não se encontra mais no estoque");
        }
         catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    public void removerDoCarrinho(String nomeProduto) throws IOException, Exception
    {
        for(Produto produto : carrinho)
        {
            if(produto.getNomeProduto().equalsIgnoreCase(nomeProduto))
            {
                carrinho.remove(produto);
                return;
            }
        }
        throw new Exception( nomeProduto + "não presente no carrinho.");
    }

    private String lerCarrinho()
    {
        StringBuilder sb = new StringBuilder();

        for(Produto produto : carrinho)
        {
            sb.append("Produtos no carrinho: \n");
            sb.append(produto.toString());
            sb.append("\n");
        }
        return sb.toString();
    }

    public double getValorTotal() 
    {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) 
    {
        this.valorTotal = valorTotal;
    }

    public ArrayList<Produto> getCarrinho() 
    {
        return carrinho;
    }

    public void setCarrinho(ArrayList<Produto> carrinho) 
    {
        this.carrinho = carrinho;
    }

    public LocalDateTime getDataHoraCompra() 
    {
        return dataHoraCompra;
    }

    public void setDataHoraCompra(LocalDateTime dataHoraCompra) 
    {
        this.dataHoraCompra = dataHoraCompra;
    }

    @Override
    public String toString() 
    {
        return lerCarrinho() + "Valor total: " + valorTotal + "Data e hora da compra: " + dataHoraCompra;
    }
    //Removi o From string pois não vejo necessidade de transfromar o histórico de compra em um array
}
