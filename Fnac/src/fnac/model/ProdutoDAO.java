package fnac.model;

import fnac.ConexaoBanco;
import fnac.model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    
    public boolean inserir(Produto produto)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("INSERT INTO produto(nome,marca,estoque,preco) VALUES(?,?,?,?)");
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getMarca());
            preparedStatement.setInt(3, produto.getEstoque());
            preparedStatement.setDouble(4, produto.getPreco());
            preparedStatement.execute();
            System.out.println("Produto inserido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir produto: "+ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public boolean atualizar(Produto produto)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("UPDATE produto SET nome = ?, marca = ?, estoque = ?, preco = ? WHERE id = ?");
            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getMarca());
            preparedStatement.setInt(3, produto.getEstoque());
            preparedStatement.setDouble(4, produto.getPreco());
            preparedStatement.setInt(5, produto.getId());
            preparedStatement.execute();
            System.out.println("Produto atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar produto: "+ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public boolean remover(int id)
    {
         Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("DELETE FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Produto removido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao remover produto: "+ex.getMessage());
            sucesso = false;
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return sucesso;
    }
    
    public Produto buscar(int id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Produto produto = null;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM produto WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null && resultSet.first()) {
                produto = new Produto();
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setMarca(resultSet.getString(3));
                produto.setEstoque(resultSet.getInt(4));
                produto.setPreco(resultSet.getDouble(5));
            } 
            System.out.println("Produto buscado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar produto: "+ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return produto;
    }
    
    public List<Produto> listar()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM produto");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setMarca(resultSet.getString(3));
                produto.setEstoque(resultSet.getInt(4));
                produto.setPreco(resultSet.getDouble(5));
                produtos.add(produto);
            } 
            System.out.println("Produtos listados com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao listar Produtos: "+ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return produtos;
    }
    
    public List<Produto> buscarPorNome(String nome)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Produto> produtos = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM produto WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nome + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Produto produto = new Produto();
                produto.setId(resultSet.getInt(1));
                produto.setNome(resultSet.getString(2));
                produto.setMarca(resultSet.getString(3));
                produto.setEstoque(resultSet.getInt(4));
                produto.setPreco(resultSet.getDouble(5));
                produtos.add(produto);
            } 
            System.out.println("Produto buscado por nome com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Produto por nome: "+ex.getMessage());
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Erro ao fechar as conexões: "+ex.getMessage());
            }
        }
        return produtos;
    }
}
