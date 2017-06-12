/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.vendas;

import br.com.senac.pi3.db.dao.DaoCliente;
import br.com.senac.pi3.db.dao.DaoVenda;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.model.funcionario.Funcionario;
import br.com.senac.pi3.model.vendas.ItemVenda;
import br.com.senac.pi3.model.vendas.Venda;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Souza08
 */
@WebServlet("/CadastraVenda")

public class CadastraVenda extends HttpServlet implements Serializable{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
            String cpf = req.getParameter("cpfClienteVendaCD");
            System.out.println(cpf);
        
        DaoVenda daoVenda = new DaoVenda(ConnectionUtils.getConnection());
        DaoCliente daoCliente = new DaoCliente(ConnectionUtils.getConnection());
        HttpSession session = req.getSession();
        
        Cliente cliente= null;
        try {
            cliente = daoCliente.buscarPorCPFobject(cpf);
            
            
        } catch (SQLException ex) {
            Logger.getLogger(CadastraVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Venda venda = new Venda();
        Funcionario funcionario = (Funcionario) session.getAttribute("logado");

        venda.setItens( (ArrayList<ItemVenda>) session.getAttribute("carrinho")  );
        
        venda.setCliente(cliente);
        venda.setFuncionario(funcionario);
        
        
        try {
            daoVenda.inserirVenda(venda);
        } catch (Exception ex) {

        }
         

    }


    
}
