/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.produtos;

import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoProduto;
import java.io.IOException;
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
@WebServlet("/ExcluiProduto")

public class ExcluirProduto extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        DaoProduto produtoDao = new DaoProduto(ConnectionUtils.getConnection());
        
        int idProduto = Integer.parseInt(req.getParameter("idProdutoExcluir"));
        
        
        try {
            produtoDao.excluirProduto(idProduto);
        } catch (Exception ex) {
            Logger.getLogger(ExcluirProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
                resp.sendRedirect("ListaProduto");

    }
    
    
    
}
