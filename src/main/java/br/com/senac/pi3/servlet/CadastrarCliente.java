/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet;

import br.com.senac.pi3.db.dao.DaoCliente;
import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.exceptions.ClienteException;
import br.com.senac.pi3.services.clientes.ServicoCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jazon
 */
@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastraCliente"})
public class CadastrarCliente extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("Clientes/inserir.jsp");
    }

   
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Connection connection = ConnectionUtils.getConnection();
        DaoEndereco enderecoDao = new DaoEndereco(connection);
        
        Cliente cliente = new Cliente();
        cliente.setNome(request.getParameter("nomeCliente"));
        cliente.setSobrenome(request.getParameter("sobreNomeCliente"));
        cliente.setDtNasc(request.getParameter("dataNascimentoCliente"));
        cliente.setSexo(request.getParameter("selectSexoCliente"));
        cliente.setCel(request.getParameter("celularCliente"));
        
        String cpf = request.getParameter("cpfCliente");
        
        cpf = cpf.replace(".", "").replace("-", "");
        cliente.setCpf(cpf);
        cliente.setEmail(request.getParameter("emailCliente"));
        
        Endereco endereco = new Endereco();
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
            endereco = enderecoDao.inserir(endereco);
            cliente.setEndereco(endereco);
            connection.commit();
            connection.close();
            System.out.println(endereco.getId());

        } catch (Exception ex) {
            
        }
            
            System.out.println("1");
        try {
            Connection connection1 = ConnectionUtils.getConnection();
            DaoCliente clienteDao = new DaoCliente(connection1);
            clienteDao.inserir(cliente);
            message = "Inclusão efetuada com sucesso";
            request.setAttribute("message", message);
            
            response.sendRedirect("ListarClientes");
           // request.getRequestDispatcher("Clientes/inserir.jsp").forward(request, response);
                        System.out.println("2");

        } catch (Exception ex) {
            message = "Erro na fonte de dados";
            request.setAttribute("message", message);
                        System.out.println("3");

            request.getRequestDispatcher("Clientes/inserir.jsp").forward(request, response);
        }
        }
                
        
        //response.sendRedirect("ListarClientes");
    }
 }
