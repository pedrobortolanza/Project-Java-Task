import java.time.LocalDateTime;
import java.util.ArrayList;

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

    
}
