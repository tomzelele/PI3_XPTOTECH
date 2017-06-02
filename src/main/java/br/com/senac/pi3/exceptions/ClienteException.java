package br.com.senac.pi3.exceptions;

import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.servlet.CadastrarCliente;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * 
 * @author Caio Fernandes
 */
public class ClienteException extends Exception {
    /**
     * Construtor de exceções que permite informar uma mensagem *
     */
    public ClienteException(String message) {
        super(message);
    }
    
    public ClienteException (Cliente cliente, String message) {
        super (message);
        // Validação dos campos
        // Nome
        if (cliente.getNome().equals("")) {
            try {
                throw new ClienteException("Informe o nome");
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Sobrenome
        if (cliente.getSobrenome().equals("")) {
            try {
                throw new ClienteException("Informe o sobrenome") ;
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // Data de Nascimento
        if (cliente.getDtNasc().equals("")) {
            try {
                throw new ClienteException("Informe a data de nascimento");
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // CPF
        if (cliente.getCpf().equals("")) {
            try {
                throw new ClienteException("Informe o CPF");
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // Celular
        if(cliente.getCel().equals("")) {
            try {
                throw new ClienteException("Informe o celular");
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        // E-mail
        if (cliente.getEmail().equals("")) {
            try {
                throw new ClienteException("Informe o e-mail");
            } catch (ClienteException ex) {
                Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    public ClienteException(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
