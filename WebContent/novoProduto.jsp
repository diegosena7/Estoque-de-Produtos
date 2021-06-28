<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Estoque de Produtos</title>
<link rel="icon" href="imagens/estoque.png">
<link rel="stylesheet" href="style.css">
</head>
<meta charset="ISO-8859-1">
<title>Estoque</title>
</head>
<body>
	<h1>Cadastrar novo Produto</h1>
	<form name="frmProdutos" action="insert">
	<table>
		<tr><td><input type="text" name="nome" placeholder="Nome" class="Caixa1"><td></tr>
		<tr><td><input type="text" name="descricao" placeholder="Descrição" class="Caixa1"><td></tr>
		<tr><td><input type="text" name="fabricante" placeholder="Fabricante" class="Caixa1"><td></tr>
		<tr><td><input type="text" name="preco" placeholder="Preço" class="Caixa2"><td></tr>
	</table>
	<br>
	<input type="button" class="botao1" value="Cadastrar"onclick="validar()">
	<script src="scripts/validador.js"></script>
	</form>
</body>
</html>