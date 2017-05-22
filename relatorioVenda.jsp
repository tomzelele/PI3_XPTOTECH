<%-- 
    Document   : relatorioVenda
    Created on : 21/05/2017, 21:56:46
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
    <link href="default.css" rel="stylesheet" type="text/css" media="all" />
    <link href="fonts.css" rel="stylesheet" type="text/css" media="all" />

    <!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

    <title>Consultar Cliente</title>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="http://getbootstrap.com/dist/js/bootstrap.min.js"></script>
</head>
<div id="wrapper3">
    <div id="three-column" class="container">
        <div><span class="arrow-down"></span></div>

        <div id="tbox1" class="paginaDeGerenciamento"> <span class="icon icon-signal"></span>
            <div id="textCustom" class="title">	<h2>Relatório de Vendas</h2> </div>
        </div>

        <a id="botaoCustom" href="/XPTOTECH/gerarRelatorios" class="button">Voltar</a>
    </div>

    <div class="container">
        <div class="row">


            <div class="col-md-12">
                <div style="margin-top: -250px;" class="table-responsive">
                    <table     id="mytable" class="table table-bordred table-striped">
                        <thead>
                        <th>Filial</th>
                        <th>Data Inicial</th>
                        <th>Data Final</th>
                        <th>Procurar</th>
                        </thead>
                        <tbody>
                            <tr>
                                <td  class="form-group" > <input type="text" class="form-control" placeholder="Filial"> </td>
                                <td  class="form-group" > <input type="date" class="form-control" placeholder="Data Inicial"> </td>
                                <td  class="form-group" > <input type="date" class="form-control" placeholder="Data Final"> </td>
                                <td><p data-placement="top" data-toggle="tooltip" title="Edit"><a href="#" class="btn btn-success" data-title="Edit"  ><span class="glyphicon glyphicon-eye-open"></span></a></p></td>
                            </tr>
                        </tbody>
                    </table>
                    <div class="clearfix"></div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>
