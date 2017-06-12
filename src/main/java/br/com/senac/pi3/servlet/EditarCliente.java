/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet;

import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoCliente;
import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.exceptions.ClienteException;
import br.com.senac.pi3.services.clientes.ServicoCliente;
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

/**
 *
 * @author Souza08
 */
@WebServlet("/EditarCliente")
public class EditarCliente  extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
        int idCliente = 0;
        idCliente= Integer.parseInt(request.getParameter("idCliente") );
        
        DaoCliente clienteDao  = new DaoCliente(ConnectionUtils.getConnection());
        Cliente cliente = null;
        try {
             cliente = clienteDao.buscarPorId( idCliente);
            response.getWriter().write(cliente.getNome());
        } catch (SQLException ex) {
         
        }
        
        request.getSession().setAttribute("clienteAtualiza", cliente);
        
        request.getRequestDispatcher("/Clientes/editarCliente.jsp").forward(request, response);
        
        System.out.println(idCliente);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();
        DaoEndereco enderecoDao = new DaoEndereco(connection);
        
        Cliente cliente = new Cliente();
        cliente.setId(Integer.parseInt(request.getParameter("idClienteAtualiza")));        
        cliente.setNome(request.getParameter("nomeCliente"));
        cliente.setSobrenome(request.getParameter("sobreNomeCliente"));
        cliente.setDtNasc(request.getParameter("dataNascimentoCliente"));
        cliente.setCpf(request.getParameter("cpfCliente"));
        cliente.setSexo(request.getParameter("selectSexoCliente"));
        cliente.setCel(request.getParameter("celularCliente"));
        cliente.setEmail(request.getParameter("emailCliente"));
        
        Endereco endereco = new Endereco();
        endereco.setId(Integer.parseInt(request.getParameter("idEnderecoAtualiza")));        
        endereco.setBairro(request.getParameter("bairroCliente"));
        endereco.setRua(request.getParameter("enderecoCliente"));
        endereco.setCep(request.getParameter("cepCliente"));
        endereco.setCidade(request.getParameter("cidadeCliente"));
        endereco.setEstado(request.getParameter("estadoCliente"));
        endereco.setNumero(request.getParameter("numEnderecoCliente"));
        
        ServicoCliente utilCliente = new ServicoCliente();
        String message = utilCliente.validarCampos(cliente, endereco);
        if (!message.equals("")) {
            // Obtendo os valores do formulário p/ manter o mesmo preenchido 
            request.setAttribute("nomeCliente", cliente.getNome());
            request.setAttribute("sobreNomeCliente", cliente.getSobrenome());
            request.setAttribute("dataNascimentoCliente", cliente.getDtNasc());
            request.setAttribute("selectSexoCliente", cliente.getSexo());
            request.setAttribute("celularCliente", cliente.getCel());
            request.setAttribute("cpfCliente", cliente.getCpf());
            request.setAttribute("emailCliente", cliente.getEmail());            
            request.setAttribute("bairroCliente", endereco.getBairro());
            request.setAttribute("enderecoCliente", endereco.getRua());
            request.setAttribute("cepCliente", endereco.getCep());
            request.setAttribute("cidadeCliente", endereco.getCidade());
            request.setAttribute("estadoCliente", endereco.getEstado());
            request.setAttribute("numEnderecoCliente", endereco.getNumero());
            // Passando mensagem para página jsp
            request.setAttribute("message", message);
            request.getRequestDispatcher("Clientes/inserir.jsp").forward(request, response);
        } else {
            try {
                enderecoDao.atualizarEndereco(endereco);
                connection.commit();
                connection.close();
            } catch (Exception ex) {
                message = "Erro na fonte de dados";
                request.setAttribute("message", message);
                request.getRequestDispatcher("Clientes/inserir.jsp").forward(request, response);
            }
            try { 
                Connection connection1 = ConnectionUtils.getConnection();
                DaoCliente clienteDao = new DaoCliente(connection1);
                clienteDao.atualizarCliente(cliente);
                message = "Alteração efetuada com sucesso";
                request.setAttribute("message", message);
                response.sendRedirect("ListarClientes");
            } catch (Exception ex) {
                message = "Erro na fonte de dados";
                request.setAttribute("message", message);
                response.sendRedirect("ListarClientes");
            }
        }
        
        
     //   response.sendRedirect("ListarClientes");

    }
}
