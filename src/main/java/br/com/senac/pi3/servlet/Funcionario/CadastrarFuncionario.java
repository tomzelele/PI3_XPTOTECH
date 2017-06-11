/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

import br.com.senac.pi3.db.dao.DaoCargo;
import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.model.endereco.Endereco;
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
        
        DaoCargo cargoDao = new DaoCargo(ConnectionUtils.getConnection());
        
        try {
            req.getSession().setAttribute("listaCargo", cargoDao.listarCargo()) ;
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
             
        req.getRequestDispatcher("Funcionarios/inserirFuncionario.jsp").forward(req, resp);
        
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionUtils.getConnection();
        
        DaoFuncionario funcionarioDao = new DaoFuncionario(connection);
        DaoEndereco enderecoDao = new DaoEndereco(connection);
        
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
        funcionario.setSexo(req.getParameter("selectSexoFuncionario"));
        funcionario.setCpf(req.getParameter("cpfFuncionario"));
        funcionario.setCel(req.getParameter("celularFuncionario"));
        funcionario.setEmail(req.getParameter("emailFuncionario"));
                
        Endereco endereco = new Endereco();
        endereco.setRua(req.getParameter("enderecoFuncionario"));
        endereco.setNumero(req.getParameter("numEnderecoFuncionario"));
        endereco.setBairro(req.getParameter("bairroFuncionario"));
        endereco.setCep(req.getParameter("cepFuncionario"));
        endereco.setCidade(req.getParameter("cidadeFuncionario"));
        endereco.setEstado(req.getParameter("estadoFuncionario"));
        
        try {
            endereco = enderecoDao.inserir(endereco);
            funcionario.setEndereco(endereco);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            funcionarioDao.inserirFuncionario(funcionario);
        } catch (Exception ex) {
            Logger.getLogger(CadastrarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        resp.sendRedirect("ListarFuncionarios");
    }
}