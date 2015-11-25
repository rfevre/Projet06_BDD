<!-- affichage.html -->
<!DOCTYPE HTML>
<HTML>
<HEAD>
  <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@ page import="java.util.*" %>
  <%@ page import="java.io.File" %>
  <%@ page session="true" %>
  <%@ page errorPage="erreur.jsp" %>
  <%@ page contentType="text/html; charset=UTF-8" %>
   <TITLE>Page affichage</TITLE>
</HEAD>
<BODY>

<center>
<h1>Page pour l'affichage des tables</h1>
<%
   String login = (String)session.getAttribute("login");
   if (login==null)
   response.sendRedirect("index.html");

   File repertoire;
   File fichier=null;
   File nouveauFichier;
   String[] listeFichiers;

   
   %>
<br>
Bienvenue <%= login %> !
<br>
<form method="post" action="index.html">
<input type="submit" value="deconnecter" />
</form>
<br>
<br>
</center>

</BODY>
</HTML>
