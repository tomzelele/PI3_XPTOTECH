package br.com.senac.pi3.services.produtos;

/**
 *
 * @author Jonathan Souza
 */

import br.com.senac.pi3.db.dao.DaoProduto;
import br.com.senac.pi3.exceptions.DataSourceException;
import br.com.senac.pi3.exceptions.ProdutoException;
import br.com.senac.pi3.model.produto.Produto;
import java.util.List;

/**
 * Classe de serviço de produto *
 */
public class ServicoProduto {

    //Insere um produto na fonte de dados
    public static void cadastrarProduto(Produto produto)
            throws ProdutoException, DataSourceException {

        DaoProduto daoProduto = new DaoProduto();
        
        //Realização de validações de negócio
        if (produto == null) {
            throw new ProdutoException("Não foi informado um produto");
        }
        if (produto.getProduto() == null || "".equals(produto.getProduto())) {
            throw new ProdutoException("É necessário informar um nome para produto");
        }
        if (produto.getCategoria() == null || "".equals(produto.getCategoria())) {
            throw new ProdutoException("É necessário informar uma categoria para produto");
        }
        if (produto.getVlProd() == null
                || produto.getVlProd() < 0) {
            throw new ProdutoException("É necessário informar um valor para o produto");
        }
        
        try {
            //Realiza a chamada de inserção na fonte de dados
            daoProduto.inserirProduto(produto);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Atualiza um produto na fonte de dados
    public static void atualizarProduto(Produto produto)
            throws ProdutoException, DataSourceException {
        
        DaoProduto daoProduto = new DaoProduto();
        
        //Realização de validações de negócio
        if (produto == null) {
            throw new ProdutoException("Não foi informado um produto");
        }
        if (produto.getProduto() == null || "".equals(produto.getProduto())) {
            throw new ProdutoException("É necessário informar um nome para produto");
        }
        if (produto.getCategoria() == null || "".equals(produto.getCategoria())) {
            throw new ProdutoException("É necessário informar uma categoria para produto");
        }
        if (produto.getVlProd() == null
                || produto.getVlProd() < 0) {
            throw new ProdutoException("É necessário informar um valor para o produto");
        }

        try {
            //Realiza a chamada de atualização na fonte de dados
            daoProduto.atualizarProduto(produto);
            return;
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Realiza a pesquisa de um produto por nome na fonte de dados
    public static List<Produto> procurarProduto(String produto)
            throws ProdutoException, DataSourceException {
        
        DaoProduto daoProduto = new DaoProduto();
        
        try {
            //Verifica se um parâmetro de pesquisa não foi informado.
            //Caso afirmativo, realiza uma listagem simples do banco.
            //Caso contrário, realiza uma pesquisa com o parâmetro
            if (produto == null || "".equals(produto)) {
                return daoProduto.listarProduto();
            } else {
                return daoProduto.procurarProduto(produto);
            }
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }

    //Obtem o produto com ID informado do banco de dados
    public static Produto buscarPorId(Integer id)
            throws ProdutoException, DataSourceException {
        
        DaoProduto daoProduto = new DaoProduto();
        
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
    public static void excluirProduto(Integer id)
            throws ProdutoException, DataSourceException {
        
        DaoProduto daoProduto = new DaoProduto();
        
        try {
            //Solicita ao DAO a exclusão do produto informado
            daoProduto.excluirProduto(id);
        } catch (Exception e) {
            //Imprime qualquer erro técnico no console e devolve
            //uma exceção e uma mensagem amigável a camada de visão
            e.printStackTrace();
            throw new DataSourceException("Erro na fonte de dados", e);
        }
    }
    
    /**
     * Método responsável por realizar a validações do formulário de Cadastro de Clientes
     * @author cfaraujo
     * @param p (objeto produto)
     * @return msgErro
     */
     public String validarCampos(Produto p) {
    	String msgErro = "";
    	ServicoProduto utilProduto = new ServicoProduto();
    	
    	if (p.getProduto() == null || p.getProduto().equals("")) {
    		msgErro = "Informe o produto";
    		return msgErro;
    	} else {
    		try {
    			utilProduto.procurarProduto(p.getProduto());
    		} catch (Exception e) {
				
			}
    		
    	}
    	
    	if (p.getCategoria() == null || p.getCategoria().equals("")) {
    		msgErro = "Informe a categoria";
    		return msgErro;
    	} else {
    		
    	}
    	
    	if (p.getVlProd() == 0) {
    		if (p.getVlProd() < 0) {
    			msgErro = "Valor inválido";
    			return msgErro;
    		}
    		msgErro = "Informe o valor do produto";
    		return msgErro;
    	}
    	
    	return msgErro;
    }
}
