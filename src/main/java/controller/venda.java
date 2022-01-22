package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;


import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Modelo.Basebens;
import Modelo.DAO;

@WebServlet(urlPatterns = {"/venda","/adicionar","/enviar","/main","/update","/delete","/relat","/voltar"})
public class venda extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	DAO dao = new DAO();
    Basebens contatos = new Basebens();
    public venda() {
        super();
       
    }

	
 	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	
		
	
		
		String n = request.getServletPath();
		
		if(n.equals("/enviar")) {
			visualizrContato(request, response);
			
		} else if(n.equals("/voltar")) {
			response.sendRedirect("cadrastro.html");
		
			
		}else if(n.equals("/adicionar")) {
		 
			 mensagem(request, response);
			//adicionarcontato(request, response);
			
			
		}else if(n.equals("/main")) {
		 
			listacontato(request, response);
			
			
		}else if(n.equals("/update")) {
			 atualizarcontato(request, response);
			
	
		}else if(n.equals("/delete")) {
			removerContato(request, response);
				
				
		}else if(n.equals("/relat")) {
		
				geraRelatorioPdf(request, response);
				
		}else {
			response.sendRedirect("cadrastro.html");
		}
		System.out.println(n);
		
	}
	
 	protected void mensagem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
 		//Pegando os dados
 		String nome = request.getParameter("nome");
 		String fone =request.getParameter("fone");
 		String email=request.getParameter("email");
 		
 		
 		
 		if(nome.isEmpty() || fone.isEmpty()|| email.isEmpty()) {
 		
 	  
 			response.sendRedirect("javascript:valida()");
 			
 			
 			
 		}else {
 			
 			 adicionarcontato(request, response);
 		 
 		}
 		
 	}	
 
	
	private void cont(HttpServletResponse response, HttpServletRequest request) throws IOException {
		response.sendRedirect("contato.jsp");
		
	}
	
	//Método para adicionar contato
    private void adicionarcontato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	
		
		
		contatos.setNome(request.getParameter("nome"));
		contatos.setFone(request.getParameter("fone"));
		contatos.setEmail(request.getParameter("email"));
		
	 // Método para  adicionar dados;
		  dao.adicionarDados(contatos);
		  
	   response.sendRedirect("cadrastro.html");
		
	}
      //Método para lista todos contatos
    private void listacontato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	ArrayList<Basebens> list = dao.listaDados();
        
		 request.setAttribute("contatos", list);
		 RequestDispatcher rt = request.getRequestDispatcher("contato.jsp");
		
		 rt.forward(request, response);
    	
    }
	    //Método para visualizar contato
	  private void visualizrContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		  
		 
		
			 
			 contatos.setFone( request.getParameter("fone"));
			 
			 dao.visualizarPorFone(contatos);
			 
			 System.out.println(contatos.getNome());
			 System.out.println(contatos.getFone());
			 System.out.println(contatos.getEmail());
			 
			 request.setAttribute("nome", contatos.getNome());
			 request.setAttribute("fone", contatos.getFone());
			 request.setAttribute("email", contatos.getEmail());
			 
			 RequestDispatcher rt = request.getRequestDispatcher("Edita.jsp");
				
			 rt.forward(request, response);
			 
	  }
	  //Método atualizar contato
	  private void atualizarcontato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	
		 
		  

			contatos.setNome(request.getParameter("nome"));
			contatos.setFone(request.getParameter("fone"));
			contatos.setEmail(request.getParameter("email"));
			
		   dao.alterarContato(contatos);
		  
		   response.sendRedirect("main");
	  }
	  
	  //Método para remover contato
	  protected void removerContato(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	  
		 
		  
	  	contatos.setFone(request.getParameter("fone"));
		
		 dao.removerDados(contatos);
		 response.sendRedirect("main");
	  }
	  
	  // Gera Relatório em PDF
	  protected void geraRelatorioPdf(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
		  
		  Document documento = new Document();
		  
		  try {
			
			  //tipo de conteúdo
			  response.setContentType("apllication/pdf");
			  //nome do documento
			  response.addHeader("Content-Disposition","inline; filename="+"contatos.pdf");
			  //criar documento
			  PdfWriter.getInstance(documento, response.getOutputStream());
			  //abrir pdf para gera conteudo
			  documento.open();
			  documento.add(new Paragraph("Lista Contatos:"));
			  documento.add(new Paragraph(""));
			  
			 
			  //criar tabela
			  PdfPTable tabela = new  PdfPTable(3);
			  // cabeçalho
			  PdfPCell   col1 = new PdfPCell(new Paragraph("nome"));
			  PdfPCell   col2 = new PdfPCell(new Paragraph("fone"));
			  PdfPCell   col3 = new PdfPCell(new Paragraph("email"));
			  tabela.addCell(col1);
			  tabela.addCell(col2);
			  tabela.addCell(col3);
			
			  // imprimindo os dados de forma dinamica

		    	ArrayList<Basebens> lista = dao.listaDados();
			    for(int i=0;i<lista.size();i++) {
				   tabela.addCell(lista.get(i).getNome());
				   tabela.addCell(lista.get(i).getFone());
				   tabela.addCell(lista.get(i).getEmail());
			   }
			    documento.add(tabela);
			  documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
		  
	  }
	  
	  private void validade(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  response.sendRedirect("javascript:valida()");
	  }
	  
}
