<%-- 
    Document   : inserirFuncionario
    Created on : 03/06/2017, 21:18:22
    Author     : Kelly
--%>

<%@page import="java.util.List"%>
<%@page import="br.com.senac.pi3.model.filial.Filial"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inserir Usuário</title>
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
        <% List<Filial> lista =  (List<Filial>)session.getAttribute("listaFilial") ;%>

        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-group"></span>
                    <div id="textCustom" class="title">	<h2>Cadastro de Funcionários</h2> </div>
                </div>
                
                <a id="botaoCustom" href="/XPTOTECH/ListarFuncionarios" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario">

                        <form method="post" action="../CadastraFuncionario">

                            <fieldset class="fieldCliente">
                                <legend>Dados Pessoais</legend>  
                            </fieldset>  
                            
                            <div class="codAcesso">					
                                <label  for="codAcesso">Código de Acesso: </label>			
                                <input  size="10" requerid="" name="codAcesso" type="text" id="codAcesso" class="form-control input-sm"> </br>
                            </div>
                            
                            <div class="cargo">					
                                <label  for="cargo">Cargo: </label>			
                                <input  size="30" requerid="" name="cargo" type="text" id="nomeCliente" class="form-control input-sm"> </br>
                            </div>
                            
                            <div  class="filial">
                                <label  for="filial">Filial: </label>
                                <select class="form-control" name="filial" id="filial" >
                                
                                    <% for(Filial filial : lista){ %>
                                        <option value="<%= filial.getIdFilial() %>"><%= filial.getNome() %></option>
                                    <% } %>
                                </select>
                            
                            </div>
                                
                            <label  class="teste"  for="perfil">Perfil de Acesso:  </label>
                            <div  class="perfilFuncionario">
                                <select class="form-control" name="perfilFuncionario" >
                                    <option value="M">Administrador</option>
                                    <option value="F">Gerente</option>
                                    <option value="F">Vendedor</option>
                                </select>
                            </div>    
                            
                            <div class="nomeFuncionario">					
                                <label  for="nome">Nome: </label>			
                                <input  size="30" requerid="" name="nomeFuncionario" type="text" id="nomeCliente" class="form-control input-sm"> </br>
                            </div>

                            <div  class="sobreNomeFuncionario">
                                <label  for="sobreNomeFuncionario">Sobrenome: </label>
                                <input requerid="" name="sobreNomeFuncionario" type="text" size="40" id="sobreNomeCliente" class="form-control input-sm"> </br>
                            </div>			

                            <div  class="dataNascimentoFuncionario">
                                <label  for="dataNascimentoFuncionario">Data Nascimento:  </label>
                                <input maxlength="10" requerid="" OnKeyPress="formatar('##/##/####', this)" name="dataNascimentoFuncionario" type="text" id="dataNascimentoCliente" class="form-control input-sm"> </br>
                            </div>
                            
                            <div  class="cpfFuncionario">
                                <label  for="cpfFuncionario">CPF:  </label>
                                <input requerid=""  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" name="cpfFuncionario" type="text" id="cpfCliente" class="form-control input-sm" > </br>
                            </div>

                            <label  class="teste"  for="sexoFuncionario">Sexo:  </label>
                            <div  class="selectSexoFuncionario">
                                <select class="form-control" name="selectSexoFuncionario" >
                                    <option value="M">Masculino</option>
                                    <option value="F">Feminino</option>
                                </select>
                            </div>
                            
                            
                            <div required="" class="celularFuncionario">  
                                <label  for="celularFuncionario">Celular:  </label>
                                <input  maxlength="13" OnKeyPress="formatar('##-#####-####', this)" requerid="" name="celularFuncionario" type="text" id="celularCliente" class="form-control input-sm" </br>
                            </div>

                            <div  class="cpfCliente">
                                <label  for="cpfCliente">CPF:  </label>
                                <input requerid=""  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" name="cpfFuncionario" type="text" id="cpfCliente" class="form-control input-sm" > </br>
                            </div>
                                                      

                            <div class="emailFuncionario">  
                                <label for="emailFuncionario">Email:  </label>
                                <input size="50" name="emailFuncionario" type="text" id="emailFuncionario" class="form-control input-sm" </br></br>
                            </div>

                            
                            <div class="botaoCadastrarFuncionario">
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
