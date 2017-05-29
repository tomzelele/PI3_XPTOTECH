package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoEstoque;
import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.filial.Filial;
import java.io.IOException;
import java.sql.SQLException;
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
           
           DaoFilial dao = new DaoFilial(ConnectionUtils.getConnection());
           
           DaoEstoque  estoqueDao = new DaoEstoque(ConnectionUtils.getConnection());
           
           Filial filial = null;
        try {
           filial = dao.buscarPorId(idFilial);
        } catch (SQLException ex) {
            //Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*
        List<Categoria> listaCategoria = null;
        try {
            listaCategoria = categoriaDao.listarCategoria();
        } catch (SQLException ex) {
            Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }     
        listaCategoria.remove(produto.getCategoria());
        
        listaCategoria.set(0, produto.getCategoria());
        
        req.getSession().setAttribute("ListaCategoriaAtualiza", listaCategoria);
        */
        req.getSession().setAttribute("FilialAtualiza", filial);
        req.getRequestDispatcher("/Filial/editarFilial.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        Filial filial = new Filial();
        
        filial.setIdFilial(Integer.parseInt(req.getParameter("idFilial")));
        
        filial.setNome(req.getParameter("nome"));
        /*
        int idCategoria = Integer.parseInt(req.getParameter("categoriaProd"));
        try {        
            filial.setCategoria(new DaoCategoria(ConnectionUtils.getConnection()).buscarPorId(idCategoria));
        } catch (SQLException ex) {
            //Logger.getLogger(CadastrarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        filial.setVlProd(Double.parseDouble(req.getParameter("vlProd")));
        */
        try {
            filialDao.atualizarFilial(filial);
        } catch (Exception ex) {

        }        
        resp.sendRedirect("ListarFilial");
    }
    
    
}
