<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Edita</title>
</head>
<body>

 <h1>Edita contato</h1>
   
 

    
   <form name="edita" action="update">
     Nome:
    <input type="text" name="nome" size="20" value="<%out.print(request.getAttribute("nome")); %>"  ></br></br>
     Fone:
     <input type="text" name="fone" size="20" value="<%out.print(request.getAttribute("fone")); %>"  ></br></br>
     
     Email:
     <input type="text" name="email" size="20" value="<%out.print(request.getAttribute("email")); %>" ></br></br>
   
     <input type="submit" value="editar" size="50" style="color: blue;">
     
   </form>



</body>
</html>