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

    public DaoFilial() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void inserirFilial(Filial filial) throws SQLException, Exception {
        //Monta a string de inserção de um produto no BD,
        //utilizando os dados do produtos passados como parâmetro
        String sql = "INSERT INTO FILIAL (CNPJ,DESC_NOME,DESC_FANTASIA,TELEFONE, FK_ENDERECO, ENABLED) "
                + "VALUES (?,?,?,?,?,?)";
        
        try{
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, filial.getCnpj());
            psComando.setString(2, filial.getNome());
            psComando.setString(3, filial.getFantasia());
            psComando.setString(4, filial.getTelefone());
            psComando.setInt(5, filial.getEndereco().getId());
            psComando.setBoolean(6, true);
            
            psComando.execute();

        } catch(Exception erro) {
           erro.printStackTrace();
       }
    
    }

    public void atualizarFilial(Filial filial) throws SQLException, Exception {
        String sql = "UPDATE FILIAL SET CNPJ=?,DESC_NOME=?,DESC_FANTASIA=?,TELEFONE=? WHERE ID_FILIAL=?";
        
                
        try{
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, filial.getCnpj());
            psComando.setString(2, filial.getNome());
            psComando.setString(3, filial.getFantasia());
            psComando.setString(4, filial.getTelefone());
            psComando.setInt(5, filial.getIdFilial());
            
            psComando.executeUpdate();

        } catch(Exception erro) {
           erro.printStackTrace();
       }

        
        
    }

    public void excluirFilial(Integer id) throws SQLException, Exception {
        String sql = "UPDATE FILIAL SET enabled=false WHERE id_filial=?"; 
        
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, id);
        psComando.executeUpdate();
        
    }

    public ArrayList<Filial> listarFiliais() throws SQLException{
        
        String sql = "SELECT * FROM FILIAL WHERE ENABLED = TRUE";

        psComando = conBanco.prepareStatement(sql);
        ResultSet rs =  psComando.executeQuery();
        
        ArrayList<Filial> listaFilial  = new ArrayList<Filial>();
        
        while(rs.next()){
            Filial filial = new Filial ();
            
            filial.setIdFilial(rs.getInt("ID_FILIAL"));
            filial.setCnpj(rs.getString("CNPJ"));
            filial.setNome(rs.getString("DESC_NOME"));
            filial.setFantasia(rs.getString("DESC_FANTASIA"));
            filial.setTelefone(rs.getString("TELEFONE"));
            filial.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
            
            listaFilial.add(filial);
            
        }
       
        return listaFilial;
    }

     public ArrayList<Filial> procurarFilialPorCNPJ(String cnpj)
            throws SQLException, Exception {
        String sql = "SELECT * FROM FILIAL WHERE UPPER (nome) LIKE UPPER ('%" + cnpj + "%') AND enabled=true";
        
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs =  psComando.executeQuery();
        
        ArrayList<Filial> listaFilial  = new ArrayList<Filial>();
        
        while(rs.next()){
            Filial filial = new Filial ();
            
            filial.setIdFilial(rs.getInt("ID_FILIAL"));
            filial.setCnpj(rs.getString("CNPJ"));
            filial.setNome(rs.getString("DESC_NOME"));
            filial.setFantasia(rs.getString("DESC_FANTASIA"));
            filial.setTelefone(rs.getString("TELEFONE"));
            filial.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
            
            listaFilial.add(filial);
        
        }
        return listaFilial;
    }
     public ArrayList<Filial> procurarFilial(String cnpj)
            throws SQLException, Exception {
        String sql = "SELECT * FROM FILIAL WHERE UPPER (nome) LIKE UPPER ('%" + cnpj + "%') AND enabled=true";
        
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs =  psComando.executeQuery();
        
        ArrayList<Filial> listaFilial  = new ArrayList<Filial>();
        
        while(rs.next()){
            Filial filial = new Filial ();
            
            filial.setIdFilial(rs.getInt("ID_FILIAL"));
            filial.setCnpj(rs.getString("CNPJ"));
            filial.setNome(rs.getString("DESC_NOME"));
            filial.setFantasia(rs.getString("DESC_FANTASIA"));
            filial.setTelefone(rs.getString("TELEFONE"));
            filial.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
            
            listaFilial.add(filial);
        
        }
        return listaFilial;
    }
     
    public ArrayList<Filial> listarFilial() throws SQLException{
           String sql = "SELECT * FROM FILIAL WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();

           ArrayList<Filial> listaFilial  = new ArrayList<>();           

           while(rs.next()){
               Filial filial = new Filial();
               
               filial.setIdFilial(rs.getInt("ID_FILIAL"));
               filial.setNome(rs.getString("DESC_NOME"));
               filial.setFantasia(rs.getString("DESC_FANTASIA"));
               filial.setTelefone(rs.getString("TELEFONE"));
               filial.setCnpj(rs.getString("CNPJ"));
                             
               filial.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
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
               filial.setCnpj(rs.getString("CNPJ"));
               filial.setNome(rs.getString("DESC_NOME"));
               filial.setFantasia(rs.getString("DESC_FANTASIA"));
               filial.setTelefone(rs.getString("TELEFONE"));
               filial.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
               
            }
        return filial;
    } 
}