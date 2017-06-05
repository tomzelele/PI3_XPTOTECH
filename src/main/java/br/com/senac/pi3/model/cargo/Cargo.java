/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.model.cargo;

/**
 *
 * @author Kelly
 */
public class Cargo {
    
    private Integer idCargo; 
    private String cargo; 

    /**
     * @return the idCargo
     */
    public Integer getIdCargo() {
        return idCargo;
    }

    /**
     * @param idCargo the idCargo to set
     */
    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
