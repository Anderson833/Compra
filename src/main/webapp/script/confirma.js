/**
 * 
 */


	
	
  

 function valida(fone){
	
	var fnD = confirm("Deseja Excluir Esse Contato ?")
	
	if(fnD===true){
		alert(fone)
		
		window.location.href="delete?fone="+fone;
	}
}








