<!-- recherche.jsp -->
<!DOCTYPE HTML>
<HTML>
  <HEAD>
    <META http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <%@ page import="java.util.*" %>
    <%@ page import="java.sql.*" %>
    <%@ page session="true" %>
    <%@ page errorPage="erreur.jsp" %>
    <%@ page contentType="text/html; charset=UTF-8" %>
    <TITLE>Page recherche</TITLE>
  </HEAD>
<BODY>
    <%
   		Connection con = null;
   		Statement stmt = null;
   		PreparedStatement ps = null;
   		ResultSet rs = null;

    	String nom1 = request.getParameter("nom");

    	if (nom1.equals("")) {
    		session.setAttribute("erreur","Champ vide");
    		response.sendRedirect("index.jsp");
    	}


       	try {
       	Class.forName("org.postgresql.Driver");
       	String url = "jdbc:postgresql://psqlserv/da2i";
       	con = DriverManager.getConnection(url,"fevrer", "moi");
       	stmt= con.createStatement();

       	ps = con.prepareStatement("SELECT * FROM annuaire WHERE nom LIKE ?;");
       	ps.setString(1,nom1+"%");

       	rs = ps.executeQuery();

       	HashMap<String,String> map = new HashMap<String,String>();

       	if (rs.next()){
       		map.put("nom",rs.getString("nom"));
       		map.put("prenom",rs.getString("prenom"));
       		map.put("sexe",rs.getString("sexe"));
       		map.put("tel",rs.getString("tel"));
       		map.put("fonction",rs.getString("fonction"));
       		session.setAttribute("map",map);
       	}
       	else {
       		session.setAttribute("erreur","Personne inexistant");
        }

   		} catch(Exception e) {  }
   		finally { 
   			if(con != null) 
   				con.close();
   			response.sendRedirect("index.jsp");
   		}

       %>
</BODY>
</HTML>