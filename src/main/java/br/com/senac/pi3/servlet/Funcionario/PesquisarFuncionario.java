/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

import br.com.senac.pi3.db.dao.DaoCargo;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.ProdutoException;
import br.com.senac.pi3.model.cargo.Cargo;
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
@WebServlet(name = "PesquisarFuncionario", urlPatterns = {"/PesquisaFuncionario"})

public class PesquisarFuncionario extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        
        try {
            req.getSession().setAttribute("listaFilial", filialDao.listarFiliais()) ;
        } catch (Exception ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DaoCargo  cargoDao = new DaoCargo(ConnectionUtils.getConnection());
        
        
        try {
            req.getSession().setAttribute("listaCargo", cargoDao.listarCargo());
        } catch (SQLException ex) {
            Logger.getLogger(PesquisarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        req.getRequestDispatcher("Produtos/listarProduto.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

               
        DaoFuncionario funcionarioDao = new DaoFuncionario(ConnectionUtils.getConnection());
                
        String funcionario = req.getParameter("pesquisaNome");
       
        
         List<Funcionario> listaFuncionarios = null;
        
        try {
            listaFuncionarios = funcionarioDao.procurarFuncionario(funcionario);
        } catch (Exception ex) {
            Logger.getLogger(PesquisarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        req.getSession().setAttribute("listaFuncionarios", listaFuncionarios);
        
        req.getRequestDispatcher("Funcionarios/listarFuncionario.jsp").forward(req, resp);
    }
}
