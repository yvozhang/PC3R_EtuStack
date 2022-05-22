package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import composant.Commentaire;
import composant.User;

public class CommentaireDao implements Dao<Commentaire>{

	//get the comment by id
	@Override
	public Commentaire get(long id) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Commentaire c  = null;
		
		try {
			String sql = "select * from commentaire where idC="+id;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				c = new Commentaire();
				c.setIdU(rs.getInt("idU"));
				c.setIdP(rs.getInt("idP"));
				c.setContenu(rs.getString("contenu"));
				c.setDate(rs.getString("date"));
			}
			//rs.close();
			//stmt.close();
			return c;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return null; 
		
	}
	
	//get all comments by post's title
	public List<Commentaire> getAllCommentaire(String titre) {
		String sql = "SELECT c.contenu, pseudo,c.date FROM posts p, users u, commentaire c where titre = '"
				+ titre +"' and c.idU=u.idU and p.idP=c.idP";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Commentaire c  = null;
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new Commentaire();
				c.setContenu(rs.getString("contenu"));
				c.setDate(rs.getString("date"));
				c.setUsername(rs.getString("pseudo"));
				commentaires.add(c);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return commentaires;
	}

	//get all comments
	@Override
	public List<Commentaire> getAll() {
		String sql = "SELECT `idU`,`pseudo`,`firstname`,`lastname`,`email`,`status` FROM commentaire WHERE idC <>"+ "null";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Commentaire c  = null;
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				c = new Commentaire();
				c.setIdC(rs.getInt("idC"));
				c.setIdU(rs.getInt("idU"));
				c.setIdP(rs.getInt("idP"));
				c.setContenu(rs.getString("contenu"));
				c.setDate(rs.getString("date"));
				commentaires.add(c);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return commentaires;
	}

	//add new comments to the database
	@Override
	public void save(Commentaire t) {
		Date d=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
		t.setDate(dateFormat.format(d));
		System.out.println("commentaire date="+ t.getDate());
		
		String sql = "INSERT INTO commentaire (`contenu`,`date`,`idU`,`idP`)"
				+ " VALUES"
				+ " ('"+t.getContenu()+"',"
				+ "'"+t.getDate()+"',"
				+ "'"+t.getIdU()+"',"
				+ "'"+t.getIdP()+"')";
		
		Connection connection = DbConnection.getInstance();
		PreparedStatement ps = null;		
		int ligne = 0;
		
		try {
			 ps = connection.prepareStatement(sql);
			 ligne =  ps.executeUpdate();
			 //ps.close();
			 
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
	}

	@Override
	public void update(Commentaire t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Commentaire t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exist(Commentaire t) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
