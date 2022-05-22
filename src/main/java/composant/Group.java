package composant;

public class Group {
	private static final long serialVersionUID = 1L; 
	private int idG,id_owner;
	private String nom,category;

	public Group() {
		
	}
	
	public Group(String nom,String category,int id_owner) {
		this.nom = nom;
		this.category = category;
		this.id_owner = id_owner;
	}

	public int getIdG() {
		return idG;
	}

	public void setIdG(int idG) {
		this.idG = idG;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getId_owner() {
		return id_owner;
	}

	public void setId_owner(int id_owner) {
		this.id_owner = id_owner;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
