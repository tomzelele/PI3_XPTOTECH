/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.produtos;

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

/**
 *
 * @author Maquinaria
 */

@WebServlet(name = "CadastrarProduto", urlPatterns = {"/CadastraProduto"})

public class CadastrarProduto extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        DaoCategoria categoriaDao = new DaoCategoria(ConnectionUtils.getConnection());
        
        try {
            req.getSession().setAttribute("listaCategoria", categoriaDao.listarCategoria()) ;
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.getRequestDispatcher("Produtos/inserirProduto.jsp").forward(req, resp);
        
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
            Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        produto.setVlProd(Double.parseDouble(req.getParameter("vlProd")));
                
        // Validar campos
        ServicoProduto utilProduto = new ServicoProduto();
        String message = utilProduto.validarCampos(produto);
        if (!message.equals("")) {
            // Obtendo os valores do formulário p/ manter o mesmo preenchido 
            req.setAttribute("nomeProd", produto.getProduto());
            req.setAttribute("vlProd", produto.getVlProd());
            
            // Passando mensagem para página jsp
            req.setAttribute("message", message);
            req.getRequestDispatcher("Produtos/inserirProduto.jsp").forward(req, resp);
            
            
        } else {
            try {
                // Realiza a inclusão do registro e envia mensagem para jsp
                produtoDao.inserirProduto(produto);
                message = "Inclusão efetuada com sucesso";
                req.setAttribute("message", message);
                req.getRequestDispatcher("Produtos/inserirProduto.jsp").forward(req, resp);
            } catch (ProdutoException ex) {
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataSourceException ex) {
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) { 
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
       
        req.getRequestDispatcher("Produtos/listarProduto.jsp").forward(req, resp);
    }
    
}
