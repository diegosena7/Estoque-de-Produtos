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

@WebServlet(urlPatterns = {"/ProdutosController", "/main", "/insert"})
public class ProdutosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    ProdutosDAO dao = new ProdutosDAO();
    ProdutosEntity produto = new ProdutosEntity();
    
    final String novoProdutoJsp = "/insert";
    final String indexJsp = "/main";
    
   public ProdutosController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminhoDaRequisicao = request.getServletPath();
		System.out.println(caminhoDaRequisicao);
		
		if (caminhoDaRequisicao.equals(indexJsp)) {
			listarProdutos(request, response);
		}else if(caminhoDaRequisicao.equals(novoProdutoJsp)) {
			cadastrarProdutos(request, response);
		}else {
			response.sendRedirect("main");
		}
	}
	
	//Lista de produtos
	protected void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProdutosEntity> listaDeProdutos = dao.listarProdutos();
		request.setAttribute("produtos", listaDeProdutos);
		RequestDispatcher rd = request.getRequestDispatcher("estoque.jsp");
		rd.forward(request, response);
	}
	
	protected void cadastrarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("nome"));
		System.out.println(request.getParameter("descricao"));
		System.out.println(request.getParameter("fabricante"));
		System.out.println(request.getParameter("preco"));
		
		produto.setNome(request.getParameter("nome"));
		produto.setDescricao(request.getParameter("descricao"));
		produto.setFabricante(request.getParameter("fabricante"));
		produto.setPreco(request.getParameter("preco"));
		
		dao.cadastrarProdutos(produto);
		
		response.sendRedirect("main");
	}
}
