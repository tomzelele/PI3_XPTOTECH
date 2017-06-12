/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.funcionario.Funcionario;
import br.com.senac.pi3.model.user.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Souza08
 */
public class DaoFuncionario {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoFuncionario(Connection conBanco) {
        
        this.conBanco = conBanco;
    }
    
    
     public Funcionario buscarPorId(int idFuncionario) throws SQLException{
        String sql = "SELECT * FROM FUNCIONARIO WHERE id_funcionario=?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idFuncionario);
        ResultSet rs = psComando.executeQuery(); 
        
        Funcionario funcionario = new Funcionario();
        
        while(rs.next()){
                        
            funcionario.setId(rs.getInt("ID_FUNCIONARIO"));
            funcionario.setCodAcesso(rs.getInt("COD_ACESSO"));
            funcionario.setCargo(new DaoCargo(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_CARGO")));
            funcionario.setFilial(new DaoFilial(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_FILIAL")));
            funcionario.setNome(rs.getString("NOME"));
            funcionario.setSobrenome(rs.getString("SOBRENOME"));
            funcionario.setDtNasc(rs.getString("DT_NASC"));
            funcionario.setCpf(rs.getString("CPF"));
            funcionario.setSexo(rs.getString("SEXO"));
            funcionario.setCel(rs.getString("CEL"));
            funcionario.setEmail(rs.getString("EMAIL"));

            funcionario.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));

        }
        
        
        return  funcionario;
    }
     
    public void inserirFuncionario(Funcionario funcionario) throws SQLException, Exception {

        String sql = "INSERT INTO funcionario (cod_acesso,id_cargo,id_filial,nome,sobrenome,"
                + "dt_nasc,cpf,sexo,cel,email,enabled,fk_endereco) "+
               " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setInt(1, funcionario.getCodAcesso());
            psComando.setInt(2, funcionario.getCargo().getIdCargo());
            psComando.setInt(3, funcionario.getFilial().getIdFilial());
            psComando.setString(4, funcionario.getNome());
            psComando.setString(5, funcionario.getSobrenome());
            psComando.setString(6, funcionario.getDtNasc());
            psComando.setString(7, funcionario.getCpf());
            psComando.setString(8, funcionario.getSexo());
            psComando.setString(9, funcionario.getCel());
            psComando.setString(10, funcionario.getEmail());
            psComando.setBoolean(11, true);
            psComando.setInt(12, funcionario.getEndereco().getId());


            psComando.execute();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    public void atualizarFuncionario(Funcionario funcionario) throws SQLException, Exception {

        String sql = "UPDATE funcionario set cod_acesso=?,id_cargo=?, id_filial=?,"
                + "nome=?,sobrenome=?,dt_nasc=?,cpf=?,sexo=?,cel=?,email=? WHERE id_funcionario=?";
              

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setInt(1, funcionario.getCodAcesso());
            psComando.setInt(2, funcionario.getCargo().getIdCargo());
            psComando.setInt(3, funcionario.getFilial().getIdFilial());
            psComando.setString(4, funcionario.getNome());
            psComando.setString(5, funcionario.getSobrenome());
            psComando.setString(6, funcionario.getDtNasc());
            psComando.setString(7, funcionario.getCpf());
            psComando.setString(8, funcionario.getSexo());
            psComando.setString(9, funcionario.getCel());
            psComando.setString(10, funcionario.getEmail());
            psComando.setInt(11, funcionario.getId());
        

            psComando.executeUpdate();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    public  void excluirFuncionario(Integer id) throws SQLException, Exception {
        //Monta a string de atualização do cliente no BD, utilizando
        //o ID do cliente passado como parâmetro para desativá-lo
        String sql = "UPDATE funcionario SET enabled=false WHERE id_funcionario=?";        
       
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, id);
        psComando.executeUpdate();
    }
    
    public ArrayList<Funcionario> listarFuncionario() throws SQLException{
           
           String sql = "SELECT * FROM funcionario WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();
           
           
           ArrayList<Funcionario> listaFuncionario  = new ArrayList<Funcionario>();
           
           DaoEndereco  daoEndereco = new DaoEndereco(ConnectionUtils.getConnection());
           
           while(rs.next()){
               Funcionario funcionario = new Funcionario();
             
               funcionario.setId(rs.getInt("ID_FUNCIONARIO"));
               funcionario.setCodAcesso(rs.getInt("COD_ACESSO"));
               funcionario.setCargo(new DaoCargo(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_CARGO")));
               funcionario.setFilial(new DaoFilial(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_FILIAL")));
               funcionario.setNome(rs.getString("NOME"));
               funcionario.setSobrenome(rs.getString("SOBRENOME"));
               funcionario.setDtNasc(rs.getString("DT_NASC"));
               funcionario.setCpf(rs.getString("CPF"));
               funcionario.setSexo(rs.getString("SEXO"));
               funcionario.setCel(rs.getString("CEL"));
               funcionario.setEmail(rs.getString("EMAIL"));
               
              
               funcionario.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
               listaFuncionario.add(funcionario);
               
           }
           
           return listaFuncionario;
           
    }
    
    public ArrayList<Funcionario> procurarFuncionario(String nome)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM funcionario WHERE UPPER (nome) LIKE UPPER ('%" + nome + "%') AND enabled=true";
        
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs =  psComando.executeQuery();
        
        ArrayList<Funcionario> listaFuncionario  = new ArrayList<Funcionario>();
         
           
           while(rs.next()){
               
               Funcionario funcionario =  new Funcionario();
               
               funcionario.setId(rs.getInt("ID_FUNCIONARIO"));
               funcionario.setCodAcesso(rs.getInt("COD_ACESSO"));
               funcionario.setCargo(new DaoCargo(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_CARGO")));
               funcionario.setFilial(new DaoFilial(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("ID_FILIAL")));
               funcionario.setNome(rs.getString("NOME"));
               funcionario.setSobrenome(rs.getString("SOBRENOME"));
               funcionario.setDtNasc(rs.getString("DT_NASC"));
               funcionario.setCpf(rs.getString("CPF"));
               funcionario.setSexo(rs.getString("SEXO"));
               funcionario.setCel(rs.getString("CEL"));
               funcionario.setEmail(rs.getString("EMAIL"));
                             
               funcionario.setEndereco(new DaoEndereco(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ENDERECO")));
               
               listaFuncionario.add(funcionario);
           }
           
           return listaFuncionario;
    } 
    
}
