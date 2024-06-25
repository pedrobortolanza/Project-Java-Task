package model;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import controller.GerenciadorDeProdutos;

public class Compra 
{
    private double valorTotal;
    private static ArrayList<Produto> carrinho;
    private LocalDateTime dataHoraCompra;

    public Compra(double valorTotal, ArrayList<Produto> carrinho) 
    {
        this.valorTotal = valorTotal;
        Compra.carrinho = carrinho;
        this. dataHoraCompra = LocalDateTime.now();
    }

    public void comprar() throws Exception
    {
        try
        {
            ArrayList<Produto> listaProdutos = GerenciadorDeProdutos.listarProdutos();
           
            for(Produto produtoCarrinho : carrinho)
            {
                for(Produto produtoLista : listaProdutos)
                {
                    if(produtoCarrinho.equals(produtoLista))
                    {   
                        valorTotal = valorTotal + produtoLista.getValorProduto();
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

    public String lerCarrinho()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("Produtos no carrinho: \n");

        for(Produto produto : carrinho)
        {
            sb.append(produto.toString());
            sb.append("\n");
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == '\n') 
        {
                sb.deleteCharAt(sb.length() - 1);
        }
        if(carrinho.isEmpty())
        {
            return "\nCarrinho vazio";
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
        Compra.carrinho = carrinho;
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
        return lerCarrinho() + "Valor total: " + valorTotal + " Data e hora da compra: " + dataHoraCompra + "\n";
    }
    //Removi o From string pois não vejo necessidade de transfromar o histórico de compra em um array
}
