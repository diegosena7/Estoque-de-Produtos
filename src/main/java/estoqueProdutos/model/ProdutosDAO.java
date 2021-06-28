package estoqueProdutos.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {
	
	ConexaoDAO conDAO = new ConexaoDAO();

	public ArrayList<ProdutosEntity> listarProdutos() {

		ArrayList<ProdutosEntity> produtos = new ArrayList<>();
		String buscar = "select * from produto order by id";
		try {
			Connection con = conDAO.conectaComBD();
			PreparedStatement pst = con.prepareStatement(buscar);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				String id = rs.getString(1);
				String nome = rs.getString(2);
				String descricao = rs.getString(3);
				String fabricante = rs.getString(4);
				String preco = rs.getString(5);
				produtos.add(new ProdutosEntity(id, nome, descricao, fabricante, preco));
			}
			con.close();
			return produtos;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}

	public void cadastrarProdutos(ProdutosEntity produto) {
		String inserir = "insert into produto (nome, descricao, fabricante, preco) values(?, ?, ?, ?);";
		try {
			Connection con = conDAO.conectaComBD();
			PreparedStatement pst = con.prepareStatement(inserir);
			
			pst.setString(1, produto.getNome());
			pst.setString(2, produto.getDescricao());
			pst.setString(3, produto.getFabricante());
			pst.setString(4, produto.getPreco());
			
			pst.executeUpdate();
			
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
