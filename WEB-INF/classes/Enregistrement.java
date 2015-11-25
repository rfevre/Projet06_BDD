import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/enregistrement")
public class Enregistrement extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
    
        Connection con = null;
	Statement stmt = null;
        int rs = 0;

	String nom1="";
	String login1="";
	String mdp1="";

	HttpSession session = null;
	
	try {
	    //Chargement des variables de session
	    session = req.getSession(true);
	    //Enregistrement du Driver
	    Class.forName("org.postgresql.Driver");	    
	    //Connexion a la base
	    String url = "jdbc:postgresql://psqlserv/da2i";
	    con = DriverManager.getConnection(url,"fevrer","moi");
	    stmt = con.createStatement();

	    nom1= req.getParameter("nom");
	    login1 = req.getParameter("login");
	    mdp1 = req.getParameter("mdp");
	    
	    try {
		stmt.executeUpdate("INSERT INTO personne VALUES ('"+nom1+"','"+login1+"','"+mdp1+"')");
	    } catch (Exception e) {
				session.setAttribute("erreur","Login déjà utilisé");
	        res.sendRedirect("../enregistrement.jsp");
	    }

	    res.sendRedirect("../index.html");
	}
	
	catch(Exception e) {
	    out.println(e.getMessage());
	}	
	finally {
	    try {
		stmt.close();
		con.close();
	    }
	    catch(Exception e) {
		System.out.println(e.getMessage());
	    }
	}
    }
}
