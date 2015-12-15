<!-- testBeans.jsp -->
<!DOCTYPE HTML>
<HTML>
<HEAD>
   <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
   <link rel="stylesheet" href="css/style.css" />
    <%@ page errorPage="erreur.jsp" %>
    <%@ page import="java.util.*" %>
   <TITLE>Page de test Beans</TITLE>
</HEAD>
<BODY>

<center>
<h1>Page test Beans</h1>
</center>

<br>
<br>

<h2>Recherche :</h2>

<form method="post" action="BeansTool.jsp">
  <p>
  	<label>nom : </label><input type="text" name="nom"/> <br>
  	<label>prenom : </label><input type="text" name="prenom"/> <br>
  	<label>sexe : </label><input type="text" name="sexe"/> <br>
  	<label>tel : </label><input type="text" name="tel"/> <br>
  	<label>fonction : </label><input type="text" name="fonction"/> <br>
  	<br>
    <h2>Trie sur :</h2>
    <label>nom </label><input type="radio" name="tri" value="nom" checked/><br>
	<label>prenom </label><input type="radio" name="tri" value="prenom"/><br>
	<label>sexe </label><input type="radio" name="tri"value="sexe"/><br>
  	<label>tel </label><input type="radio" name="tri" value="tel"/><br>
  	<label>fonction </label><input type="radio" name="tri" value="fonction"/><br>
    <br>
    <input type="submit" value="Valider" />
   </p>
</form>
	
<jsp:useBean id="tool" class="mvc.Tool" scope="application" />

<% 	String nom1 = request.getParameter("nom");
	String prenom1 = request.getParameter("prenom");
	String sexe1 = request.getParameter("sexe");
	String tel1 = request.getParameter("tel");
	String fonction1 = request.getParameter("fonction");
	String tri1 = request.getParameter("tri");
	ArrayList<mvc.Personne> listPersonne= tool.rechPersonne(nom1,prenom1,sexe1,tel1,fonction1,tri1);
	if (listPersonne != null) { %>
		<table>
		<tr>
		<th> num </th>
		<th> nom </th>
		<th> prenom </th>
		<th> sexe </th>
		<th> tel </th>
		<th> fonction </th>
		</tr> 
		<% for(mvc.Personne i:listPersonne){	
				out.println(i.getHTML());
			}
	} %>

<br>
<br>

</BODY>
</HTML>