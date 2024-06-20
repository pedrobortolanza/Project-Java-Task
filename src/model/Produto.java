package model;

public class Produto 
{
    private String nomeProduto;
    private double valorProduto;
    private int quantidadeEstoque;

    public Produto(String nomeProduto, double valorProduto, int quantidadeEstoque) 
    {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    private void comprar(Produto produto, Usuario usuario) throws Exception
    {
        double saldo = usuario.getSaldo();
        if(produto.getValorProduto() <= saldo)
        {
            saldo = saldo - produto.getValorProduto();
            usuario.setSaldo(saldo);
            return;
        }
        throw new Exception("Saldo insuficiente");
    }

    public String getNomeProduto() 
    {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) 
    {
        this.nomeProduto = nomeProduto;
    }

    public double getValorProduto() 
    {
        return valorProduto;
    }

    public void setValorProduto(double valorProduto) 
    {
        this.valorProduto = valorProduto;
    }

    public int getQuantidadeEstoque() 
    {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(int quantidadeEstoque) 
    {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    @Override
    public String toString() 
    {
        return "\nNome do Produto: " + nomeProduto + ", " + 
               "Valor: " + valorProduto + ", " + 
               "Quantidade em estoque: " + quantidadeEstoque;
    }

    public static Produto fromString(String linha)
    {
        String[] informacoesProduto = linha.split(", ");

        return new Produto(informacoesProduto[0],Double.parseDouble(informacoesProduto[1]), Integer.parseInt(informacoesProduto[2]));
    }
}
