 package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.services.filial.ServicoFilial;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.FilialException;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.model.filial.Filial;
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

@WebServlet(name = "CadastrarFilial", urlPatterns = {"/CadastraFilial"})
public class CadastrarFilial extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {        
        req.getRequestDispatcher("Filial/inserirFilial.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection connection = ConnectionUtils.getConnection();

        DaoFilial filialDao = new DaoFilial(connection);
        DaoEndereco enderecoDao = new DaoEndereco(connection);
        
        Filial filial = new Filial();
        Endereco endereco = new Endereco();

        filial.setNome(req.getParameter("desc_nome"));
        filial.setFantasia(req.getParameter("desc_fantasia"));
        filial.setCnpj(req.getParameter("cnpj"));
        filial.setTelefone(req.getParameter("telefone"));
        
        endereco.setBairro(req.getParameter("bairroCliente"));
        endereco.setRua(req.getParameter("enderecoCliente"));
        endereco.setCep(req.getParameter("cepCliente"));
        endereco.setCidade(req.getParameter("cidadeCliente"));
        endereco.setEstado(req.getParameter("estadoCliente"));
        endereco.setNumero(req.getParameter("numEnderecoCliente"));
        
        ServicoFilial utilFilial = new ServicoFilial();
        String message = utilFilial.validarCampos(filial, endereco);
        if (!message.equals("")) {
            // Obtendo os valores do formulário p/ manter o mesmo preenchido 
            req.setAttribute("cnpj", filial.getCnpj());
            req.setAttribute("desc_nome", filial.getNome());
            req.setAttribute("desc_fantasia", filial.getFantasia());
            req.setAttribute("telefone", filial.getTelefone());            
            req.setAttribute("bairroCliente", endereco.getBairro());
            req.setAttribute("enderecoCliente", endereco.getRua());
            req.setAttribute("cepCliente", endereco.getCep());
            req.setAttribute("cidadeCliente", endereco.getCidade());
            req.setAttribute("estadoCliente", endereco.getEstado());
            req.setAttribute("numEnderecoCliente", endereco.getNumero());
            // Passando mensagem para página jsp
            req.setAttribute("message", message);
            req.getRequestDispatcher("Filial/inserirFilial.jsp").forward(req, resp);
        } else {
            try {
                endereco = enderecoDao.inserir(endereco);
                filial.setEndereco(endereco);
                connection.commit();
                connection.close();
                System.out.println(endereco.getId());
                
                } catch (Exception ex) {
                }
            
            try {
                filialDao.inserirFilial(filial);
                message = "Inclusão efetuada com sucesso";
                req.setAttribute("message", message);
                resp.sendRedirect("ListarFilial");        
            } catch (FilialException ex) {
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataSourceException ex) {
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) { 
                message = "Erro na fonte de dados";
                req.setAttribute("message", message);
                Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    
    }       
}