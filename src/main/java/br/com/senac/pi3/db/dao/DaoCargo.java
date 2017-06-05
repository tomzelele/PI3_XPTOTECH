/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.model.cargo.Cargo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Kelly
 */
public class DaoCargo {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoCargo(Connection conBanco) {
        
        this.conBanco = conBanco;
    }
    
        public void inserir(Cargo cargo) throws SQLException, Exception {

        String sql = "INSERT INTO cargo (cargo,enabled) "+
               " VALUES (?,?)";

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, cargo.getCargo());
            psComando.setBoolean(2, true);


            psComando.execute();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    
    
        public void atualizarCargo(Cargo cargo) throws SQLException, Exception {

        String sql = "UPDATE cargo SET cargo=? WHERE id_cargo=?";
              

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setString(1, cargo.getCargo());
     
     
            psComando.executeUpdate();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
        
    public  void excluirCargo(Integer idCargo) throws SQLException, Exception {
        //Monta a string de atualização do cliente no BD, utilizando
        //o ID do cliente passado como parâmetro para desativá-lo
        String sql = "UPDATE cargo SET enabled=false WHERE id_cargo=?";        
       
        psComando = conBanco.prepareStatement(sql);
        psComando.executeUpdate();
    }
     
       public ArrayList<Cargo> listarCargo() throws SQLException{
           
           String sql = "SELECT * FROM Cargo WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();
           
           
           ArrayList<Cargo> listaCargo  = new ArrayList<Cargo>();
           

           while(rs.next()){
               Cargo cargo = new Cargo();
               
               cargo.setIdCargo(rs.getInt("ID_CARGO"));
               cargo.setCargo(rs.getString("CARGO"));
              
               listaCargo.add(cargo);
               
           }
           
           return listaCargo;
           
    }    

    public Cargo buscarPorId(int idCargo) throws SQLException {
         String sql = "SELECT * FROM Cargo WHERE ID_cargo=?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idCargo);
        ResultSet rs =  psComando.executeQuery();
           
           
           Cargo cargo =  new Cargo();
           
           
           while(rs.next()){
               
               cargo.setIdCargo(rs.getInt("ID_CARGO"));
               cargo.setCargo(rs.getString("CARGO"));
                             
           }
           
            
        return cargo;
    }  
    
}
