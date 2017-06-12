/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.servlet.Funcionario.*;
import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.db.utils.ConnectionUtils;
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
 * @author Jonathan
 */
@WebServlet("/ExcluiFilial")

public class ExcluirFilial extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        DaoFilial daoFilial = new DaoFilial(ConnectionUtils.getConnection());
        
        int idFilial = Integer.parseInt(req.getParameter("idFilialExcluir"));
        
        try {
            daoFilial.excluirFilial(idFilial);
        } catch (Exception ex) {
            Logger.getLogger(ExcluirFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        resp.sendRedirect("ListarFilial");

    }
    
    
    
}
