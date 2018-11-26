package fnac.model;

import fnac.ConexaoBanco;
import fnac.model.Funcionario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO {
    
    public boolean inserir(Funcionario funcionario)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("INSERT INTO funcionario(login,senha,nome,cpf,telefone) VALUES(?,?,?,?,?)");
            preparedStatement.setString(1, funcionario.getLogin());
            preparedStatement.setString(2, funcionario.getSenha());
            preparedStatement.setString(3, funcionario.getNome());
            preparedStatement.setString(4, funcionario.getCpf());
            preparedStatement.setInt(5, funcionario.getTelefone());
            preparedStatement.execute();
            System.out.println("Funcionario inserido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir Funcionario: "+ex.getMessage());
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
    
    public boolean atualizar(Funcionario funcionario)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("UPDATE funcionario SET login = ?, senha = ?, nome = ?, cpf = ?, telefone = ? WHERE id = ?");
            preparedStatement.setString(1, funcionario.getLogin());
            preparedStatement.setString(2, funcionario.getSenha());
            preparedStatement.setString(3, funcionario.getNome());
            preparedStatement.setString(4, funcionario.getCpf());
            preparedStatement.setInt(5, funcionario.getTelefone());
            preparedStatement.setInt(6, funcionario.getId());
            preparedStatement.execute();
            System.out.println("Funcionario atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Funcionario: "+ex.getMessage());
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
            preparedStatement = connection.prepareStatement("DELETE FROM funcionario WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Funcionario removido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao remover Funcionario: "+ex.getMessage());
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
    
    public Funcionario buscar(int id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Funcionario funcionario =  null;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM funcionario WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null && resultSet.first()) {
                funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt(1));
                funcionario.setLogin(resultSet.getString(2));
                funcionario.setSenha(resultSet.getString(3));
                funcionario.setNome(resultSet.getString(4));
                funcionario.setCpf(resultSet.getString(5));
                funcionario.setTelefone(resultSet.getInt(6));
            } 
            System.out.println("Funcionario buscado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Funcionario: "+ex.getMessage());
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
        return funcionario;
    }
    
    public List<Funcionario> listar()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM funcionario");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt(1));
                funcionario.setLogin(resultSet.getString(2));
                funcionario.setSenha(resultSet.getString(3));
                funcionario.setNome(resultSet.getString(4));
                funcionario.setCpf(resultSet.getString(5));
                funcionario.setTelefone(resultSet.getInt(6));
                funcionarios.add(funcionario);
            } 
            System.out.println("Funcionarios listados com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao listar Funcionarios: "+ex.getMessage());
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
        return funcionarios;
    }
    
    public List<Funcionario> buscarPorNome(String nome)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Funcionario> funcionarios = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            preparedStatement.setString(1, "%" + nome + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt(1));
                funcionario.setLogin(resultSet.getString(2));
                funcionario.setSenha(resultSet.getString(3));
                funcionario.setNome(resultSet.getString(4));
                funcionario.setCpf(resultSet.getString(5));
                funcionario.setTelefone(resultSet.getInt(6));
                funcionarios.add(funcionario);
            } 
            System.out.println("Funcionario buscado por nome com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar funcionario por nome: "+ex.getMessage());
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
        return funcionarios;
    }
}