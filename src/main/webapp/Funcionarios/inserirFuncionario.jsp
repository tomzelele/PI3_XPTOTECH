
<%@page import="br.com.senac.pi3.model.cargo.Cargo"%>
<%@page import="java.util.List"%>
<%@page import="br.com.senac.pi3.model.filial.Filial"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Perfil</title>
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
        
        <!-- Mensagens de alerta (CSS) -->
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
    </head>
    <body>
        <% List<Filial> listaFilial =  (List<Filial>)session.getAttribute("listaFilial") ;%>
        <% List<Cargo> listaCargo =  (List<Cargo>)session.getAttribute("listaCargo") ;%>

        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-group"></span>
                    <div id="textCustom" class="title">	<h2>Cadastro de Funcionários</h2> </div>
                </div>
                
                <a id="botaoCustom" href="/XPTOTECH/ListarFuncionario" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario">

                        <form method="post" action="../CadastraFuncionario">

                            <fieldset class="fieldCliente">
                                <legend>Dados Pessoais</legend>  
                            </fieldset>  
                            
                            <div class="codAcesso">					
                                <label  for="codAcesso">Código de Acesso: </label>			
                                <input  size="10" name="codAcesso" type="text" id="codAcesso" class="form-control input-sm" value="<%= request.getAttribute("codAcesso") != null ? request.getAttribute("codAcesso") : ""%>"> </br>
                            </div>
                            
                            <div class="cargo">					
                                <label  for="cargo">Cargo: </label>
                                <select class="form-control" name="cargo" id="cargo" >
                                    <% for(Cargo cargo : listaCargo){ %>
                                        <option value="<%= cargo.getIdCargo() %>"><%= cargo.getCargo() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div  class="filial">
                                <label  for="filial">Filial: </label>
                                <select class="form-control" name="filial" id="filial" >
                                    <% for(Filial filial : listaFilial){ %>
                                        <option value="<%= filial.getIdFilial() %>"><%= filial.getNome() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="nomeFuncionario">					
                                <label  for="nome">Nome: </label>			
                                <input  size="30" name="nomeFuncionario" type="text" id="nomeCliente" class="form-control input-sm" value="<%= request.getAttribute("nomeFuncionario") != null ? request.getAttribute("nomeFuncionario") : ""%>"> </br>
                            </div>

                            <div  class="sobreNomeFuncionario">
                                <label  for="sobreNomeFuncionario">Sobrenome: </label>
                                <input	name="sobreNomeFuncionario" type="text" size="40" id="sobreNomeCliente" class="form-control input-sm" value="<%= request.getAttribute("sobreNomeFuncionario") != null ? request.getAttribute("sobreNomeFuncionario") : ""%>"> </br>
                            </div>			

                            <div  class="dataNascimentoFuncionario">
                                <label 	for="dataNascimentoFuncionario">Data Nascimento:  </label>
                                <input 	maxlength="10" OnKeyPress="formatar('##/##/####', this)" name="dataNascimentoFuncionario" type="text" id="dataNascimentoCliente" class="form-control input-sm" value="<%= request.getAttribute("dataNascimentoFuncionario") != null ? request.getAttribute("dataNascimentoFuncionario") : ""%>"> </br>
                            </div>
                            
                            <div  class="cpfFuncionario">
                                <label  for="cpfFuncionario">CPF:  </label>
                                <input  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" name="cpfFuncionario" type="text" id="cpfCliente" class="form-control input-sm" value="<%= request.getAttribute("cpfFuncionario") != null ? request.getAttribute("cpfFuncionario") : ""%>"> </br>
                            </div>

                            <label  class="teste"  for="sexoFuncionario">Sexo:  </label>
                            <div  class="selectSexoFuncionario">
                                <select class="form-control" name="selectSexoFuncionario" >
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </div>
                            
                            <div	class="celularFuncionario">  
                                <label  for="celularFuncionario">Celular:  </label>
                                <input  maxlength="13" OnKeyPress="formatar('##-#####-####', this)" name="celularFuncionario" type="text" id="celularCliente" class="form-control input-sm" value="<%= request.getAttribute("celularFuncionario") != null ? request.getAttribute("celularFuncionario") : ""%>"> </br>
                            </div>
                                                   
                            <div class="emailFuncionario">  
                                <label for="emailFuncionario">Email:  </label>
                                <input size="50" name="emailFuncionario" type="text" id="emailFuncionario" class="form-control input-sm" value="<%= request.getAttribute("emailFuncionario") != null ? request.getAttribute("emailFuncionario") : ""%>"> </br></br>
                            </div>
                            
                            

                            
                            <div class="botaoCadastrarFuncionario">
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
