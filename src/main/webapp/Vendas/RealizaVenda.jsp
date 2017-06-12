<%-- 
    Document   : RealizaVenda
    Created on : 08/06/2017, 21:04:39
    Author     : Souza08
--%>

<%@page import="br.com.senac.pi3.model.cliente.Cliente"%>
<%@page import="br.com.senac.pi3.model.vendas.ItemVenda"%>
<%@page import="br.com.senac.pi3.model.produto.Produto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
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
        <title>Realiza Venda</title>

        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
        

    </head>
    <body>
   
        <% 
            
             ArrayList<ItemVenda> carrinho  = new ArrayList<ItemVenda> ();
            if(request.getSession().getAttribute("carrinho") != null){
               carrinho = ( ArrayList<ItemVenda>)request.getSession().getAttribute("carrinho");  

            }
              
             Cliente clienteVenda = new Cliente();
        
        %>  
            

        
        <div id="wrapper3"> 
            <div id="three-column" class="container">
                <div><span class="arrow-down"></span></div>
                
                

                <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-suitcase"></span>
                    <div id="textCustom" class="title">	<h2>Gerenciamento de Vendas</h2> </div>
                </div>
                
                <form method="POST" action="../AdicionarItemVenda" name="formCadastraVenda" >
                    
                        <div class="clicpf">
                            <label>CPF Cliente: </label>	                           
                            <input required="" name="cpfClienteVenda" type="text" id="cpfClienteVenda" class="form-control input-sm" 
                                   value="<%= request.getSession().getAttribute("cpfClienteVenda")!= null ? request.getSession().getAttribute("cpfClienteVenda") : "" %>"> 
                        </div>
                      
                        <div class="cli">
                            <label>Cliente: </label>	                           
                            <input required="" name="clienteVenda" type="text" id="clienteVenda" class="form-control input-sm" readonly="true"
                                   value="<%= request.getSession().getAttribute("clienteVenda") != null ? request.getSession().getAttribute("clienteVenda") : "" %>"> 
                        </div>
                    
                        <div class="prod">
                            <label>Produto: </label>	                           
                            <input required="" name="prodvenda" type="text" id="prodVenda" class="form-control input-sm"> 
                        </div>
                    
                        <div class="qtdProduto">
                        <label>Qtd. Produto: </label>	                           
                        <input required="" name="qtdProduto" type="text" id="qtdProduto" class="form-control input-sm"> 
                        </div>
                    
                        <button class="btn btn-pesq">Adicionar Produto</button>

                </form>

                <a id="botaoCustom" href="dashboard.jsp" class="button" style="border-radius: 10px;">Voltar</a>

                <div class="container">
                    <div class="row">


                        <div class="col-md-12">
                            <div style="margin-top: -250px;" class="table-responsive">


                                <table     id="mytable" class="table table-bordred table-striped">

                                    <thead>

                                    <th>ID Produto</th>
                                    <th>Produto</th>
                                    <th>Categoria</th>
                                    <th>Qtd. Produto</th>
                                    <th>Valor Unitário</th>
                                   
                                    <th>Excluir</th>
                                    </thead>
                                    <tbody>
                                       
                                        
                                        <% for(ItemVenda itemVenda : carrinho){ %>
                                        
                                        <tr>
                                            <td><%= itemVenda.getProduto().getId() %></td>
                                            <td><%= itemVenda.getProduto().getProduto()%></td>
                                            <td><%= itemVenda.getProduto().getCategoria().getCategoria()%></td>
                                            <td><%= itemVenda.getQtd() %></td>
                                            <td><%= itemVenda.getProduto().getVlProd() %></td>
                                            <td><p data-placement="top" data-toggle="tooltip" title="Delete"><button class="btn btn-danger btn-xs excluir-produto"  data-idProduto="" data-title="Delete" data-toggle="modal" data-target="#delete" ><span class="glyphicon glyphicon-trash"></span></button></p></td>
                                        </tr> 
                                        <% } %>
                                    </tbody>

                                </table>

                                <div class="clearfix"></div>


                            </div>
                            
                                    <form method="post" action="../CadastraVenda" >
                                        
                                        <input type="hidden" name="cpfClienteVendaCD" id="cpfClienteVendaCD" value="" >
                                        <button  class="btn btn-inserir">Registrar Venda</button>

                                    </form>        
                                    
                                    
                        </div>
                    </div>
                    
                </div>


               

            </div>
        </div>

    <c:import url="Estrutura/footer.jsp"></c:import>
    <script
            src="https://code.jquery.com/jquery-1.12.4.min.js"
            integrity="sha256-ZosEbRLbNQzLpnKIkEdrPv7lOy9C27hHQ+Xp8a4MxAQ="
        crossorigin="anonymous"></script>
        
        
    <script>
            
            
            $("#cpfClienteVenda").on('change',function(){
                
                
                urlR = window.location.origin+'/XPTOTECH/ConsultaClienteCpf?cpf='+$("#cpfClienteVenda").val()
                
                $.ajax({
                    url: urlR
                    ,
                    success: function(resultado) {
                            
                            
                            $("#clienteVenda").val(resultado);
                            $("#cpfClienteVendaCD").val($("#cpfClienteVenda").val());
                            
                            alert( $("#cpfClienteVendaCD").val())
                            
                        
                    },
                    type: 'GET'
                 });
                
            })
            
            
            
            
    </script>

</body>
</html>

