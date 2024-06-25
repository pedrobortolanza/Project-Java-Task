package model;

public class Produto 
{
    private String nomeProduto;
    private double valorProduto;
    

    public Produto(String nomeProduto, double valorProduto) 
    {
        this.nomeProduto = nomeProduto;
        this.valorProduto = valorProduto;
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

    @Override
    public String toString() 
    {
        return nomeProduto + ", " + 
               valorProduto + "\n";
    }

    public static Produto fromString(String linha)
    {
        String[] informacoesProduto = linha.split(", ");

        return new Produto(informacoesProduto[0],Double.parseDouble(informacoesProduto[1]));
    }
}
