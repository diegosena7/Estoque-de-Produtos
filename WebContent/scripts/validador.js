/**
 * Validação do formulário
 */

function validar() {
	let nome = frmProdutos.nome.value
	let descricao = frmProdutos.descricao.value
	let fabricante = frmProdutos.fabricante.value
	let preco = frmProdutos.preco.value

	if (nome === "") {
		alert('Informe o nome do produto')
		frmProdutos.nome.focus()
		return false;
	} else if (descricao === "") {
		alert('Informe a descrição do produto')
		frmProdutos.descricao.focus()
		return false;
	} else if (fabricante === "") {
		alert('Informe o fabricante do produto')
		frmProdutos.fabricante.focus()
		return false;
	} else if (preco === "") {
		alert('Informe o preço do produto')
		frmProdutos.preco.focus()
		return false;
	} else {
		document.forms["frmProdutos"].submit()
	}
}