
<%@page import="java.util.List"%>
<%@page import="br.com.senac.pi3.model.categoria.Categoria"%>
<%@page import="br.com.senac.pi3.model.produto.Produto"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Produto</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
        <link href="default.css" rel="stylesheet" type="text/css" media="all" />
        <link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

		<!-- Mensagens de alerta (CSS) -->
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
                    <div id="textCustom" class="title">	<h2>Editar Produto</h2> </div>
                </div>

                <a id="botaoCustom" href="/XPTOTECH/ListarProdutos" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario" >

                        <form method="post" action="EditarProduto">

                            <fieldset class="fieldProd">
                                <legend>Informações do produto</legend>  
                            </fieldset>   
                            
                            
                            <input value="" name="idProd" type="hidden" id="idProd" class="form-control input-sm"> </br>


                            <div class="nomeProd">
                                <label  for="nomeProd">Nome: </label>			
                                <input  value="" required="" name="nomeProd" type="text" id="nomeProd" class="form-control input-sm"> </br>
                            </div>

                            <div  class="categoriaProd">
                                <label  for="categoriaProd">Categoria: </label>
                            <select class="form-control" name="categoriaProd" id="categoriaProd" >
                                
                                    <option value=""></option>
                         
                            </select>
                            
                            </div>							
							
                            <div  class="vlProd">
                                <label  for="vlProd">Valor: </label>
                                <input  name="vlProd" value=""  type="text" id="vlProd" class="form-control input-sm"> </br>
                            </div>
                          
 

                            <div class="botaoCadastrarProd">
                                <input type="submit" class="btn btn-primary" value="Atualizar"></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <c:if test="${not empty message}" >                    
                <div class="bs-example">
                    <div class="alert alert-danger" id="myAlert">
                        <a href="#" class="close" data-dismiss="alert">&times;</a>
                        <p>${message}.</p>
                        </div>
                    </div>                                    
            </c:if>
        </div>      
        
		<script>
                $(document).ready(function(){
                    $("#myAlert").on('closed.bs.alert', function () {
                        
                    });
                });      
                
        </script>
        <c:import url="../Estrutura/footer.jsp"></c:import>
    </body>
</html>