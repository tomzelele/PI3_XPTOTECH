package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.model.produto.Produto;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoCategoria;
import br.com.senac.pi3.db.dao.DaoProduto;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.ProdutoException;
import br.com.senac.pi3.services.produtos.ServicoProduto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "CadastrarFilial", urlPatterns = {"/CadastraFilial"})
public class CadastraFilial extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        DaoCategoria categoriaDao = new DaoCategoria(ConnectionUtils.getConnection());
        
        try {
            req.getSession().setAttribute("listaCategoria", categoriaDao.listarCategoria()) ;
        } catch (SQLException ex) {
            //Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.getRequestDispatcher("Produtos/inserirFilial.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        DaoProduto produtoDao = new DaoProduto(ConnectionUtils.getConnection());
        Produto produto = new Produto();
        
        
        
        produto.setProduto(req.getParameter("nomeProd"));
        int idCategoria = Integer.parseInt(req.getParameter("categoriaProd"));
        try {        
            produto.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(idCategoria));
        } catch (SQLException ex) {
            //Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        produto.setVlProd(Double.parseDouble(req.getParameter("vlProd")));
        
        
        try {
            ServicoProduto.cadastrarProduto(produto);
        } catch (ProdutoException ex) {
            //Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataSourceException ex) {
           // Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
                
        try {
            produtoDao.inserirProduto(produto);
        } catch (Exception ex) {
            //Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        req.getRequestDispatcher("Produtos/listarProduto.jsp").forward(req, resp);
    }
    
    
    
    
    
}