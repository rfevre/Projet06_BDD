import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

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
		System.out.println(e.getMessage());
	    }
	}
    }
}
