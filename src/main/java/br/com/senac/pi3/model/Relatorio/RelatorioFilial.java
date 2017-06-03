/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.pi3.model.relatorio;

/**
 *
 * @author Nataly
 */
public class RelatorioFilial {

    private int idFilial;
    private String cnpj;
    private String descNome;
    private String descFantasia;
    private String telefone;
    private int qtdVendas;

    public int getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescNome() {
        return descNome;
    }

    public int getQtdVendas() {
        return qtdVendas;
    }

    public void setQtdVendas(int qtdVendas) {
        this.qtdVendas = qtdVendas;
    }

    public void setDescNome(String descNome) {
        this.descNome = descNome;
    }

    public String getDescFantasia() {
        return descFantasia;
    }

    public void setDescFantasia(String descFantasia) {
        this.descFantasia = descFantasia;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
