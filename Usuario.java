import java.io.IOException;

public class Usuario
{
    private String nome;
    private String senha;
    private double saldo = 10000;
    public Usuario(String nome, String senha) 
    {
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public void setSenha(String senha) 
    {
        this.senha = senha;
    }

    public double getSaldo() 
    {
        return saldo;
    }

    public void setSaldo(double saldo) 
    {
        this.saldo = saldo;
    }

    protected boolean login(String nome, String senha) throws IOException
    {
        String nomeUsuario = this.nome;
        String senhaUsuario = this.senha;

        try
        {
            if(nomeUsuario.equals(nome) && senhaUsuario.equals(senha))
            {
                return true;
            }
            return false;

        } catch(Exception e)
        {
            return false;
        }
    }
}
