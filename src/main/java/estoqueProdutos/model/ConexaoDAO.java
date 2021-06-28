package estoqueProdutos.model;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoDAO {

	final String driver = "com.mysql.cj.jdbc.Driver";
	final String url = "jdbc:mysql://localhost:3306/estoque?useTimezone=true&serverTimezone=UTC";
	final String user = "dsena7";
	final String password = "Santosfc@1912";
	
	public Connection conectaComBD() {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
			return null;
		}
	}
}
