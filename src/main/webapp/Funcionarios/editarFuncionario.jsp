
<%@page import="br.com.senac.pi3.model.cargo.Cargo"%>
<%@page import="br.com.senac.pi3.model.filial.Filial"%>
<%@page import="br.com.senac.pi3.model.funcionario.Funcionario"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Funcionário</title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
        <link href="default.css" rel="stylesheet" type="text/css" media="all" />
        <link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
        <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>

	<style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>

    </head>
    <body>
        <% Funcionario funcionario = (Funcionario) session.getAttribute("FuncionarioAtualiza");
            List<Filial> listaFilial = (List<Filial>) session.getAttribute("ListaFilialAtualiza");
            List<Cargo> listaCargo = (List<Cargo>) session.getAttribute("ListaCargoAtualiza");
        
        %>
        
        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-group"></span>
                    <div id="textCustom" class="title">	<h2>Editar Funcionário</h2> </div>
                </div>

                <a id="botaoCustom" href="/XPTOTECH/ListarFuncionarios" class="button">Voltar</a>

                <div class="container" >

                    <div class="cadastroFormulario" >

                        <form method="post" action="EditarFuncionario">

                            <fieldset class="fieldFuncionario">
                                <legend>Informações do Funcionário</legend>  
                            </fieldset>   
                            
                            
                            <input type="hidden" value="<%= funcionario.getId()%>" name="idFuncionarioAtualiza"> </br>


                            <div class="codAcesso">					
                                <label  for="codAcesso">Código de Acesso: </label>	
                                <input  requerid="" name="codAcesso" type="text" id="codAcesso" value="<%= funcionario.getCodAcesso()%>" class="form-control input-sm" > </br>
                            </div>
                            
                            <label class="testeCargo" for="cargo">Cargo: </label>
                            <div class="cargo">					
                                <select class="form-control" name="cargo" id="cargo" >
                                    <% for(Cargo cargo : listaCargo){ %>
                                        <option value="<%= cargo.getIdCargo() %>"><%= cargo.getCargo() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <label class="testeFilial"for="filial">Filial: </label>
                            <div  class="filial">
                                <select class="form-control" name="filial" id="filial" >
                                    <% for(Filial filial : listaFilial){ %>
                                        <option value="<%= filial.getIdFilial() %>"><%= filial.getNome() %></option>
                                    <% } %>
                                </select>
                            </div>
                            
                            <div class="nomeFuncionario">					
                                <label  for="nome">Nome: </label>			
                                <input  requerid="" name="nomeFuncionario" type="text" id="nomeFuncionario" value="<%= funcionario.getNome()%>" class="form-control input-sm" > </br>
                            </div>

                            <div  class="sobreNomeFuncionario">
                                <label  for="sobreNomeFuncionario">Sobrenome: </label>
                                <input  requerid="" name="sobreNomeFuncionario" type="text" id="sobreNomeFuncionario" value="<%= funcionario.getSobrenome()%>" class="form-control input-sm" > </br>
                            </div>			

                            <div  class="dataNascimentoFuncionario">
                                <label 	for="dataNascimentoFuncionario">Data Nascimento:  </label>
                                <input  maxlength="10" OnKeyPress="formatar('##/##/####', this)" requerid="" name="dataNascimentoFuncionario" type="text" id="dataNascimentoFuncionario" value="<%= funcionario.getDtNasc()%>" class="form-control input-sm" > </br>
                            </div>
                            
                            <div  class="cpfFuncionario">
                                <label  for="cpfFuncionario">CPF:  </label>
                                <input  maxlength="14" OnKeyPress="formatar('###.###.###-##', this)" requerid="" name="cpfFuncionario" type="text" id="cpfFuncionario" value="<%= funcionario.getCpf()%>" class="form-control input-sm" > </br>
                            </div>

                            <label  class="teste"  for="sexoFuncionario">Sexo:  </label>
                            <div  class="selectSexoFuncionario">
                                <select class="form-control" name="selectSexoFuncionario" >
                                    <% if (funcionario.getSexo().equals("M")) { %>
                                    <option value="M" >Masculino</option>
                                    <option value="F">Feminino</option>
                                    <% } else { %>
                                    <option value="F">Feminino</option>
                                    <option value="M" >Masculino</option>
                                    <% }%>
                                </select>
                            </div>
                            
                            <div class="celularFuncionario">  
                                <label  for="celularFuncionario">Celular:  </label>
                                <input  maxlength="13" OnKeyPress="formatar('##-#####-####', this)" requerid="" name="celularFuncionario" type="text" id="celularFuncionario" value="<%= funcionario.getCel()%>" class="form-control input-sm" > </br>
                            </div>
                                                   
                            <div class="emailFuncionario">  
                                <label style="margin-right: 12px" for="emailFuncionario">Email:  </label>
                                <input size="37" requerid="" name="emailFuncionario" type="text" id="emailFuncionario" value="<%= funcionario.getEmail()%>" class="form-control input-sm" > </br>
                            </div>
                            
                            <fieldset  class="fieldEnderecoFuncionario">
                                <legend>Endereço - Funcionário</legend>
                            </fieldset> 

                            <input type="hidden" value="<%= funcionario.getEndereco().getId()%>" name="idEnderecoAtualiza" > 
                            
                            <div class="enderecoFuncionario">
                                <label>Rua: </label> 
                                <input requerid="" size="30" type="text" id="enderecoFuncionario" maxlength="80" name="enderecoFuncionario" class="form-control input-sm" value="<%= funcionario.getEndereco().getRua()%>">
                            </div>
                            <div class="numEnderecoFuncionario">
                                <label>Número: </label> 
                                <input requerid="" size="4" type="text" id="numEnderecoFuncionario" maxlength="5" name="numEnderecoFuncionario" class="form-control input-sm" value="<%= funcionario.getEndereco().getNumero()%>" >
                            </div>
                            
                            <div class="cepFuncionario">
                                <label>Cep:</label>
                                <input OnKeyPress="formatar('#####-###', this)" requerid="" id="cepFuncionario"  type="text" maxlength="9" name="cepFuncionario" class="form-control input-sm" value="<%= funcionario.getEndereco().getCep()%>" > </br>
                            </div>
                            
                            <div class="bairroFuncionario">
                                <label>Bairro: </label> 
                                <input requerid="" type="text" id="bairroFuncionario" maxlength="40" name="bairroFuncionario"class="form-control input-sm" value="<%= funcionario.getEndereco().getBairro()%>">
                            </div>
                                                        
                            <div class="cidadeFuncionario">
                                <label>Cidade: </label>
                                <input requerid="" type="text" id="cidadeFuncionario" maxlength="40" name="cidadeFuncionario"class="form-control input-sm" value="<%= funcionario.getEndereco().getCidade()%>">
                            </div>
                            
                            <div class="estadoFuncionario">
                                <label>Estado: </label>
                                <input requerid="" size="6" type="text" id="estadoFuncionario" maxlength="2" name="estadoFuncionario"class="form-control input-sm" value="<%= funcionario.getEndereco().getEstado()%>"></br>
                            </div>
 

                            <div class="botaoCadastrarFuncionario">
                                <input type="submit" class="btn btn-primary" value="Atualizar"></button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>      

        <c:import url="../Estrutura/footer.jsp"></c:import>
    </body>
</html>