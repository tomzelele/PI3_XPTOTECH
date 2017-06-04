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
        
        // Validar campos
        String message = validarCampos(cliente, endereco);
        if (!message.equals("")) {
            request.setAttribute("message", message);
            request.getRequestDispatcher("Clientes/inserir.jsp").forward(request, response);
        }      
        
        try {
            
           
            enderecoDao.atualizarEndereco(endereco);
            connection.commit();
            connection.close();

        } catch (Exception ex) {
        }
        try { 
            
        Connection connection1 = ConnectionUtils.getConnection();
        
        DaoCliente clienteDao = new DaoCliente(connection1);
        clienteDao.atualizarCliente(cliente);

        } catch (Exception ex) {
        }
        
                response.sendRedirect("ListarClientes");


    }
    
    /**
     * Método responsável por realizar a validações do formulário de Cadastro de Clientes
     */
    public String validarCampos(Cliente c, Endereco e) {
        String msgErro = "";
        
        // Nome
        if (c.getNome() == null || c.getNome().equals("")) {
            msgErro = "Informe o nome";
        } else {
            
        }        
        // Sobrenome
        if (c.getSobrenome() == null || c.getSobrenome().equals("")) {
            msgErro = "Informe o sobrenome";
        } else {
            
        }
        // Data de Nascimento
        if (c.getDtNasc()== null || c.getDtNasc().equals("")) {
            msgErro = "Informe a data de nascimento";
        } else {
            
        }
        // CPF
        if (c.getCpf() == null || c.getCpf().equals("")) {
            msgErro = "Informe o CPF";
        } else {
            
        }
        // Sexo
        if (c.getSexo() == null || c.getSexo().equals("")) {
            msgErro = "Informe o sexo"; 
        } else {
            
        }
        // Celular
        if (c.getCel() == null || c.getCel().equals("")) {
            msgErro = "Informe o celular";
        } else {
            
        }
        // E-mail
        if (c.getEmail() == null || c.getEmail().equals("")) {
            msgErro = "Informe o e-mail";
        } else {

        }
        // Rua
        if (e.getRua() == null || e.getRua().equals("")) {
            msgErro = "Informe o nome da rua";
        } else {
            
        }
        // Numero
        if (e.getNumero() == null || e.getNumero().equals("")) {
            msgErro = "Informe o número residencial";
        } else {
            
        }
        // Bairro
        if (e.getBairro() == null || e.getBairro().equals("")) {
            msgErro = "Informe o bairro";
        } else {
            
        }
        // Cep
        if (e.getCep() == null || e.getCep().equals("")) {
            msgErro = "Informe o cep";
        } else {
            
        }
        // Cidade
        if (e.getCidade() == null || e.getCidade().equals("")) {
            msgErro = "Informe a cidade";
        } else {
            
        }
        // Estado
        if (e.getEstado() == null || e.getEstado().equals("")) {
            msgErro = "Informe o estado";
        } else {
            
        }
        
        return msgErro;
    }
}
