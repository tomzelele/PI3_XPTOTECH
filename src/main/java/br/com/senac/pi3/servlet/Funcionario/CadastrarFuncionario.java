/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.db.dao.DaoPerfil;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.model.funcionario.Funcionario;
import java.io.IOException;
import java.sql.Connection;
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
 * @author Kelly
 */

@WebServlet(name = "CadastrarFuncionario", urlPatterns = {"/CadastraFuncionario"})

public class CadastrarFuncionario extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        
        try {
            req.getSession().setAttribute("listaFilial", filialDao.listarFiliais()) ;
        } catch (Exception ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        DaoPerfil perfilDao = new DaoPerfil(ConnectionUtils.getConnection());
        
        try { 
            req.getSession().setAttribute("listaPerfil", perfilDao.listarPerfil());
                    
                    } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        req.getRequestDispatcher("Funcionarios/inserirFuncionario.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        Connection connection = ConnectionUtils.getConnection();
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setCodAcesso(Integer.parseInt(req.getParameter("codAcesso")));
        funcionario.setCargo(req.getParameter("cargo"));
        
        int idFilial = Integer.parseInt(req.getParameter("filial"));
               
        try {
            funcionario.setFilial(new DaoFilial(ConnectionUtils.getConnection()).buscarPorId(idFilial));
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int idPerfil = Integer.parseInt(req.getParameter("perfil"));
        
        try {
            funcionario.setPerfil(new DaoPerfil (ConnectionUtils.getConnection()).buscarPorId(idPerfil));
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        funcionario.setNome(req.getParameter("nomeFuncionario"));
        funcionario.setSobrenome(req.getParameter("sobreNomeFuncionario"));
        funcionario.setDtNasc(req.getParameter("dataNascimentoFuncionario"));
        funcionario.setCpf(req.getParameter("cpfFuncionario"));
        funcionario.setSexo(req.getParameter("selectSexoFuncionario"));
        funcionario.setCel(req.getParameter("celularFuncionario"));
        funcionario.setEmail(req.getParameter("emailFuncionario"));
                
        try {
            
        Connection connection1 = ConnectionUtils.getConnection();
        
        DaoFuncionario funcionarioDao = new DaoFuncionario(connection1);
            funcionarioDao.inserir(funcionario);

        } catch (Exception ex) {
        }
      
        req.getRequestDispatcher("Funcionarios/listarFuncionario.jsp").forward(req, resp);
    }
    
    
    
    
    
}