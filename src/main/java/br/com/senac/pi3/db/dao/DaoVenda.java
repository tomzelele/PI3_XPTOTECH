/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.vendas.ItemVenda;
import br.com.senac.pi3.model.vendas.Venda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Souza08
 */
public class DaoVenda {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoVenda(Connection conBanco) {
        
        this.conBanco = conBanco;

    }
    
    public void inserirVenda(Venda venda) throws SQLException, Exception {

        String sql = "INSERT INTO VENDA(DT_VENDA,VALOR_COMPRA,CLIENTE_ID,FILIAL_ID) "+
               " VALUES (CURRENT_DATE,?,?,?)";

        try {
            
            psComando = conBanco.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            
            System.out.println(venda.getCliente());
            
            psComando.setDouble(1, venda.calcularValorTotal());
            psComando.setInt(2,venda.getCliente().getId());
            psComando.setInt(3, venda.getFuncionario().getFilial().getIdFilial());

            psComando.execute();
            
            ResultSet rs = psComando.getGeneratedKeys();

            
            Integer idVenda = 0 ;
            while(rs.next()){
                idVenda = rs.getInt(1);
            }  
            
            DaoItemVenda daoItemVenda = new DaoItemVenda(ConnectionUtils.getConnection());
            
            for(ItemVenda item  : venda.getItens()){
                
                daoItemVenda.inserirItemVenda(item, idVenda);
                
            }
            
            
       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    
    
    
    
}
