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
import br.com.senac.pi3.model.funcionario.Funcionario;
import br.com.senac.pi3.services.funcionarios.ServicoFuncionario;
import br.com.senac.pi3.services.produtos.ServicoProduto;

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
        
        
             
        req.getRequestDispatcher("Funcionarios/inserirFuncionario.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        
        Connection connection = ConnectionUtils.getConnection();
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setCodAcesso(Integer.parseInt(req.getParameter("codAcesso")));
        
        int idCargo = Integer.parseInt(req.getParameter("cargo"));
        
        try {
            funcionario.setCargo(new DaoCargo(ConnectionUtils.getConnection()).buscarPorId(idCargo));
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        int idFilial = Integer.parseInt(req.getParameter("filial"));
               
        try {
            funcionario.setFilial(new DaoFilial(ConnectionUtils.getConnection()).buscarPorId(idFilial));
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
        
        // Validar campos
        ServicoFuncionario utilFuncionario = new ServicoFuncionario();
        String message = utilFuncionario.validarCampos(funcionario);
        if (!message.equals("")) {
        	// Obtendo os valores do formulário p/ manter o mesmo preenchido 
        	req.setAttribute("codAcesso", funcionario.getCargo());
        	req.setAttribute("nomeFuncionario", funcionario.getNome());
        	req.setAttribute("sobreNomeFuncionario", funcionario.getSobrenome());
        	req.setAttribute("dataNascimentoFuncionario", funcionario.getDtNasc());
        	req.setAttribute("cpfFuncionario", funcionario.getCpf());
        	req.setAttribute("selectSexoFuncionario", funcionario.getSexo());
        	req.setAttribute("celularFuncionario", funcionario.getCel());
        	req.setAttribute("emailFuncionario", funcionario.getEmail());
        	// Passando mensagem para página jsp
            req.setAttribute("message", message);
            req.getRequestDispatcher("Funcionarios/inserirFuncionario.jsp").forward(req, resp);
        } else {
            try {
                // Realiza a alteração do registro e envia mensagem para jsp
                Connection connection1 = ConnectionUtils.getConnection();
                DaoFuncionario funcionarioDao = new DaoFuncionario(connection1);
                funcionarioDao.inserir(funcionario);
                message = "Inclusão efetuada com sucesso";
                req.setAttribute("message", message);

            } catch (Exception ex) {
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
            }
            req.getRequestDispatcher("Funcionarios/inserirFuncionario.jsp").forward(req, resp);
        }
        
        
      
        req.getRequestDispatcher("Funcionarios/listarFuncionario.jsp").forward(req, resp);
    }
}