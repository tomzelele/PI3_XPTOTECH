<%@page import="java.util.List"%>
<%@page import="br.com.senac.pi3.model.endereco.Endereco"%>
<%@page import="br.com.senac.pi3.db.utils.ConnectionUtils"%>
<%@page import="br.com.senac.pi3.db.dao.DaoCliente"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
        <link href="default.css" rel="stylesheet" type="text/css" media="all" />
        <link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
    </head>
    <body>

        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-group"></span>
                    <div id="textCustom" class="title">	<h2>Cadastro de Filiais</h2> </div>
                </div>
                
                <a id="botaoCustom" href="/XPTOTECH/ListarFilial" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario">

                        <form method="post" action="CadastrarFilial">

                            <div class="cnpj">					
                                <label  for="nome">CNPJ: </label>			
                                <input  requerid="" maxlength="17" name="cnpj" type="text" id="cnpj" OnKeyPress="formatar('##.###.###/####-##', this)" class="form-control input-sm"> </br>
                            </div>

                            <div class="desc_nome">
                                <label  for="desc_nome">Razão social: </label>
                                <input requerid="" name="desc_nome" type="text" maxlength="50" id="desc_nome" class="form-control input-sm"> </br>
                            </div>			

                            <div  class="desc_fantasia">
                                <label  for="desc_fantasia">Fantasia:  </label>
                                <input  requerid="" name="desc_fantasia" maxlength="50" type="text" id="desc_fantasia" class="form-control input-sm"> </br>
                            </div>

                            <div  class="telefone">
                                <label  for="telefone">Telefone:  </label>
                                <input requerid="" maxlength="13" OnKeyPress="formatar('##-#####-####', this)" maxlength="10"  name="telefone" type="text" id="telefone" class="form-control input-sm" > </br>
                            </div>

                            <fieldset  class="fieldEndreco">
                                <legend>Endereço</legend>
                            </fieldset> 

                            <div class="enderecoCliente">
                                <label>Rua: </label> 
                                <input requerid="" size="30" type="text" id="enderecoCliente" maxlength="80" name="enderecoCliente" class="form-control input-sm">
                            </div>
                            <div class="numEnderecoCliente">
                                <label>Número: </label> 
                                <input requerid="" size="4" type="text" id="numEnderecoCliente" maxlength="5" name="numEnderecoCliente" class="form-control input-sm">
                            </div>
                            <div class="cepCliente">
                                <label>Cep:</label>
                                <input maxlength="9" OnKeyPress="formatar('#####-###', this)" requerid="" id="cepCliente"  type="cepCliente" maxlength="8" name="cepCliente" class="form-control input-sm" > </br>
                            </div>
                            <div class="bairroCliente">
                                <label>Bairro: </label> 
                                <input requerid="" type="text" id="bairroCliente" maxlength="40" name="bairroCliente"class="form-control input-sm">
                            </div>
                            <div class="cidadeCliente">
                                <label>Cidade: </label>
                                <input requerid="" type="text" id="cidadeCliente" maxlength="40" name="cidadeCliente"class="form-control input-sm">
                            </div>
                            <div class="estadoCliente">
                                <label>Estado: </label>
                                <input requerid="" size="6"type="text" id="estadoCliente" maxlength="2" name="estadoCliente"class="form-control input-sm"></br>
                            </div>

                            <div class="botaoCadastrarFilial">
                                <input type="submit" class="btn btn-primary" value="Cadastrar"></button>
                            </div>
                        </form>

                    </div>
                </div>


            </div>
        </div>            

        
        <script>
            function formatar(mascara, documento){
                var i = documento.value.length;
                var saida = mascara.substring(0,1);
                var texto = mascara.substring(i)

                if (texto.substring(0,1) != saida){
                        documento.value += texto.substring(0,1);
                }
            }
        </script>  
        <c:import url="../Estrutura/footer.jsp"></c:import>
    </body>
</html>
