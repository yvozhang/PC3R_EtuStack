package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import composant.Group;

public class GroupDao implements Dao<Group>{

	
	@Override
	public Group get(long id) {
		return null; 	
	}
	
	//get category's name by groupname
	public String getCategoryByGroupname(String groupname) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		String category = null;
		
		try {
			String sql = "select category from `groupes` where nomG='"+groupname+"'";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				category = rs.getString("category");
			}
			return category;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return category;
	}
	
	//get group's id by groupname
	public int getIdByGroupname(String groupname) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		int idG = 0;
		
		try {
			String sql = "select idG from `groupes` where nomG='" + groupname+"'";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				idG = rs.getInt("idG");
			}
			return idG;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return idG;
		
	}

	//get all groups by category
	public List<Group> getGroupeByCategory(String category) {
		String sql = "SELECT * FROM groupes WHERE category = '"+category+ "'";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Group u  = null;
		List<Group> groups = new ArrayList<Group>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				u = new Group();
				System.out.println("nomG:"+rs.getString("nomG"));
				u.setNom(rs.getString("nomG"));
				System.out.println("category:"+rs.getString("category"));
				u.setCategory(rs.getString("category"));
				groups.add(u);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return groups;
	}
	
	//get all groups from database
	@Override
	public List<Group> getAll() {
		String sql = "SELECT * FROM groupes";
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Group u  = null;
		List<Group> groups = new ArrayList<Group>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				u = new Group();
				u.setNom(rs.getString("nomG"));
				u.setCategory(rs.getString("category"));
				groups.add(u);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return groups;
	}

	//add new group to database
	@Override
	public void save(Group t) {
		String sql = "insert into groupes(`nomG`,`category`,`id_owner`) values('"
					+t.getNom()+"','"+t.getCategory()+"','"+t.getId_owner()+"')";
		
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
	public void update(Group t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Group t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exist(Group t) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
