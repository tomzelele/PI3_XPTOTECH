<%-- 
    Document   : gerRelatorio
    Created on : 21/05/2017, 21:56:22
    Author     : Nataly
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title></title>
        <meta name="keywords" content="" />
        <meta name="description" content="" />
        <link href="http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,900" rel="stylesheet" />
        <style><%@include file="../CSS/default.css" %></style>
        <style><%@include file="../CSS/fonts.css" %></style>
    </head>
    <div id="wrapper3">
        <div id="three-column" class="container">
            <div><span class="arrow-down"></span></div>

            <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-signal"></span>
                <div id="textCustom" class="title">	<h2>Relatórios</h2> </div>
            </div>

            <a id="botaoCustom" href="../dashboard.jsp" class="button">Voltar</a>

            <a href="relatorioCliente.jsp" class="button botaoCustomizado">Gerar Relatório Cliente</a>
            <a href="relatorioFilial.jsp" class="button botaoCustomizado">Gerar Relatório Filial</a>
            <a href="relatorioVenda.jsp" class="button botaoCustomizado">Gerar Relatório Vendas</a>

        </div>
    </div>
    <div id="wrapper4">
        <div id="footer" class="container">
            <div>
                <header class="title">
                    <span class="byline">Empresa jovem e revolucionaria</span> </header>
                <ul class="contact">
                    <li><a href="#" class="icon icon-twitter"><span>Twitter</span></a></li>
                    <li><a href="#" class="icon icon-facebook"><span></span></a></li>
                </ul>
            </div>
        </div>
    </div>
</body>
</html>
