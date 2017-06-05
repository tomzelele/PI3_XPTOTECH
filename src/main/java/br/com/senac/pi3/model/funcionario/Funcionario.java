/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.model.funcionario;

import br.com.senac.pi3.model.filial.Filial;
import br.com.senac.pi3.model.Pessoa.Pessoa;
import br.com.senac.pi3.model.cargo.Cargo;
import br.com.senac.pi3.model.categoria.Categoria;
import br.com.senac.pi3.model.perfil.Perfil;

/**
 *
 * @author Souza08
 */
public class Funcionario extends Pessoa {
    
    private Integer idFuncionario;
    private Cargo cargo;
    private Integer codAcesso; 
    private Filial filial;
    
    /**
     * @return the idFuncionario
     */
    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the codAcesso
     */
    public Integer getCodAcesso() {
        return codAcesso;
    }

    /**
     * @param codAcesso the codAcesso to set
     */
    public void setCodAcesso(Integer codAcesso) {
        this.codAcesso = codAcesso;
    }

    /**
     * @return the filial
     */
    public Filial getFilial() {
        return filial;
    }

    /**
     * @param filial the filial to set
     */
    public void setFilial(Filial filial) {
        this.filial = filial;
    }

}
