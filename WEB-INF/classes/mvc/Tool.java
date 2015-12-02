package mvc;
import java.util.*;
import java.sql.*;

public class Tool
{
	private Connection con = null;
   	private Statement stmt = null;
   	private PreparedStatement ps = null;
   	private ResultSet rs = null;
   	private Personne laPersonne = null;
   	private ArrayList<Personne> listPersonne=null;

	public Tool(){}

	public ArrayList<Personne> rechPersonne(String nom,String prenom,String sexe,String tel,String fonction,String tri){

       	try {
          //Connexion a la base
         	Class.forName("org.postgresql.Driver");
         	String url = "jdbc:postgresql://psqlserv/da2i";
         	con = DriverManager.getConnection(url,"fevrer", "moi");
         	stmt= con.createStatement();

          //On prépare la requête
         	ps = con.prepareStatement("SELECT * FROM annuaire WHERE nom LIKE ? AND prenom LIKE ? AND sexe LIKE ? AND tel LIKE ? AND fonction LIKE ? ORDER BY "+tri+" ASC;");
          //On remplie la requête
         	ps.setString(1,nom+"%");
          ps.setString(2,prenom+"%");
          ps.setString(3,sexe+"%");
          ps.setString(4,tel+"%");  
          ps.setString(5,fonction+"%");       
          //On exécute la requête
         	rs = ps.executeQuery();

         	listPersonne = new ArrayList<Personne>();

          //Si la requête ne renvoie rien
         	if (!rs.next())
         		listPersonne = null;
          else {
  	       	do{
    	       		laPersonne = new Personne();
    	       		laPersonne.setNum(rs.getInt("num"));
    	       		laPersonne.setNom(rs.getString("nom"));
    	       		laPersonne.setPrenom(rs.getString("prenom"));
    	       		laPersonne.setSexe(rs.getString("sexe").charAt(0));
    	       		laPersonne.setTel(rs.getString("tel"));
                laPersonne.setFonction(rs.getString("fonction"));
    	       		listPersonne.add(laPersonne);
         		}while(rs.next()); //On enregistre la personne dans une ArrayList pour chaque ligne de la requête
       	}

   		} catch(Exception e) {}
   		finally { 
   			if(con != null) {
   				 try {con.close();
   				}catch(Exception e){}
   			}
   			return listPersonne; //On retourne la liste de Personne
   		}
	}

}