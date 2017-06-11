package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.model.filial.Filial;
import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarFilial", urlPatterns = {"/CadastrarFilial"})
public class CadastraFilial extends HttpServlet{

      @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.sendRedirect("Filial/inserirFilial.jsp");
    }

   
    

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        Connection connection = ConnectionUtils.getConnection();
        DaoFilial filialDao = new DaoFilial(connection);
        
        Filial filial = new Filial();
        filial.setCnpj(request.getParameter("cnpj"));
        filial.setFantasia(request.getParameter("desc_fantasia"));
        filial.setNome(request.getParameter("desc_nome"));
        filial.setTelefone(request.getParameter("telefone"));

        
        Endereco endereco = new Endereco();
        endereco.setBairro(request.getParameter("bairroFilial"));
        endereco.setRua(request.getParameter("enderecoFilial"));
        endereco.setCep(request.getParameter("cepFilial"));
        endereco.setCidade(request.getParameter("cidadeFilial"));
        endereco.setEstado(request.getParameter("estadoFilial"));
        endereco.setNumero(request.getParameter("numEnderecoFilial"));
        
            // Obtendo os valores do formulário p/ manter o mesmo preenchido 
            request.setAttribute("cnpj", filial.getCnpj());
            request.setAttribute("desc_fantasia", filial.getFantasia());
            request.setAttribute("desc_nome", filial.getNome());
            request.setAttribute("telefone", filial.getTelefone());    
            request.setAttribute("bairroFilial", endereco.getBairro());
            request.setAttribute("enderecoFilial", endereco.getRua());
            request.setAttribute("cepFilial", endereco.getCep());
            request.setAttribute("cidadeFilial", endereco.getCidade());
            request.setAttribute("estadoFilial", endereco.getEstado());
            request.setAttribute("numEnderecoFilial", endereco.getNumero());
            // Passando mensagem para página jsp
            request.getRequestDispatcher("Filial/inserirFilial.jsp").forward(request, response);
        
 
             response.sendRedirect("ListarFilial");
        }
                
        
       
    }