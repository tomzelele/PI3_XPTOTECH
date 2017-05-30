/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.db.dao;


import br.com.senac.pi3.model.produto.Produto;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Souza08
 */
public class DaoProduto {
    
    private Connection conBanco;
    private PreparedStatement psComando;
    private ResultSet rsRegistros;

   
    public DaoProduto(Connection conBanco) {
        
        this.conBanco = conBanco;

    }

    public DaoProduto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    public void inserirProduto(Produto produto) throws SQLException, Exception {

        String sql = "INSERT INTO Produto(vl_prod,desc_prod,enabled,fk_id_categoria) "+
               " VALUES (?,?,?,?)";

        try {
            psComando = conBanco.prepareStatement(sql);

            psComando.setDouble(1, produto.getVlProd());
            psComando.setString(2, produto.getProduto());
            psComando.setBoolean(3, true);
            psComando.setInt(4, produto.getCategoria().getIdCategoria());


            psComando.execute();

       }catch (Exception erro) {
           erro.printStackTrace();
       }

    }
    
    
    
        public void atualizarProduto(Produto produto) throws SQLException, Exception {

//            String sql = "UPDATE Produto set qtd_unidade=?,vl_prod=?,desc_prod=?,qtd_estoque=?,categoria=? WHERE id_produto=?";

            
            String sql =  "UPDATE PRODUTO SET DESC_PROD =? ,VL_PROD=? , FK_ID_CATEGORIA = ? WHERE ID_PRODUTO = ?";

            try {
                psComando = conBanco.prepareStatement(sql);

                psComando.setString(1, produto.getProduto());
                psComando.setDouble(2, produto.getVlProd());
                psComando.setInt(3, produto.getCategoria().getIdCategoria());
                psComando.setInt(4, produto.getId());


                psComando.executeUpdate();

           }catch (Exception erro) {
               erro.printStackTrace();
           }

        }
        
        public  void excluirProduto(Integer id) throws SQLException, Exception {
        //Monta a string de atualização do Produto no BD, utilizando
        //o ID do produto passado como parâmetro para desativá-lo
        String sql = "UPDATE produto SET enabled=false WHERE ID_PRODUTO=?";        
       
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, id);
        psComando.executeUpdate(); 
    }
     
       public ArrayList<Produto> listarProduto() throws SQLException{
           
           String sql = "SELECT * FROM PRODUTO WHERE enabled=true";
           psComando = conBanco.prepareStatement(sql);
           ResultSet rs =  psComando.executeQuery();
           
           
           ArrayList<Produto> listaProduto  = new ArrayList<Produto>();
           
           
           while(rs.next()){
               Produto produto = new Produto();
               
               produto.setId(rs.getInt("id_produto"));
               produto.setVlProd(rs.getDouble("vl_prod"));
               produto.setProduto(rs.getString("desc_prod"));
               produto.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ID_CATEGORIA")));
               listaProduto.add(produto);
               
           }
           
           return listaProduto;
           
    }    

    public Produto buscarPorId(int idProduto) throws SQLException {
         String sql = "SELECT * FROM Produto WHERE id_produto=?";
        psComando = conBanco.prepareStatement(sql);
        psComando.setInt(1, idProduto);
        ResultSet rs =  psComando.executeQuery();
           
           
           Produto produto=  new Produto();
           
           
           while(rs.next()){
                                      

            produto.setId(rs.getInt("id_produto"));
            produto.setVlProd(rs.getDouble("vl_prod"));
            produto.setProduto(rs.getString("desc_prod"));
            produto.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ID_CATEGORIA")));
     
           }
           
            
        return produto;
    }  
    
    public ArrayList<Produto> procurarProduto(String valor)
            throws SQLException, Exception {
        
        String sql = "SELECT * FROM produto WHERE UPPER (desc_prod) LIKE UPPER ('" + valor+ ""
                + "%') AND enabled=true;";
        
        psComando = conBanco.prepareStatement(sql);
        ResultSet rs =  psComando.executeQuery();
        
        ArrayList<Produto> listaProduto  = new ArrayList<Produto>();
         
           
           while(rs.next()){
               Produto produto = new Produto();
               
               produto.setId(rs.getInt("id_produto"));
               produto.setVlProd(rs.getDouble("vl_prod"));
               produto.setProduto(rs.getString("desc_prod"));
               produto.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(rs.getInt("FK_ID_CATEGORIA")));
               listaProduto.add(produto);
               
           }
           
           return listaProduto;
    }
    
    
}