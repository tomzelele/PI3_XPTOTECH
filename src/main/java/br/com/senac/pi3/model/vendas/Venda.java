/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.model.vendas;

import br.com.senac.pi3.model.Pessoa.Pessoa;
import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.model.funcionario.Funcionario;
import br.com.senac.pi3.model.produto.Produto;
import java.util.ArrayList;
import java.util.Date;
/**
 *
 * @author Jonathan Souza
 */
public class Venda {

    
    private Integer cod_venda;   
    private Cliente cliente;
    private Funcionario funcionario;
    private ArrayList<ItemVenda> itens;
    private String  data;

    /**
     * @return the cod_venda
     */
    public Integer getCod_venda() {
        return cod_venda;
    }

    /**
     * @param cod_venda the cod_venda to set
     */
    public void setCod_venda(Integer cod_venda) {
        this.cod_venda = cod_venda;
    }

    /**
     * @return the valorVenda
     */
    

    /**
     * @return the cliente
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the itens
     */
    public ArrayList<ItemVenda> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(ArrayList<ItemVenda> itens) {
        this.itens = itens;
    }

    /**
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    
    
    
    
    public Double calcularValorTotal(){
        
        double vlTotal =0.0;
        
        for(ItemVenda item : itens ){
            
            vlTotal+=  (item.getProduto().getVlProd() * item.getQtd()  ) ;
        }
        System.out.print(vlTotal);
        return vlTotal;
    }
  
   
   
    
    
}
