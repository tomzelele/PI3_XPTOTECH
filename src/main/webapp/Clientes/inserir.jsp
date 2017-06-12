
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Cliente</title>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
    </head>
    <body>

        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-group"></span>
                    <div id="textCustom" class="title">	<h2>Cadastro de Clientes</h2> </div>
                </div>
                
                <a id="botaoCustom" href="/XPTOTECH/ListarClientes" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario">

                        <form method="post" action="../CadastraCliente">

                            <fieldset class="fieldCliente">
                                <legend>Dados Pessoais</legend>  
                            </fieldset>  

                            <div class="nomeCliente">					
                                <label  for="nome">Nome: </label>			
                                <input  size="30" requerid="" name="nomeCliente" type="text" id="nomeCliente" class="form-control input-sm"> </br>
                            </div>

                            <div  class="sobreNomeCliente">
                                <label  for="sobreNomeCliente">Sobrenome: </label>
                                <input requerid="" name="sobreNomeCliente" type="text" size="40" id="sobreNomeCliente" class="form-control input-sm"> </br>
                            </div>			

                            <div  class="dataNascimentoCliente">
                                <label  for="dataNascimentoCliente">Data Nascimento:  </label>
                                <input maxlength="10" requerid="" OnKeyPress="formatar('##/##/####', this)" name="dataNascimentoCliente" type="text" id="dataNascimentoCliente" class="form-control input-sm"> </br>
                            </div>

                            <label  class="testeCliente"  for="sexoCliente">Sexo:  </label>
                            <div  class="selectSexoCliente">
                                <select class="form-control" name="selectSexoCliente" >
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </div>
                            
                            <div required="" class="celularCliente">  
                                <label  for="celularCliente">Celular:  </label>
                                <input  maxlength="13" OnKeyPress="formatar('##-#####-####', this)" requerid="" name="celularCliente" type="text" id="celularCliente" class="form-control input-sm" </br>
                            </div>
                            <div  class="cpfCliente">
                                <label  for="cpfCliente">CPF:  </label>
                                <input requerid=""  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" name="cpfCliente" type="text" id="cpfCliente" class="form-control input-sm" > </br>
                            </div>
                                                        
                            <div class="emailCliente">  
                                <label for="emailCliente">Email:  </label>
                                <input size="50" name="emailCliente" type="text" id="emailCliente" class="form-control input-sm" </br></br>
                            </div>


                            <fieldset  class="fieldEndreco">
                                <legend>Endereço - Cliente</legend>
                            </fieldset> 

                            <div class="enderecoCliente">
                                <label>Rua: </label> 
                                <input requerid=""  type="text" id="enderecoCliente" maxlength="80" name="enderecoCliente" class="form-control input-sm">
                            </div>
                            <div class="numEnderecoCliente">
                                <label>Número: </label> 
                                <input size="4" requerid=""  type="text" id="numEnderecoCliente" maxlength="5" name="numEnderecoCliente" class="form-control input-sm">
                            </div>
                            
                            <div class="bairroCliente">
                                <label>Bairro: </label> 
                                <input requerid="" type="text" id="bairroCliente" maxlength="40" name="bairroCliente"class="form-control input-sm">
                            </div>
                            
                            <div class="cepCliente">
                                <label>Cep:</label>
                                <input requerid="" type="text" id="cepCliente"  OnKeyPress="formatar('#####-###', this)"maxlength="9" name="cepCliente" class="form-control input-sm" > </br>
                            </div>
                            
                            <div class="cidadeCliente">
                                <label>Cidade: </label>
                                <input requerid="" type="text" id="cidadeCliente" maxlength="40" name="cidadeCliente"class="form-control input-sm">
                            </div>
                            <div class="estadoCliente">
                                <label>Estado: </label>
                                <input size="5" requerid="" type="text" id="estadoCliente" maxlength="2" name="estadoCliente"class="form-control input-sm"></br>
                            </div>

                            <div class="botaoCadastrarCliente">
                                <input type="submit" class="btn btn-primary" value="Cadastrar"></button>
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
		function formatar(mascara, documento){
			var i = documento.value.length;
			var saida = mascara.substring(0,1);
			var texto = mascara.substring(i)
			
			if (texto.substring(0,1) != saida){
				documento.value += texto.substring(0,1);
			}
			
		}
                
                $(document).ready(function(){
                    $("#myAlert").on('closed.bs.alert', function () {
                        
                    });
                });
        </script>  
        <c:import url="../Estrutura/footer.jsp"></c:import>
    </body>
</html>
