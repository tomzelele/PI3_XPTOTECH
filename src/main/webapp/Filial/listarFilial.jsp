<%@page import="java.util.List"%>
<%@page import="br.com.senac.pi3.model.endereco.Endereco"%>
<%@page import="br.com.senac.pi3.model.filial.Filial"%>
<%@page import="br.com.senac.pi3.db.utils.ConnectionUtils"%>
<%@page import="br.com.senac.pi3.db.dao.DaoFilial"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
        <title>Consultar Filiais</title>
        
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
        
    </head>
    <body>   
        <form action="ExcluiFilial" method="post" name="formExcluirFilial">
            <input type="hidden" value="" name="idFilialExcluir">
        </form>

        <div id="wrapper3">
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>
                
                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-truck"></span>
                    <div id="textCustom" class="title">	<h2>Gerenciamento de Filiais</h2> </div>
                </div>
                
                <div class="voltar">
                    <a id="button" href="dashboard.jsp" class="button" style="border-radius: 10px;">Voltar</a>
                </div>
                
                <div class="inserirFuncionario">
                    <a type="button" class="btn btn-inserir" href="CadastraFilial">Inserir Filial</a>
                </div>
                
                 <div class="pesquisaNome">
                     
                     <form action="PesquisaFilial" method="POST" name="formPesquisarFilial"> 
                        <div class="col-lg-3">

                            <div  class="input-group custom-search-form">

                                <input required="" name="pesquisaNome" type="text" id="pesquisaNome" class="form-control" placeholder="Pesquisa por Nome" /> 

                                <span class="input-group-btn">                             

                                    <button type="button" class="btn btn-danger">

                                        <span class=" glyphicon glyphicon-search"></span>
                                    </button>
                                </span>
                            </div>
                        </div>
                    </form>
                    
                </div>
                

                <div class="container">
                    <div class="row">


                        <div class="col-md-12">
                            <div style="margin-top: -250px;" class="table-responsive">

                                <table id="mytable" class="table table-bordred table-striped">
                                    <thead>
                                    <th>Id</th>
                                    <th>Fantasia</th>
                                    <th>CNPJ</th>
                                    <th>Endereço</th>
                                    <th>Telefone</th>
                                    <th>Editar</th>
                                    <th>Deletar</th>
                                    </thead>
                                    
                                    <tbody>
                                        <% List<Filial> listaFiliais = (List<Filial>) session.getAttribute("listaFiliais"); %>
                                        <% for (Filial filial : listaFiliais) {%>
                                            <tr>
                                                 <td><%= filial.getIdFilial()%></td>
                                                <td><%= filial.getFantasia()%></td>
                                                <td><%= filial.getCnpj()%></td>
                                                <% Endereco endereco = filial.getEndereco();%>

                                                <td> <%= endereco.getRua() + " " + endereco.getNumero() + " " + endereco.getCidade() + "/" + endereco.getEstado()%> </td>
                                                <td><%= filial.getTelefone()%></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Edit"><a href="EditarFilial?idFilial=<%= filial.getIdFilial()%>" class="btn btn-primary btn-xs" data-title="Edit"  ><span class="glyphicon glyphicon-pencil"></span></a></p></td>
                                                <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs excluir-filial" data-idFilial="<%=filial.getIdFilial()%>" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                                            </tr>    


                                        <% }%>

                                    </tbody>

                                </table>

                                <div class="clearfix"></div>


                            </div>

                        </div>
                    </div>
                </div>


                <div class="modal fade" id="edit" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                <h4 class="modal-title custom_align" id="Heading">Editar Filial</h4>
                            </div>
                            <div class="modal-body">
                                <div class="form-group">
                                    <input class="form-control " type="text" placeholder="Mohsin">
                                </div>
                                <div class="form-group">

                                    <input class="form-control " type="text" placeholder="Irshad">
                                </div>
                                <div class="form-group">
                                    <textarea rows="2" class="form-control" placeholder="CB 106/107 Street # 11 Wah Cantt Islamabad Pakistan"></textarea>


                                </div>
                            </div>
                            <div class="modal-footer ">
                                <button type="button" class="btn btn-warning btn-lg" style="width: 100%;"><span class="glyphicon glyphicon-ok-sign"></span> Update</button>
                            </div>
                        </div>
                        <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog --> 
                </div>



                <div class="modal fade" id="delete" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                                <h4 class="modal-title custom_align" id="Heading">Deletar Filial</h4>
                            </div>
                            <div class="modal-body">

                                <div class="alert alert-danger"><span class="glyphicon glyphicon-warning-sign"></span> Você tem certeza que deseja excluir esse registro?</div>

                            </div>
                            <div class="modal-footer ">
                                <button type="button" class="btn btn-success btn-confirm-excluir" ><span class="glyphicon glyphicon-ok-sign"></span> Sim</button>
                                <button type="button" class="btn btn-default " data-dismiss="modal"><span class="glyphicon glyphicon-remove"></span> Não</button>
                            </div>
                        </div>
                        <!-- /.modal-content --> 
                    </div>
                    <!-- /.modal-dialog -->  
                </div>

            </div>
        </div>
        <c:import url="../Estrutura/footer.jsp"></c:import>
        <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
        <script>
            $(function () {
                
                $(".excluir-filial").on('click', function () {
                    
                    let idFilial = $(this).attr('data-idFilial');
                    
                    formExcluirFilial.idFilialExcluir.value = idFilial;
                });
                
                $('.btn-confirm-excluir').on('click', function () {
                    
                    formExcluirFilial.submit();
                })
            });
        </script>
    </body>
</html>

