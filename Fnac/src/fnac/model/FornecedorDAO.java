/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fnac.model;

import fnac.ConexaoBanco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuário
 */
public class FornecedorDAO {
    public boolean inserir(Fornecedor fornecedor)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("INSERT INTO fornecedor(empresa,cnpj,telefone,responsavel) VALUES(?,?,?,?)");
            preparedStatement.setString(1, fornecedor.getEmpresa());
            preparedStatement.setString(2, fornecedor.getCnpj());
            preparedStatement.setString(3, fornecedor.getTelefone());
            preparedStatement.setString(4, fornecedor.getResponsavel());
            preparedStatement.execute();
            System.out.println("Fornecedor inserido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao inserir Fornecedor: "+ex.getMessage());
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
    
    public boolean atualizar(Fornecedor fornecedor)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean sucesso = true;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("UPDATE fornecedor SET empresa = ?, cnpj = ?, telefone = ?, responsavel = ? WHERE id = ?");
            preparedStatement.setString(1, fornecedor.getEmpresa());
            preparedStatement.setString(2, fornecedor.getCnpj());
            preparedStatement.setString(3, fornecedor.getTelefone());
            preparedStatement.setString(4, fornecedor.getResponsavel());
            preparedStatement.setInt(5, fornecedor.getId());
            preparedStatement.execute();
            System.out.println("Fornecedor atualizado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao atualizar Fornecedor: "+ex.getMessage());
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
            preparedStatement = connection.prepareStatement("DELETE FROM fornecedor WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            System.out.println("Fornecedor removido com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao remover Fornecedor: "+ex.getMessage());
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
    
    public Fornecedor buscar(int id)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Fornecedor fornecedor = null;
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM fornecedor WHERE id = ?");
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if(resultSet != null && resultSet.first()) {
                fornecedor = new Fornecedor();
                fornecedor.setId(resultSet.getInt(1));
                fornecedor.setEmpresa(resultSet.getString(2));
                fornecedor.setCnpj(resultSet.getString(3));
                fornecedor.setTelefone(resultSet.getString(4));
                fornecedor.setResponsavel(resultSet.getString(5));
            } 
            System.out.println("Fornecedor buscado com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Fornecedor: "+ex.getMessage());
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
        return fornecedor;
    }
    
    public List<Fornecedor> listar()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM fornecedor");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(resultSet.getInt(1));
                fornecedor.setEmpresa(resultSet.getString(2));
                fornecedor.setCnpj(resultSet.getString(3));
                fornecedor.setTelefone(resultSet.getString(4));
                fornecedor.setResponsavel(resultSet.getString(5));
                fornecedores.add(fornecedor);
            } 
            System.out.println("Fornecedores listados com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao listar Fornecedores: "+ex.getMessage());
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
        return fornecedores;
    }
    
    public List<Fornecedor> buscarPorNome(String empresa)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Fornecedor> fornecedores = new ArrayList<>();
        try {
            connection = ConexaoBanco.conectaBanco();
            preparedStatement = connection.prepareStatement("SELECT * FROM fornecedor WHERE empresa LIKE ?");
            preparedStatement.setString(1, "%" + empresa + "%");
            resultSet = preparedStatement.executeQuery();
            while(resultSet != null && resultSet.next()) {
                Fornecedor fornecedor = new Fornecedor();
                fornecedor.setId(resultSet.getInt(1));
                fornecedor.setEmpresa(resultSet.getString(2));
                fornecedor.setCnpj(resultSet.getString(3));
                fornecedor.setTelefone(resultSet.getString(4));
                fornecedor.setResponsavel(resultSet.getString(5));
                fornecedores.add(fornecedor);
            } 
            System.out.println("Fornecedor buscado por empresa com sucesso!");
        } catch (SQLException ex) {
            System.err.println("Erro ao buscar Fornecedor por empresa: "+ex.getMessage());
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
        return fornecedores;
    }
}
