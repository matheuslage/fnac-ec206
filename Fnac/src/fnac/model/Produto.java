package fnac.model;

public class Produto {
    private int id;
    private String nome;
    private String marca;
    private int estoque;
    private double preco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double price) {
        this.preco = price;
    }
    
    
    
    @Override
    public String toString() {
        return nome + " | ID: " + id;
    }
}
