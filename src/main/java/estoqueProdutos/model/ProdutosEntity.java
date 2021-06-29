package estoqueProdutos.model;

public class ProdutosEntity {

	private String id;
	private String nome;
	private String descricao;
	private String fabricante;
	private String preco;
	
	public ProdutosEntity() {
	}
	
	public ProdutosEntity(String id, String nome, String descricao, String fabricante, String preco) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.fabricante = fabricante;
		this.preco = preco;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getFabricante() {
		return fabricante;
	}
	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}
	public String getPreco() {
		return preco;
	}
	public void setPreco(String preco) {
		this.preco = preco;
	}
}
