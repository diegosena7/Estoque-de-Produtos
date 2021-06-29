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

@WebServlet(urlPatterns = {"/ProdutosController", "/main", "/insert", "/select"})
public class ProdutosController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    ProdutosDAO dao = new ProdutosDAO();
    ProdutosEntity produto = new ProdutosEntity();
    
    final String indexJsp = "/main";
    final String inserirProdutoJsp = "/insert";
    final String selecionarProdutoId = "/select";
    
   public ProdutosController() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminhoDaRequisicao = request.getServletPath();
		System.out.println("Requisição ao servlet: " + caminhoDaRequisicao);
		
		if (caminhoDaRequisicao.equals(indexJsp)) {
			listarProdutos(request, response);
		}else if(caminhoDaRequisicao.equals(inserirProdutoJsp)) {
			cadastrarProdutos(request, response);
		}else if (caminhoDaRequisicao.equals(selecionarProdutoId)) {
			selecionarProduto(request, response);
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
	
	protected void selecionarProduto(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Id do produto recebido: " + request.getParameter("id"));
		produto.setId(request.getParameter("id"));
		dao.selecionarProdutoPorId(produto);
		
		request.setAttribute("id", produto.getId());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("descricao", produto.getDescricao());
		request.setAttribute("fabricante", produto.getFabricante());
		request.setAttribute("preco", produto.getPreco());

		request.getRequestDispatcher("editarProduto.jsp").forward(request, response);
	}
	
	protected void editarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		produto.setNome(request.getParameter("nome"));
		produto.setDescricao(request.getParameter("descricao"));
		produto.setFabricante(request.getParameter("fabricante"));
		produto.setPreco(request.getParameter("preco"));
		
		dao.atualizarProduto(produto);
		
		response.sendRedirect("main");
	}
}
