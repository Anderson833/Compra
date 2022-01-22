/**
 * 
 */

 function valida(){
	let nome = cadrastro.nome.value
	let fone = cadrastro.fone.value
	let email = cadrastro.email.value
	
	if(nome===""){
		alert("Preenchar o campo nome")
		cadrastro.nome.focus()
		return false
		
	}
	
	else if(fone===""){
		alert("Preenchar o campo fone")
		cadrastro.fone.focus()
		return false
	}
	
	else if(email===""){
		alert("Preenchar o campo email")
		cadrastro.email.focus()
		return false
	}else{
		
	
	    document.forms["cadrastro"].submit();
       	alert('Cliente cadrastrador com sucesso!')
	
	 }
	
}





