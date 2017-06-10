package br.com.senac.pi3.services.clientes;

import br.com.senac.pi3.model.cliente.Cliente;
import br.com.senac.pi3.model.endereco.Endereco;

public class ServicoCliente {
	
    /**
     * Método responsável por realizar a validações do formulário de Cadastro de Clientes
     * @author cfaraujo
     * @param c (objeto cliente)
     * @param e (objeto endereço)
     * @return msgErro
     */
    public String validarCampos(Cliente c, Endereco e) {
        String msgErro = "";
        
        // Nome
        if (c.getNome() == null || c.getNome().equals("")) {
            msgErro = "Informe o nome";
            return msgErro;
        } else {
            
        }        
        // Sobrenome
        if (c.getSobrenome() == null || c.getSobrenome().equals("")) {
            msgErro = "Informe o sobrenome";
            return msgErro;
        } else {
            
        }
        // Data de Nascimento
        if (c.getDtNasc()== null || c.getDtNasc().equals("")) {
            msgErro = "Informe a data de nascimento";
            return msgErro;
        } else {
            
        }
        // CPF
        if (c.getCpf() == null || c.getCpf().equals("")) {
            msgErro = "Informe o CPF";
            return msgErro;
        } else {           
            if (!isCpf(c.getCpf()) == true) {
            	msgErro = "CPF inválido";
            	return msgErro;
            }      	
        }
        // Sexo
        if (c.getSexo() == null || c.getSexo().equals("")) {
            msgErro = "Informe o sexo"; 
            return msgErro;
        } else {
            
        }
        // Celular
        if (c.getCel() == null || c.getCel().equals("")) {
            msgErro = "Informe o celular";
            return msgErro;
        } else {
            
        }
        // E-mail
        if (c.getEmail() == null || c.getEmail().equals("")) {
            msgErro = "Informe o e-mail";
            return msgErro;
        } else {

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
	
	/**
     * Método responsável por realizar a validação do cpf
     * @author cfaraujo
     * @param cpf
     * @return true/false
     * */
    public boolean isCpf (String strCpf) {

        int     d1, d2;
        int     digito1, digito2, resto;
        int     digitoCPF;
        String  nDigResult;
        
        strCpf = removeContaMask(strCpf);
        System.out.println(strCpf);
        

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < strCpf.length() -1; nCount++)
        {
           digitoCPF = Integer.valueOf (strCpf.substring(nCount -1, nCount)).intValue();

           //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
           d1 = d1 + ( 11 - nCount ) * digitoCPF;

           //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
           d2 = d2 + ( 12 - nCount ) * digitoCPF;
        };

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
           digito1 = 0;
        else
           digito1 = 11 - resto;

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2)
           digito2 = 0;
        else
           digito2 = 11 - resto;

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = strCpf.substring (strCpf.length()-2, strCpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        return nDigVerific.equals(nDigResult);
     
    }
	
	/**
     * Método responsável por retirar a mascará do CPF para validação do mesmo
     * @author cfaraujo
     * @param cpf
     * @return pkWithoutMask
     * */
    
    public static String removeContaMask(String cpf) {
       	StringBuffer pkWithoutMask = new StringBuffer("");
       	String pk = cpf;
       	try {
       		if (pk != null) {
       	    	for (int i = 0; pk.length() > i; i++) {
       	    		if (pk.charAt(i) != '.') {
       	    			if (pk.charAt(i) != '-') {
       	    				pkWithoutMask.append(pk.charAt(i));
       	    			}
       	    		}
       		    }
       	    }
    		} catch (Exception e) {
    			e.printStackTrace();			
    		}
    		return pkWithoutMask.toString().trim();
    }

}
