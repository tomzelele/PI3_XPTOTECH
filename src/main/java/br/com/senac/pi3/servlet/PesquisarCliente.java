/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet;

import br.com.senac.pi3.db.dao.DaoCliente;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.model.cliente.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kelly
 */
@WebServlet(name = "PesquisarCliente", urlPatterns = {"/PesquisaCliente"})

public class PesquisarCliente extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

               
        DaoCliente clienteDao = new DaoCliente(ConnectionUtils.getConnection());
                
        String cliente = req.getParameter("pesquisaNome");
       
        
         List<Cliente> listaClientes = null;
       
        try {
            listaClientes = clienteDao.procurarCliente(cliente);
        } catch (Exception ex) {
            Logger.getLogger(PesquisarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
           
        
        
        req.getSession().setAttribute("listaClientes", listaClientes);
        
        req.getRequestDispatcher("Clientes/listarCliente.jsp").forward(req, resp);
    }
}
