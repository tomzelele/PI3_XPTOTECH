package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.filial.Filial;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoFilial {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
    
    public DaoFilial(Connection conBanco) {
        this.conBanco = conBanco;
    }
    
    public static void inserirFilial(Filial filial) throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produtos passados como parâmetro
        String sql = "INSERT INTO FILIAL ( CNPJ, DESC_NOME, "
                + "DESC_FANTASIA, TELEFONE, FK_ENDERECO) VALUES ("
                + "'" +filial.getCnpj()+ "', "
                + "'" +filial.getNome()+ "', "
                + "'" +filial.getFantasia()+ "', "
                + "'" +filial.getTelefone()+ "', "
                + " " + 1
                + ")";

        //Executa o SQL
        executarSQL(sql);
        System.out.println("inserido com sucesso");
    }

    public static void atualizarFilial(Filial filial)
            throws SQLException, Exception {
        String sql = "UPDATE filial SET "
                + "CNPJ='" + filial.getCnpj()+ "', "
                + "NOME='" + filial.getNome()+ "', "
                + "NOME_FANTASIA=" + filial.getFantasia()+ ","
                + "TELEFONE=" + filial.getTelefone()+ " "
                + " WHERE (ID_FILIAL=" + filial.getIdFilial() + ")";

        //Executa o SQL
        executarSQL(sql);
    }

    public static void excluirFilial(Integer id) throws SQLException, Exception {
        String sql = "DELETE FILIAL SET "
                + " WHERE (ID_FILIAL = " + id + ")";

        //Executa o SQL
        executarSQL(sql);
    }

    public static List<Filial> listarFiliais()
            throws SQLException, Exception {
        String sql = "SELECT * FROM FILIAL WHERE ENABLED = TRUE";

        return executarConsulta(sql);
    }

     public static List<Filial> procurarFilialPorCNPJ(String cnpj)
            throws SQLException, Exception {
        String sql = "SELECT * FROM FILIAL WHERE CNPJ = " + cnpj;

        //Retorna o resultado da execução da consulta SQL montada
        return executarConsulta(sql);
    }

    
    private static void executarSQL(String sql) throws
            SQLException, Exception {
        Connection connection = null;
        Statement statement = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando COMANDO SQL: " + sql);
            statement.execute(sql);
        } finally {
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public static List<Filial> executarConsulta(String sql) throws
        SQLException, Exception {
        List<Filial> listaFilial = null;
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        try {
            connection = ConnectionUtils.getConnection();
            statement = connection.createStatement();
            System.out.println("Executando CONSULTA SQL: " + sql);
            result = statement.executeQuery(sql);
            while (result.next()) {
                if (listaFilial == null) {
                    listaFilial= new ArrayList<>();
                }
                Filial filial = new Filial();
                filial.setCnpj(result.getString("CNPJ"));
                filial.setNome(result.getString("NOME"));
                filial.setFantasia(result.getString("NOME_FANTASIA"));
                filial.setTelefone(result.getString("TELEFONE"));
                
                //Adiciona a instância na lista
                listaFilial.add(filial);
            }
        } finally {
            if (result != null && !result.isClosed()) {
                result.close();
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
        return listaFilial;
    }
    
        public ArrayList<Filial> listarFilial() throws SQLException{
           String sql = "SELECT * FROM FILIAL WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();

           ArrayList<Filial> listaFilial  = new ArrayList<>();           

           DaoEndereco  daoEndereco = new DaoEndereco(ConnectionUtils.getConnection());

           while(rs.next()){
               Filial filial = new Filial();
               
               filial.setIdFilial(rs.getInt("ID_FILIAL"));
               filial.setNome(rs.getString("NOME"));
               filial.setFantasia(rs.getString("NOME_FANTASIA"));
               filial.setTelefone(rs.getString("TELEFONE"));
               filial.setCnpj(rs.getString("CNPJ"));
                             
               filial.setEndereco(daoEndereco.buscarPorId(rs.getInt("FK_ENDERECO")));
               listaFilial.add(filial);
           }
           
           return listaFilial;   
        }
        
        public Filial buscarPorId(int idFilial) throws SQLException {
            String sql = "SELECT * FROM FILIAL WHERE ID_FILIAL=?";
            psComando = conBanco.prepareStatement(sql);
            psComando.setInt(1, idFilial);
            ResultSet rs =  psComando.executeQuery();
            
            Filial filial = new Filial();
            while(rs.next()) {
               filial.setIdFilial(rs.getInt("ID_FILIAL"));
               filial.setNome(rs.getString("NOME"));
               filial.setFantasia(rs.getString("NOME_FANTASIA"));
               filial.setTelefone(rs.getString("TELEFONE"));
               filial.setCnpj(rs.getString("CNPJ"));
               //filial.setEstoque(new DaoEstoque(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ID_CATEGORIA")));
            }
        return filial;
    } 
}
