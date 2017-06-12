package br.com.senac.pi3.services.filial;

import br.com.senac.pi3.db.dao.DaoFilial;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.FilialException;
import br.com.senac.pi3.model.endereco.Endereco;
import br.com.senac.pi3.model.filial.Filial;
import java.util.List;

public class ServicoFilial {

    public String validarCampos(Filial f, Endereco e) {
        String msgErro = "";
        
        // Cnpj
        if (f.getCnpj() == null || f.getCnpj().equals("")) {
            msgErro = "Informe o CNPJ";
            return msgErro;
        }
        // Razão social
        if (f.getNome() == null || f.getNome().equals("")) {
            msgErro = "Informe a razão social";
            return msgErro;
        }
        // Nome fantasia
        if (f.getFantasia()== null || f.getFantasia().equals("")) {
            msgErro = "Informe o nome fantasia";
            return msgErro;
        }
        // Telefone
        if (f.getTelefone() == null || f.getTelefone().equals("")) {
            msgErro = "Informe o telefone";
            return msgErro;
        }
        // Rua
        if (e.getRua() == null || e.getRua().equals("")) {
            msgErro = "Informe o nome da rua";
            return msgErro;
        } else {
            
        }
        // Numero
        if (e.getNumero() == null || e.getNumero().equals("")) {
            msgErro = "Informe o número residencial";
            return msgErro;
        } else {
            
        }
        // Bairro
        if (e.getBairro() == null || e.getBairro().equals("")) {
            msgErro = "Informe o bairro";
            return msgErro;
        } else {
            
        }
        // Cep
        if (e.getCep() == null || e.getCep().equals("")) {
            msgErro = "Informe o cep";
            return msgErro;
        } else {
            
        }
        // Cidade
        if (e.getCidade() == null || e.getCidade().equals("")) {
            msgErro = "Informe a cidade";
            return msgErro;
        } else {
            
        }
        // Estado
        if (e.getEstado() == null || e.getEstado().equals("")) {
            msgErro = "Informe o estado";
            return msgErro;
        } else {
            
        }       
        return msgErro;
    }
    //Atualiza um produto na fonte de dados
    public static void atualizarProduto(Filial filial)
            throws FilialException, DataSourceException {
        
        DaoFilial daoFilial = new DaoFilial();
        
        //Realização de validações de negócio
        if (filial == null) {
            throw new FilialException("Não foi informado um produto");
        }
        if (filial.getNome() == null || "".equals(filial.getNome())) {
            throw new FilialException("É necessário informar um nome para produto");
        }
        if (filial.getFantasia() == null || "".equals(filial.getFantasia())) {
            throw new FilialException("É necessário informar um nome fantasia para a empresa");
        }
        if (filial.getCnpj() == null || "".equals(filial.getCnpj())) {
            throw new FilialException("É necessário informar um CNPJ");
        }
        if (filial.getTelefone() == null || "".equals(filial.getTelefone())) {
            throw new FilialException("É necessário informar um número de telefone");
        }

        try {
            //Realiza a chamada de atualização na fonte de dados
            daoFilial.atualizarFilial(filial);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Realiza a pesquisa de um produto por nome na fonte de dados
    public static List<Filial> procurarFilial(String filial)
            throws FilialException, DataSourceException {
        
        DaoFilial daoFilial = new DaoFilial();
        
        try {
            if (filial == null || "".equals(filial)) {
                return daoFilial.listarFilial();
            } else {
                return daoFilial.procurarFilial(filial);
            }
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Obtem o produto com ID informado do banco de dados
    public static Filial buscarPorId(Integer id)
            throws FilialException, DataSourceException {
        
        DaoFilial daoProduto = new DaoFilial();
        
        try {
            //Retorna o produto obtido com o DAO
            return daoProduto.buscarPorId(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Exclui o produto com ID informado do banco de dados
    public static void excluirFilial (Integer id)
            throws FilialException, DataSourceException {
        
        DaoFilial daoFilial = new DaoFilial();
        
        try {
            //Solicita ao DAO a exclusão do produto informado
            daoFilial.excluirFilial(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
}    

