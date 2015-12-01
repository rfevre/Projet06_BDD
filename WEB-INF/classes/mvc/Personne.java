package mvc;

public class Personne
{
	private int num;
	private String nom;
	private String prenom;
	private char sexe;
	private String tel;
	private String fonction;

	public Personne(){	}

	public void setNum(int num){this.num=num;}
	public void setNom(String nom){this.nom=nom;}
	public void setPrenom(String prenom){this.prenom=prenom;}
	public void setSexe(char sexe){this.sexe=sexe;}
	public void setTel(String tel){this.tel=tel;}
	public void setFonction(String fonction){this.fonction=fonction;}

	public String getHTML(){
		return "<table>"+
				"<tr>"+
					"<th> num </th>"+
					"<th> nom </th>"+
					"<th> prenom </th>"+
					"<th> sexe </th>"+
					"<th> tel </th>"+
					"<th> fonction </th>"+
				"</tr>"+
				"<tr>"+
					"<td>" + num + "</td>"+
					"<td>" + nom + "</td>"+
					"<td>" + prenom + "</td>"+
					"<td>" + sexe + "</td>"+
					"<td>" + tel + "</td>"+
					"<td>" + fonction + "</td>"+
				"</tr>"+
				"</table>";
	}
}