/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.funcionario.Funcionario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "ListarFuncionario", urlPatterns = {"/ListarFuncionarios"})

public class ListarFuncionario extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        DaoFuncionario funcionarioDao = new DaoFuncionario(ConnectionUtils.getConnection());
        
         List<Funcionario> listaFuncionarios = null;
        
        try {
            listaFuncionarios = funcionarioDao.listarFuncionario();
        } catch (SQLException ex) {
            Logger.getLogger(ListarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        req.getSession().setAttribute("listaFuncionarios", listaFuncionarios);
        req.getRequestDispatcher("Funcionarios/listarFuncionario.jsp").forward(req, resp);
    }
}
