/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.Funcionario;

import br.com.senac.pi3.db.dao.DaoCargo;
import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.dao.DaoFuncionario;
import br.com.senac.pi3.model.cargo.Cargo;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.model.filial.Filial;
import br.com.senac.pi3.model.funcionario.Funcionario;
import br.com.senac.pi3.services.funcionarios.ServicoFuncionario;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.function.Predicate;
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
@WebServlet("/EditarFuncionario")

public class EditarFuncionario extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

           int idFuncionario = Integer.parseInt( req.getParameter("idFuncionario") );
           
           DaoFuncionario funcionarioDao = new DaoFuncionario(ConnectionUtils.getConnection());
           
           DaoCargo  cargoDao = new DaoCargo(ConnectionUtils.getConnection());
           DaoFilial  filialDao = new DaoFilial(ConnectionUtils.getConnection());
           
           Funcionario funcionario = null;
        
        try {
            funcionario = funcionarioDao.buscarPorId(idFuncionario);
        } catch (SQLException ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        List<Filial> listaFilial = null;
        
       
        try {
            listaFilial = filialDao.listarFiliais();
        } catch (Exception ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listaFilial.remove(funcionario.getFilial());
        listaFilial.set(0, funcionario.getFilial());
        
        List<Cargo> listaCargo = null;
        
        try {
            listaCargo = cargoDao.listarCargo();
        } catch (SQLException ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listaCargo.remove(funcionario.getCargo());
        listaCargo.set(0, funcionario.getCargo());
        
        req.getSession().setAttribute("ListaFilialAtualiza", listaFilial);
        req.getSession().setAttribute("ListaCargoAtualiza", listaCargo);
        req.getSession().setAttribute("FuncionarioAtualiza", funcionario);
        req.getRequestDispatcher("/Funcionarios/editarFuncionario.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Connection connection = ConnectionUtils.getConnection();
        
        DaoFuncionario funcionarioDao = new DaoFuncionario(connection);
        DaoEndereco enderecoDao = new DaoEndereco(connection);
        
        Funcionario funcionario = new Funcionario();
        
        funcionario.setId(Integer.parseInt(req.getParameter("idFuncionarioAtualiza")));
        
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
        endereco.setId(Integer.parseInt(req.getParameter("idEnderecoAtualiza")));
        endereco.setRua(req.getParameter("enderecoFuncionario"));
        endereco.setNumero(req.getParameter("numEnderecoFuncionario"));
        endereco.setBairro(req.getParameter("bairroFuncionario"));
        endereco.setCep(req.getParameter("cepFuncionario"));
        endereco.setCidade(req.getParameter("cidadeFuncionario"));
        endereco.setEstado(req.getParameter("estadoFuncionario"));
        
         
        try {
            enderecoDao.atualizarEndereco(endereco);
        } catch (Exception ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            funcionarioDao.atualizarFuncionario(funcionario);
        } catch (Exception ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        resp.sendRedirect("ListarFuncionarios");
        
    }
}
