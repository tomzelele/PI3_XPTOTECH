/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet;

import br.com.senac.pi3.db.dao.DaoCliente;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Souza08
 */
@WebServlet("/ConsultaClienteCpf")

public class ConsultaClienteCPF extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           DaoCliente dao = new DaoCliente(ConnectionUtils.getConnection());
            
           String cpf = req.getParameter("cpf");
           
           String retorno ="";
           
        try {
           retorno=  dao.buscarPorCPF(cpf);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaClienteCPF.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resp.getWriter().println(retorno);
    }
    
    
    
}
