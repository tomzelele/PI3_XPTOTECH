/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.produtos;

import br.com.senac.pi3.model.categoria.Categoria;
import br.com.senac.pi3.model.produto.Produto;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoCategoria;
import br.com.senac.pi3.db.dao.DaoProduto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Maquinaria
 */
@WebServlet("/EditarProduto")

public class EditarProduto extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           int idProduto = Integer.parseInt( req.getParameter("idProduto") );
           
           DaoProduto produtoDao = new DaoProduto(ConnectionUtils.getConnection());
           
           DaoCategoria  categoriaDao = new DaoCategoria(ConnectionUtils.getConnection());
           
           Produto produto = null;
        try {
           produto = produtoDao.buscarPorId(idProduto);
        } catch (SQLException ex) {
            Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        List<Categoria> listaCategoria = null;
        try {
            listaCategoria = categoriaDao.listarCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        listaCategoria.remove(produto.getCategoria());
        
        listaCategoria.set(0, produto.getCategoria());
        
        req.getSession().setAttribute("ListaCategoriaAtualiza", listaCategoria);
        req.getSession().setAttribute("ProdutoAtualiza", produto);
        req.getRequestDispatcher("/Produtos/editarProduto.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoProduto produtoDao = new DaoProduto(ConnectionUtils.getConnection());
        Produto produto = new Produto();
        
        produto.setId(Integer.parseInt(req.getParameter("idProd")));
        
        produto.setProduto(req.getParameter("nomeProd"));
        int idCategoria = Integer.parseInt(req.getParameter("categoriaProd"));
        try {        
            produto.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(idCategoria));
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        produto.setVlProd(Double.parseDouble(req.getParameter("vlProd")));
        
        try {
            produtoDao.atualizarProduto(produto);
        } catch (Exception ex) {

        }
        
        
        resp.sendRedirect("ListarProdutos");
        
    }
    
    
    
    
    
    
    
}
