package estoqueProdutos.model;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutosDAO {

	public ArrayList<ProdutosEntity> listarProdutos(){
		
		ConexaoDAO conDAO = new ConexaoDAO();
		
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
				BigDecimal preco = rs.getBigDecimal(5);
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
}
