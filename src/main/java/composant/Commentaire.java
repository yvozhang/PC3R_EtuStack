package composant;


public class Commentaire {
	private static final long serialVersionUID = 1L; 
	private int idC,idU,idP;
	private String contenu, date,username;
	
	
	public Commentaire() {
		super();
	}

	public Commentaire(int idU, int idP, String contenu) {
		super();
		this.idU = idU;
		this.idP = idP;
		this.contenu = contenu;
	}
	
	public int getIdC() {
		return idC;
	}
	public void setIdC(int idC) {
		this.idC = idC;
	}
	public int getIdU() {
		return idU;
	}
	public void setIdU(int idU) {
		this.idU = idU;
	}
	public int getIdP() {
		return idP;
	}
	public void setIdP(int idP) {
		this.idP = idP;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	
}
