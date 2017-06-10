
package br.com.senac.pi3.services.funcionarios;

import br.com.senac.pi3.model.funcionario.Funcionario;

/**
 *
 * @author kelly.csilva12
 */
public class ServicoFuncionario {
    
     /**
     * Método responsável por realizar a validações do formulário de Cadastro de Clientes
     * @author cfaraujo
     * @param f (objeto funcionario)
     * @return msgErro
     */
    public String validarCampos(Funcionario f) {
        String msgErro = "";
        // Cargo
        if (f.getCargo() == null || f.getCargo().equals("")) {
            msgErro = "Informe o cargo";
        } else {
            
        }
        
        // Nome
        if (f.getNome() == null || f.getNome().equals("")) {
            msgErro = "Informe o nome";
        } else {
            
        }
        
        // Sobrenome
        if (f.getSobrenome() == null || f.getSobrenome().equals("")) {
            msgErro = "Informe o sobrenome";
        } else {
            
        }
        
        // Data de Nascimento
        if (f.getDtNasc() == null || f.getDtNasc().equals("")) {
            msgErro = "Informe a data de nascimento";
        } else {
            
        }
        
        // CPF
        if (f.getCpf() == null || f.getCpf().equals("")) {
            msgErro = "Informe o CPF";
        } else {
            if (!isCpf(f.getCpf())) {
                msgErro = "CPF inválido";
            }
        }
        
        // Sexo
        if (f.getSexo() == null || f.getSexo().equals("")) {
            msgErro = "Informe o sexo";
        } else {
            
        }
        
        // Celular 
        if (f.getCel() == null || f.getCel().equals("")) {
            msgErro = "Informe o celular";
        }
        else {
            
        }
        
        // Email
        if (f.getEmail() == null || f.getEmail().equals("")) {
            msgErro = "Informe o e-mail";
        } else {
            
        }
        
        return null;
    }
    
    /**
    * Método responsável por realizar a validação do cpf
    * @author cfaraujo
    * @param strCpf
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

        for (int nCount = 1; nCount < strCpf.length() -1; nCount++) {
        
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
