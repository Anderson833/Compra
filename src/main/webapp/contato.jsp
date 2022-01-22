<%@page import="java.util.HashSet"%>
<%@page import="java.util.Set"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ page import="Modelo.Basebens"%>
<%@ page import="java.util.ArrayList"%>



<%
ArrayList<Basebens> lista = (ArrayList<Basebens>) request.getAttribute("contatos");
%>



<%
for (int i = 0; i < lista.size(); i++) {
%>

<%
out.print(lista.get(i).getNome());
%>
<%
out.print(lista.get(i).getEmail());
%>
<%
out.print(lista.get(i).getFone());
%>
<%
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="uft-8">
<title>Detalhe</title>

<link rel="stylesheet" href="styles.css">


</head>
<body>

	<h1>Olá Mundo</h1>

   <a href="relat">Relatorio</a>
	<table id="tabela">
		<thead>
			<tr>
				<th>Nome</th>
				<th>Fone</th>
				<th>Email</th>
				<th>Opções:</th>
			</tr>

		</thead>
		<tbody>
			<%
			for (int j = 0; j < lista.size(); j++) {
			%>


			<tr>
				<td><%=lista.get(j).getNome()%></td>
				<td><%=lista.get(j).getFone()%></td>
				<td><%=lista.get(j).getEmail()%></td>
				<td><a href="enviar?fone=<%=lista.get(j).getFone()%>">Editar</a>
				
				<a href="javascript: valida(<%=lista.get(j).getFone()%>)">Excluir</a>
		       
		       </td>
				  
			</tr>

          


			<%
			}
			%>


		</tbody>

	</table>
	<script src="script/confirma.js"></script>
   
     <a href="voltar">Voltar ao Inicio</a>
</body>
</html>