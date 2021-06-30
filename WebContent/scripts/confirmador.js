/**
 * Conforma a exclusão de um contato
 * Criamos a função confirmar, que recebe um idcon como parâmetro para confirmar a exclusão de um contato através do id
 * @param idcon
 * @author Diego Sena
 */

function confirmar(id){
	let resposta = confirm("Confirma a exclusão do produto?");
	
	if(resposta === true){
		window.location.href = "delete?id=" + id;
	}
}