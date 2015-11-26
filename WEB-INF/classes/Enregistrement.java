import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.Properties;

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

	String url = "";
	String nom = "";
	String mdp = "";

	String nom1="";
	String login1="";
	String mdp1="";

	HttpSession session = req.getSession(true);;
	
	try {
	    //Enregistrement du Driver
	    Class.forName("org.postgresql.Driver");
	    
	    //Chargement du fichier Props
	    Properties prop = new Properties();
	    try {
		prop.load(new FileInputStream(getServletContext().getRealPath("/Props.txt")));
	        url = prop.getProperty("url");
	        nom = prop.getProperty("nom");
		mdp = prop.getProperty("mdp");
	    } catch (Exception e) {
		out.println(e.getMessage());
	    }
	    
	    //Connexion a la base
	    con = DriverManager.getConnection(url,nom,mdp);
	    stmt = con.createStatement();

	    nom1= req.getParameter("nom");
	    login1 = req.getParameter("login");
	    mdp1 = req.getParameter("mdp");

	    if(nom1.equals("") || login1.equals("") || mdp1.equals("")){
		session.setAttribute("erreur","Champ vide");
		res.sendRedirect("../enregistrement.jsp");
	    }
	    else {
		try {
		    stmt.executeUpdate("INSERT INTO personne VALUES ('"+nom1+"','"+login1+"','"+mdp1+"')");
		} catch (Exception e) {
		    session.setAttribute("erreur","Login déjà utilisé");
		    res.sendRedirect("../enregistrement.jsp");
		}
		Runtime.getRuntime().exec("mkdir -p "+login1, null, new
					  File(getServletContext().getRealPath("/")+"/users") );
		res.sendRedirect("../index.html");
	    }
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
		out.println(e.getMessage());
	    }
	}
    }
}
