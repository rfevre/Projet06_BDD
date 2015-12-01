<!-- index.html -->
<!DOCTYPE HTML>
<HTML>
<HEAD>
   <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
   	<%@ page import="java.util.*" %>
    <%@ page errorPage="erreur.jsp" %>
    <%@ page session="true" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
   <TITLE>Page annuaire</TITLE>
</HEAD>
<BODY>

<center>
<h1>Annuaire</h1>
</center>

<br>
<br>

<form method="post" action="recherche.jsp">
  <p>
    <label>Recherche par nom </label> : <input type="text" name="nom"/>
    <br>
    <br>
    <input type="submit" value="Valider" />
    </p>
</form>

<br>
<br>

<%
   	String erreur= (String)session.getAttribute("erreur");
   	if (erreur!=null) {
   		out.println(erreur);
   		session.removeAttribute("erreur");
    }
    else {
      HashMap<String,String> hashmap = (HashMap<String,String>)session.getAttribute("map");
     	if (hashmap!=null) {
     		out.println(hashmap);
     		session.removeAttribute("map");
      }
    }

   %>

</BODY>
</HTML>
