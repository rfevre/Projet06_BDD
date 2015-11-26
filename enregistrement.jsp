<!-- enregistrement.html -->
<!DOCTYPE HTML>
<HTML>
  <HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page import="java.util.*" %>
    <%@ page session="true" %>
    <%@ page errorPage="erreur.jsp" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <TITLE>Page d'enregistrement</TITLE>
  </HEAD>
<BODY>

<center>
<h1>Page d'enregistrement</h1>
<br>

<%
   String erreur = (String)session.getAttribute("erreur");
   if (erreur!=null) {
   out.println(erreur);
   session.invalidate();
   }
   %>
</center>
<br>
<br>
<form method="post" action="./servlet/enregistrement">
  <p align="center">
    <label>Nom</label> : <input type="text" name="nom"/>
    <br>
    <br>
    <label>Login</label> : <input type="text" name="login"/>
    <br>
    <br>
    <label>Mdp</label> : <input type="password" name="mdp"/>
    <br>
    <br>
    <input type="submit" value="Valider" />
    </p>
</form>
<form action="./index.html">
  <p align="center">
    <input type="submit" value="Retour" />
  </p>
</form>

<br>
<br>

</BODY>
</HTML>
