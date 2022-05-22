package composant;

import java.io.Serializable;


public class User implements Serializable{
	private static final long serialVersionUID = 1L; 
	private int idU;
	private String pseudo,email,password,status;

	public User() {
		
	}
	
	public User(String pseudo,String email,String password,String status) {
		this.pseudo = pseudo;
		this.email = email;
		this.password = password;
		this.status = status;
	}
	
	public int getIdU() {
		return idU;
	}

	public void setIdU(int idU) {
		this.idU = idU;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
