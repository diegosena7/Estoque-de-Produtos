package estoqueProdutos.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import estoqueProdutos.model.ProdutosDAO;
import estoqueProdutos.model.ProdutosEntity;

/**
 * The Class ProdutosController.
 * Servlet responsável pelas requisições feitas via browser.
 */
@WebServlet(urlPatterns = {"/ProdutosController", "/main", "/insert", "/select", "/update", "/delete", "/relatorio"})
public class ProdutosController extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    /** The dao. */
    ProdutosDAO dao = new ProdutosDAO();
    
    /** The produto. */
    ProdutosEntity produto = new ProdutosEntity();
    
    /** The index jsp. */
    final String indexJsp = "/main";
    
    /** The inserir produto jsp. */
    final String inserirProdutoJsp = "/insert";
    
    /** The selecionar produto id. */
    final String selecionarProdutoId = "/select";
    
    /** The editar produto. */
    final String editarProduto = "/update";
    
    /** The remover produto. */
    final String removerProduto = "/delete";
    
    /** The envia relatorio. */
    final String enviaRelatorio = "/relatorio";
    
   /**
    * Instantiates a new produtos controller.
    */
   public ProdutosController() {
    }

	/**
	 * Método principal do Servlet, redirecionando as requisições aos seus respectivos métodos.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String caminhoDaRequisicao = request.getServletPath();
		System.out.println("Requisição ao servlet: " + caminhoDaRequisicao);
		
		if (caminhoDaRequisicao.equalsIgnoreCase(indexJsp)) {
			listarProdutos(request, response);
		}else if(caminhoDaRequisicao.equalsIgnoreCase(inserirProdutoJsp)) {
			cadastrarProdutos(request, response);
		}else if (caminhoDaRequisicao.equalsIgnoreCase(selecionarProdutoId)) {
			selecionarProdutoPorId(request, response);
		}else if (caminhoDaRequisicao.equalsIgnoreCase(editarProduto)) {
			editarProdutos(request, response);
		}else if (caminhoDaRequisicao.equalsIgnoreCase(removerProduto)) {
			excluirProdutos(request, response);
		}else if (caminhoDaRequisicao.equalsIgnoreCase(enviaRelatorio)) {
			gerarRelatorioPDF(request, response);
		}else {
			response.sendRedirect("main");
		}
	}
	
	/**
	 * Classe responsável por listar todos os produtos da base de dados.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//Lista de produtos
	protected void listarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProdutosEntity> listaDeProdutos = dao.listarProdutos();
		request.setAttribute("produtos", listaDeProdutos);
		RequestDispatcher rd = request.getRequestDispatcher("estoque.jsp");
		rd.forward(request, response);
	}
	
	/**
	 * Classe responsável por cadastrar/persisitir um produto na base de dados.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Classe responsável por selecionar um produto para editar/excluir através do id.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void selecionarProdutoPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Id do produto recebido para edição: " + request.getParameter("id"));
		produto.setId(request.getParameter("id"));
		dao.selecionarProdutoPorId(produto);
		
		request.setAttribute("id", produto.getId());
		request.setAttribute("nome", produto.getNome());
		request.setAttribute("descricao", produto.getDescricao());
		request.setAttribute("fabricante", produto.getFabricante());
		request.setAttribute("preco", produto.getPreco());

		request.getRequestDispatcher("editarProduto.jsp").forward(request, response);
	}
	
	/**
	 * Classe responsável por editar dados do produto.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		produto.setNome(request.getParameter("nome"));
		produto.setDescricao(request.getParameter("descricao"));
		produto.setFabricante(request.getParameter("fabricante"));
		produto.setPreco(request.getParameter("preco"));
		
		dao.atualizarProduto(produto);
		
		response.sendRedirect("main");
	}
	
	/**
	 * Classe resposnsável por excluir um produto.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void excluirProdutos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Id do produto recebido para exclusão: " + request.getParameter("id"));
		produto.setId(request.getParameter("id"));
		dao.deletarProduto(produto);
	
		response.sendRedirect("main");
	}
	
	/**
	 * Classe responsável por gerar relatório dos produtos em formato PDF.
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorioPDF(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Document documento = new Document();
		try {
			//Tipo de conteúdo
			response.setContentType("apllication/pdf");
			
			//Nome do documento
			response.addHeader("Content-Disposition", "inline; filename=" + "Produtos.pdf");
			
			//Criando o PDF
			PdfWriter.getInstance(documento, response.getOutputStream());
			
			//Abrindo o doc
			documento.open();
			
			//Adicionando os valores ao doc
			documento.add(new Paragraph("Lista de produtos: "));
			documento.add(new Paragraph(" "));
			
			//Criando uma tabela no doc
			PdfPTable tabela = new PdfPTable(5);//núm de colunas na tabela
			//Cabeçalho
			PdfPCell coluna1 = new PdfPCell(new Paragraph("Id"));
			PdfPCell coluna2 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell coluna3 = new PdfPCell(new Paragraph("Descrição"));
			PdfPCell coluna4 = new PdfPCell(new Paragraph("Fabricante"));
			PdfPCell coluna5 = new PdfPCell(new Paragraph("Preço"));
			tabela.addCell(coluna1);
			tabela.addCell(coluna2);
			tabela.addCell(coluna3);
			tabela.addCell(coluna4);
			tabela.addCell(coluna5);
			
			//Populando a tabela a ser exibida no doc
			ArrayList<ProdutosEntity> lista = dao.listarProdutos();//Faz a chamada do método que busca os produtos no BD
			
			for (int i = 0; i < lista.size(); i++) {//percorre a lista de produtos e adiciona na tabela
				tabela.addCell(lista.get(i).getId());
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getDescricao());
				tabela.addCell(lista.get(i).getFabricante());
				tabela.addCell(lista.get(i).getPreco());
			}
			documento.add(tabela);
			
			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
