<html>
  <head>
    <%@ page import="java.sql.*" errorPage="erreur.jsp" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
  </head>
  <body>
    <%
       String nom1 = request.getParameter("nom");
       String mdp1 = request.getParameter("mdp");

       if (nom1 == "" || mdp1 == "") {
       response.sendRedirect("login.html");
       }

       Class.forName("org.postgresql.Driver");
       String url = "jdbc:postgresql://psqlserv/da2i";
       Connection con = DriverManager.getConnection(url,"fevrer", "moi");
       Statement stmt= con.createStatement();

       ResultSet rs = stmt.executeQuery("SELECT nom,dat,ip FROM login WHERE nom LIKE '"+nom1+"' AND mdp LIKE '"+mdp1+"';");

       String nomTmp = "";
       String datTmp = "";
       String ipTmp = "";
       boolean test = false;

       while(rs.next()) {
       nomTmp = rs.getString("nom");
       if (nomTmp != null) {
       test = true;
       datTmp = rs.getString("dat");
       ipTmp = rs.getString("ip");
       break;
       }
       else {
       test = false;
       }
       }

       if (test) {
       out.println("Bonjour : "+nomTmp);
       %>
    <br>
    <%
       out.println("Dernier connexion : " + datTmp);
       out.println("<br>Votre ip : "+ ipTmp);   
       stmt.executeUpdate("UPDATE login SET dat = '"+new java.util.Date()+"' WHERE nom LIKE '"+nomTmp+"';");
       }
       else {
       rs = stmt.executeQuery("SELECT nom FROM login WHERE nom LIKE '"+nom1+"';");
       if(rs.next()==true)
       out.println("Mauvais mdp !");
       else {
       out.println("Bienvenue " +nom1+",");
       stmt.executeUpdate("INSERT INTO login VALUES ('"+nom1+"','"+mdp1+"','"+new java.util.Date()+"', '"+request.getRemoteAddr()+"');");
       out.println(" c'est votre 1ère connexion, ajout dans la table login de votre nom et mdp");
       }
       }
       
       %>
    <br>
    <br>
    <form action="login.html">
      <input type="submit" value="deconnecter"/>
    </form>
    
    <% con.close(); %>

  </body>
</html>

