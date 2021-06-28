package estoqueProdutos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import estoqueProdutos.model.ProdutosDAO;
import estoqueProdutos.model.ProdutosEntity;

@WebServlet(urlPatterns = {"/ProdutosController", "/main"})
public class ProdutosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    ProdutosDAO dao = new ProdutosDAO();
    
   public ProdutosController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		System.out.println(path);
		
		if (path.equals("/main")) {
			listarProdutos(request, response);
		}
	}
	
	//Lista de produtos
	protected void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProdutosEntity> listaDeProdutos = dao.listarProdutos();
		request.setAttribute("produtos", listaDeProdutos);
		RequestDispatcher rd = request.getRequestDispatcher("estoque.jsp");
		rd.forward(request, response);
	}
}
