import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/affichageFichiers")
public class AffichageFichiers extends HttpServlet
{
    public void service( HttpServletRequest req, HttpServletResponse res ) 
	throws ServletException, IOException
    {
	PrintWriter out = res.getWriter();

	HttpSession session = null;
	
	//Chargement des variables de session
	session = req.getSession(true);

	File repertoire = new File (getServletContext().getRealPath("/")+"users/"+session.getAttribute("login"));
	String [] listeFichiers;  
	listeFichiers=repertoire.list(); 

	session.setAttribute("listeFichiers",listeFichiers);
	res.sendRedirect("../affichage.jsp");
	
    }
}
