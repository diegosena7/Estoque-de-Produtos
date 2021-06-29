<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="utf-8">
<title>Estoque de Produtos</title>
<link rel="icon" href="imagens/estoque.png">
<link rel="stylesheet" href="style.css">
</head>
<body>
	<h1>Editar dados do produto</h1>
	<form action="update">
		<table>
			<tr><td><input type="text" name="id" id="caixa3" readonly value="<%out.println(request.getAttribute("id"));%>"></td></tr>		
			<tr><td><input type="text" name="nome" id="caixa1" readonly value="<%out.println(request.getAttribute("nome"));%>"></td></tr>		
			<tr><td><input type="text" name="descricao" id="caixa1" readonly value="<%out.println(request.getAttribute("descricao"));%>"></td></tr>		
			<tr><td><input type="text" name="fabricante" id="caixa1" readonly value="<%out.println(request.getAttribute("fabricante"));%>"></td></tr>		
			<tr><td><input type="text" name="preco" id="caixa2" onkeypress="$(this).mask('R$ #.##0,00', {reverse: true});" readonly value="<%out.println(request.getAttribute("preco"));%>"></td></tr>		
		</table>
		<br>
	<input type="button" class="botao1" value="Salvar"onclick="validar()">
	<script src="scripts/validador.js"></script>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.15/jquery.mask.min.js"></script>
	</form>
</body>
</html>