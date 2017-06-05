/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

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
 * @author Maquinaria
 */
@WebServlet("/ExcluiFuncionario")

public class ExcluirFuncionario extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        DaoFuncionario funcionarioDao = new DaoFuncionario(ConnectionUtils.getConnection());
        
        int idFuncionario = Integer.parseInt(req.getParameter("idFuncionarioExcluir"));
        
        
        try {
            funcionarioDao.excluirFuncionario(idFuncionario);
        } catch (Exception ex) {
            Logger.getLogger(ExcluirFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        resp.sendRedirect("ListarFuncionarios");

    }
    
    
    
}
