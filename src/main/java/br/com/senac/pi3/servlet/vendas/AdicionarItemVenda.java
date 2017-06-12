/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.servlet.vendas;

import br.com.senac.pi3.db.dao.DaoProduto;
import br.com.senac.pi3.db.utils.ConnectionUtils;
import br.com.senac.pi3.model.produto.Produto;
import br.com.senac.pi3.model.vendas.ItemVenda;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet("/AdicionarItemVenda")

public class AdicionarItemVenda extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        
        DaoProduto daoProduto = new DaoProduto(ConnectionUtils.getConnection());
        
        ArrayList<ItemVenda> carrinho  =null;

        if(req.getSession().getAttribute("carrinho") != null){
            
            carrinho =  (ArrayList<ItemVenda>) req.getSession().getAttribute("carrinho") ;
        }else{
            carrinho = new  ArrayList<ItemVenda>();
            
            
        }
        
        
        ItemVenda itemVenda = new ItemVenda();
        
        Produto produto =  null;
        try {
             produto= daoProduto.buscarPorId(Integer.parseInt(req.getParameter("prodvenda")));
        } catch (SQLException ex) {
            Logger.getLogger(AdicionarItemVenda.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        itemVenda.setProduto(produto);
        itemVenda.setQtd(Integer.parseInt(req.getParameter("qtdProduto")));
        
        
        carrinho.add(itemVenda);
        req.getSession().setAttribute("carrinho",carrinho);
        
         
        
        req.getSession().setAttribute("cpfClienteVenda",req.getParameter("cpfClienteVenda"));
        req.getSession().setAttribute("clienteVenda",req.getParameter("clienteVenda"));

        resp.sendRedirect("Vendas/RealizaVenda.jsp");

        
        
    }
    
    
    
}
