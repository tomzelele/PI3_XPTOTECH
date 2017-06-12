/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.ProdutoException;
import br.com.senac.pi3.model.cargo.Cargo;
import br.com.senac.pi3.model.filial.Filial;
import br.com.senac.pi3.model.funcionario.Funcionario;
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
@WebServlet(name = "PesquisarFilial", urlPatterns = {"/PesquisaFilial"})

public class PesquisarFilial extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("Filial/listarFilial.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

               
        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
                
        String filial = req.getParameter("pesquisaNome");
       
        
         List<Filial> listaFiliais = null;
        
        
        try {
            listaFiliais = filialDao.procurarFilial(filial);
        } catch (Exception ex) {
            Logger.getLogger(PesquisarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        req.getSession().setAttribute("listaFiliais", listaFiliais);
        
        req.getRequestDispatcher("Filial/listarFilial.jsp").forward(req, resp);
    }
}
