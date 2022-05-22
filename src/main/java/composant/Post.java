package composant;

import java.io.Serializable;

import java.util.List;

public class Post implements Serializable{
	private static final long serialVersionUID = 1L; 
	private int idP,idU,idG;
	private String titre, contenu, date, category,username;
	private List<Post> allPosts;
	
	
	
	public Post() {
		super();
	}

	public Post(int idU, int idG, String titre, String contenu,String category, String username) {
		super();
		this.idU = idU;
		this.idG = idG;
		this.titre = titre;
		this.contenu = contenu;
		this.category = category;
		this.username = username;
	}

	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public int getIdU() {
		return idU;
	}
	public void setIdU(int idU) {
		this.idU = idU;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getIdG() {
		return idG;
	}
	public void setIdG(int idG) {
		this.idG = idG;
	}
	public String getCategory() {
		return this.category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public List<Post> getAllPosts() {
		return allPosts;
	}
	public void setAllPosts(List<Post> allPosts) {
		this.allPosts = allPosts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
