import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.servlet.annotation.MultipartConfig;

@MultipartConfig
@WebServlet("/servlet/upload")
public class Upload extends HttpServlet {

    protected void service(HttpServletRequest req,
				  HttpServletResponse res)
        throws ServletException, IOException {
	res.setContentType("text/html;charset=UTF-8");

	//Chargement des variables de session
	HttpSession session = req.getSession(true);	
	
	// On créer un chemin pour la sauvegarde du fichier
	final String path = getServletContext().getRealPath("/users/")+session.getAttribute("login");
	final Part filePart = req.getPart("file");
	final String fileName = getFileName(filePart);

	OutputStream out = null;
	InputStream filecontent = null;
	final PrintWriter writer = res.getWriter();

	try {
	    out = new FileOutputStream(new File(path + File.separator
						+ fileName));
	    filecontent = filePart.getInputStream();

	    int read = 0;
	    final byte[] bytes = new byte[1024];

	    while ((read = filecontent.read(bytes)) != -1) {
		out.write(bytes, 0, read);
	    }
	    session.setAttribute("uploadMsg","Nouveau fichier " + fileName + " créé à l'endroit suivant " + path);
	    res.sendRedirect("./affichageFichiers");
	} catch (FileNotFoundException fne) {
	    session.setAttribute("uploadMsg","Vous n'avez pas spécifié un fichier à Upload ou "
			   + "vous essayez d'Upload un fichier protégé ou un endroit "
				 + "inexistant.");
	    res.sendRedirect("./affichageFichiers");
	} finally {
	    if (out != null) {
		out.close();
	    }
	    if (filecontent != null) {
		filecontent.close();
	    }
	    if (writer != null) {
		writer.close();
	    }
	}
    }

    private String getFileName(final Part part) {
	final String partHeader = part.getHeader("content-disposition");
	for (String content : part.getHeader("content-disposition").split(";")) {
	    if (content.trim().startsWith("filename")) {
		return content.substring(
					 content.indexOf('=') + 1).trim().replace("\"", "");
	    }
	}
	return null;
    }
}
