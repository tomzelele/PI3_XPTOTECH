/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.model.produto.Produto;
import br.com.senac.pi3.model.vendas.ItemVenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Souza08
 */
public class DaoItemVenda {
    
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoItemVenda(Connection conBanco) {
        
        this.conBanco = conBanco;

    }
    
    
     public void inserirItemVenda(ItemVenda itemVenda, Integer idVenda) throws SQLException, Exception {

        String sql = "INSERT INTO VENDA_PRODUTO(produto_id,id_venda,qtdund) "+
               " VALUES (?,?,?)";

        try {
            
            psComando = conBanco.prepareStatement(sql);

            psComando.setInt(1, itemVenda.getProduto().getId());
            psComando.setInt(2,  idVenda);
            psComando.setInt(3, itemVenda.getQtd());


            psComando.execute();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
}
