package br.com.senac.pi3.db.dao;

import br.com.senac.pi3.model.estoque.Estoque;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaoEstoque {
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;
    
    public DaoEstoque(Connection conBanco) {
        this.conBanco = conBanco;
    }

     public ArrayList<Estoque> listarEstoque() throws SQLException{
           
           String sql = "SELECT * FROM MOVIMENTACAO_ESTOQUE WHERE TG_ES = 'E' AND ENABLED=TRUE";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();

           ArrayList<Estoque> listaEstoque  = new ArrayList<>();

           while(rs.next()){
               Estoque entrada = new Estoque();
               
               entrada.setId(rs.getInt("id_produto"));
               entrada.setFk_produto(rs.getInt("fk_produto"));
               entrada.setFk_estoque(rs.getInt("fk_estoque"));
               entrada.setQt_movimento(rs.getInt("Qt_movimento"));
               entrada.setES(rs.getString("ES"));
               
               
               listaEstoque.add(entrada);
               
           }
           return listaEstoque;      
    }
}
