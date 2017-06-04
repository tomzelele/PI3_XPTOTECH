/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.model.perfil.Perfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kelly
 */
public class DaoPerfil {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoPerfil(Connection conBanco) {
        
        this.conBanco = conBanco;
    }
    
        public void inserirPerfil(Perfil perfil) throws SQLException, Exception {

        String sql = "INSERT INTO perfil_usuario (perfil,enabled) "+
               " VALUES (?,?)";

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, perfil.getPerfil());
            psComando.setBoolean(2, true);


            psComando.execute();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    
    
        public void atualizarPerfil(Perfil perfil) throws SQLException, Exception {

        String sql = "UPDATE perfil_usuario SET perfil=? WHERE id_perfil=?";
              

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, perfil.getPerfil());
     
     
            psComando.executeUpdate();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
        
    public  void excluirPerfil(Integer idPerfil) throws SQLException, Exception {
        
        String sql = "UPDATE perfil_usuario SET enabled=false WHERE id_perfil=?";        
       
        psComando = conBanco.prepareStatement(sql);
        psComando.executeUpdate();
    }
     
       public ArrayList<Perfil> listarPerfil() throws SQLException{
           
           String sql = "SELECT * FROM perfil_usuario WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();
           
           
           ArrayList<Perfil> listaPerfil  = new ArrayList<Perfil>();
           

           while(rs.next()){
               Perfil perfil = new Perfil();
               
               perfil.setIdPerfil(rs.getInt("id_perfil"));
               perfil.setPerfil(rs.getString("perfil"));
              
               listaPerfil.add(perfil);
               
           }
           
           return listaPerfil;
           
    }    

    public Perfil buscarPorId(int idPerfil) throws SQLException {
         String sql = "SELECT * FROM perfil_usuario WHERE id_perfil=?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idPerfil);
        ResultSet rs =  psComando.executeQuery();
           
           
           Perfil perfil =  new Perfil();
           
           
           while(rs.next()){
               
               perfil.setIdPerfil(rs.getInt("id_perfil"));
               perfil.setPerfil(rs.getString("perfil"));
                             
           }
           
            
        return perfil;
    }  
    
}
