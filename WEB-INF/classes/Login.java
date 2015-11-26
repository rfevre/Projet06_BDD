import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.Properties;

@WebServlet("/servlet/login")
public class Login extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();
    
        Connection con = null;
	Statement stmt = null;
	ResultSet rs = null;

	String url = "";
	String nom = "";
	String mdp = "";

	String login1="";
	String mdp1="";

	//Chargement des variables de session
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

	    login1 = req.getParameter("login");
	    mdp1 = req.getParameter("mdp");

	    if (login1 == null || mdp1 == null)
		res.sendRedirect("../index.html");
	    
	    rs = stmt.executeQuery("SELECT login FROM personne WHERE login LIKE '"+login1+"' AND mdp LIKE '"+mdp1+"';");

	    String loginTmp = "";
	    String nomTmp = "";
	    boolean test = false;

	    if(rs.next()) {
		loginTmp = rs.getString("login");
		session.setAttribute("login",loginTmp);
		res.sendRedirect("./affichageFichiers");
	    }
	    else {
		res.sendRedirect("../enregistrement.jsp");
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
