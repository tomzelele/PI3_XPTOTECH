package br.com.senac.pi3.model.filial;

import br.com.senac.pi3.model.endereco.Endereco;

public class Filial {
    
    private Integer idFilial; 
    private String Nome;
    private String Fantasia;
    private String Telefone;
    private String cnpj;
    private Endereco endereco;

    public Integer getIdFilial() {
        return idFilial;
    }

    public void setIdFilial(Integer idFilial) {
        this.idFilial = idFilial;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getFantasia() {
        return Fantasia;
    }

    public void setFantasia(String Fantasia) {
        this.Fantasia = Fantasia;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        this.Telefone = telefone;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
    
}
