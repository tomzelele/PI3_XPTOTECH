package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoEndereco;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.model.filial.Filial;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditarFilial")
public class EditarFilial extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int idFilial = Integer.parseInt( req.getParameter("idFilial") );
        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        DaoEndereco enderecoDao = new DaoEndereco(ConnectionUtils.getConnection());
           
        Filial filial = null;
        try {
           filial = filialDao.buscarPorId(idFilial);
        } catch (SQLException ex) {
           Logger.getLogger(EditarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Endereco endereco = new Endereco();
        try {
            endereco = enderecoDao.buscarPorId(filial.endereco.getId());
        } catch (SQLException ex) {
            Logger.getLogger(EditarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }     
        
        req.getSession().setAttribute("Endereco", endereco);
        req.getSession().setAttribute("FilialAtualiza", filial);
        req.getRequestDispatcher("Filial/editarFilial.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         
        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        DaoEndereco enderecoDao = new DaoEndereco(ConnectionUtils.getConnection());
        Filial filial = new Filial();
        filial.setIdFilial(Integer.parseInt(req.getParameter("IdFilialAtualiza")));
        
        filial.setNome(req.getParameter("desc_nome"));
        filial.setFantasia(req.getParameter("desc_fantasia"));
        filial.setCnpj(req.getParameter("cnpj"));
        filial.setTelefone(req.getParameter("telefone"));
        Endereco endereco = new Endereco();
        endereco.setId(Integer.parseInt(req.getParameter("idEnderecoAtualiza")));
        endereco.setBairro(req.getParameter("bairroCliente"));
        endereco.setRua(req.getParameter("enderecoCliente"));
        endereco.setCep(req.getParameter("cepCliente"));
        endereco.setCidade(req.getParameter("cidadeCliente"));
        endereco.setEstado(req.getParameter("estadoCliente"));
        endereco.setNumero(req.getParameter("numEnderecoCliente"));
       
        try {
            enderecoDao.atualizarEndereco(endereco);
        } catch (Exception ex) {
            Logger.getLogger(EditarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            filialDao.atualizarFilial(filial);
        } catch (Exception ex) {

        }        
        resp.sendRedirect("ListarFilial");
    }
    
    
}