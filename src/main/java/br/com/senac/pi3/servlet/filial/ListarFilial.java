package br.com.senac.pi3.servlet.filial;

import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.filial.Filial;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ListarFilial", urlPatterns = {"/ListarFilial"})

public class ListarFilial extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        DaoFilial filialDao = new DaoFilial(ConnectionUtils.getConnection());
        
        List<Filial> listaFilial = null;
        try {
            listaFilial =  filialDao.listarFilial();
        } catch (SQLException ex) {
        }
        req.getSession().setAttribute("listaFilial", listaFilial);
        
        req.getRequestDispatcher("/Filial/listarFilial.jsp").forward(req, resp);

    }
    
    
    
    
}
