<!-- affichage.html -->
<!DOCTYPE HTML>
<HTML>
  <HEAD>
  <link rel="stylesheet" href="css/style.css" />
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

	 String[] listeFic = (String[])session.getAttribute("listeFichiers");
	 %>
      
<br>
Bienvenue <%= login %> !
<br>
<br>

<TABLE>
  <tr>
    <th> <%= "Fichier de l'utilisateur "+session.getAttribute("login") %></th>
  </tr>
  <% for(int i=0;i< listeFic.length;i++){ %>
       <tr>
	 <td><a href="<%= "./users/"+session.getAttribute("login")+"/"+listeFic[i]%>" > <%= listeFic[i] %> </a></td>
       </tr>
       <% } %>
</TABLE>
   <br>
   <br>
   <form method="POST" action="./servlet/upload" enctype="multipart/form-data" >
     File:
     <input type="file" name="file" id="file" />
     <br>
     <br>
     <input type="submit" value="Upload" name="upload" id="upload" />
   </form>
   <br>
   <br>
   
   <form method="post" action="index.html">
     <input type="submit" value="Deconnecter" />
   </form>
   <br>
   <br>
</center>

</BODY>
</HTML>
