package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import composant.User;

public class UserDao implements Dao<User>{

	
	//get user by id
	@Override
	public User get(long id) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		User u  = null;
		
		try {
			String sql = "select * from `users` where idU="+id;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				u = new User();
				u.setIdU(rs.getInt("idU"));
				u.setPseudo(rs.getString("pseudo"));
				u.setPassword(rs.getString("password"));
			}
			//rs.close();
			//stmt.close();
			return u;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return null; 
		
	}
	
	//get user's id by username
	public int getIdByUsername(String username) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		int idU = 0;
		
		try {
			String sql = "select idU from users where pseudo = '" +username+"'";
			System.out.println(sql);
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				idU = rs.getInt("idU");
				System.out.println("in UserDao, i got idU="+rs.getInt("idU"));
			}
			return idU;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return idU;
	}
	
	//get user by email
	public User get(String email) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		User u  = null;
		
		try {
			String sql = "select * from `users` where email= '"+ email+"'";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				u = new User();
				u.setIdU(rs.getInt("idU"));
				u.setPseudo(rs.getString("pseudo"));
				u.setPassword(rs.getString("password"));
			}
			//rs.close();
			//stmt.close();
			return u;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return null; 
		
	}

	//get all the users 
	@Override
	public List<User> getAll() {
		String sql = "SELECT `idU`,`pseudo`,`firstname`,`lastname`,`email` FROM `users` WHERE idU <>"+ "null";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		User u  = null;
		List<User> users = new ArrayList<User>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				u = new User();
				u.setIdU(rs.getInt("idU"));
				u.setPseudo(rs.getString("pseudo"));
				u.setEmail(rs.getString("email"));
				users.add(u);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return users;
	}

	//add new user to database
	@Override
	public void save(User t) {
		String sql = "INSERT INTO users (`pseudo`, `email`, `password`)"
				+ " VALUES"
				+ " ('"+t.getPseudo()+"',"
				+ "'"+t.getEmail()+"',"
				+ "'"+t.getPassword()+"')";
		System.out.println("in fonction save, before connection");
		Connection connection = DbConnection.getInstance();
		System.out.println("in fonction save, after connection");

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
	public void update(User t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		
	}
	
	//test if user exist or not
	public boolean exist(User t) {
		String sql = "select * from users where email= '"+ t.getEmail()+"'";
		System.out.println("in exist(): email from html form "+ t.getEmail());
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		User u = new User();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				u.setIdU(rs.getInt("idU"));
				u.setPseudo(rs.getString("pseudo"));
				u.setPassword(rs.getString("password"));
				u.setEmail(rs.getString("email"));
			}
			System.out.println("in exist(): "+ u.getEmail()+" and "+u.getPassword());

			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		
		return u.getPassword().equals(t.getPassword());
		
	}
	

}
