<%@page import="estoqueProdutos.model.ProdutosEntity"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
@SuppressWarnings("unchecked")
ArrayList<ProdutosEntity> produtos = (ArrayList<ProdutosEntity>)request.getAttribute("produtos");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Estoque de Produtos</title>
<link rel="icon" href="imagens/estoque.png">
<link rel="stylesheet" href="style.css">
</head>
<meta charset="ISO-8859-1">
</head>
<body>
	<h1>Produtos em Estoque</h1>
	<a href="" class="botao1">Novo Produto</a>
	<table id="tabela">
		<thead>
		<tr>
			<th>Id</th>
			<th>Nome</th>
			<th>Descrição</th>
			<th>Fabricante</th>
			<th>Preço</th>
		</tr>
		</thead>
		<tbody>
		<%for(int i = 0; i < produtos.size(); i ++){ %>
		<tr>
			<td><%=produtos.get(i).getId() %></td>
			<td><%=produtos.get(i).getNome() %></td>
			<td><%=produtos.get(i).getDescricao() %></td>
			<td><%=produtos.get(i).getFabricante() %></td>
			<td><%=produtos.get(i).getPreco() %></td>
		</tr>	
		<% }%>
		</tbody>
	</table>
</body>
</html>