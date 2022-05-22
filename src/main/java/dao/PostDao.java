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

import composant.Post;

public class PostDao implements Dao<Post>{

	//get post by id
	@Override
	public Post get(long id) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Post p  = null;
		
		try {
			String sql = "select * from posts where idP="+id;
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				p = new Post();
				p.setIdP(rs.getInt("idP"));
				p.setIdU(rs.getInt("idU"));
				p.setTitre(rs.getString("titre"));
				p.setContenu(rs.getString("contenu"));
				p.setDate(rs.getString("date"));
			}
			//rs.close();
			//stmt.close();
			return p;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return null; 
		
	}
	
	//get post by title
	public Post get(String titre) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		Post p  = null;
		
		try {
			String sql = "SELECT * FROM posts p, users u where titre = '"+titre+"' and p.idU=u.idU";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				p = new Post();
				p.setIdP(rs.getInt("idP"));
				p.setIdU(rs.getInt("idU"));
				p.setTitre(rs.getString("titre"));
				p.setContenu(rs.getString("contenu"));
				p.setDate(rs.getString("date"));
				p.setUsername(rs.getString("pseudo"));
			}
			//rs.close();
			//stmt.close();
			return p;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return null; 
		
	}
	
	//get post'id by title
	public int getIdByPostTitre(String titrePost) {
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		
		int idG = 0;
		
		try {
			String sql = "select idP from posts where titre='" + titrePost+"'";
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				idG = rs.getInt("idP");
			}
			return idG;
		}catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			//DbConnection.close();
		}
		return idG;
		
	}
	
	//get the posts which belong to the same category
	public List<Post> getSelectedByCategory(String category) {
		System.out.println("in getSelected() begin");
		String sql = "SELECT * FROM posts where category = '"+category+"'";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;

		Post p  = null;
		List<Post> posts = new ArrayList<Post>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				p = new Post();
				p.setIdP(rs.getInt("idP"));
				System.out.println("check idU:"+rs.getInt("idU"));
				p.setIdU(rs.getInt("idU"));
				System.out.println("check titre:"+rs.getString("titre"));
				p.setTitre(rs.getString("titre"));
				p.setContenu(rs.getString("contenu"));
				p.setDate(rs.getString("date"));
				p.setIdG(rs.getInt("idG"));
				p.setCategory(rs.getString("category"));
				posts.add(p);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			//e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return posts;
	}
	
	//get all the posts of the group
	public List<Post> getSelectedByGroup(String group) {
		System.out.println("in getSelectedByGroup() begin");
		String sql = "SELECT * FROM posts p, groupes g where nomG = '"+group+"' and p.idG=g.idG";				
		Connection connection = DbConnection.getInstance();
		Statement stmt;

		Post p  = null;
		List<Post> posts = new ArrayList<Post>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				p = new Post();
				p.setIdP(rs.getInt("idP"));
				System.out.println("check idU:"+rs.getInt("idU"));
				p.setIdU(rs.getInt("idU"));
				System.out.println("check titre:"+rs.getString("titre"));
				p.setTitre(rs.getString("titre"));
				p.setContenu(rs.getString("contenu"));
				p.setDate(rs.getString("date"));
				p.setCategory(rs.getString("category"));
				posts.add(p);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			//e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return posts;
	}

	//get all posts
	@Override
	public List<Post> getAll() {
		System.out.println("in getAll() begin");
		String sql = "SELECT * FROM posts";		
		Connection connection = DbConnection.getInstance();
		Statement stmt;
		System.out.println("in getAll() begin");

		Post p  = null;
		List<Post> posts = new ArrayList<Post>();
		
		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				p = new Post();
				p.setIdP(rs.getInt("idP"));
				System.out.println("check idU:"+rs.getInt("idU"));
				p.setIdU(rs.getInt("idU"));
				System.out.println("check titre:"+rs.getString("titre"));
				p.setTitre(rs.getString("titre"));
				p.setContenu(rs.getString("contenu"));
				p.setDate(rs.getString("date"));
				p.setIdG(rs.getInt("idG"));
				p.setCategory(rs.getString("category"));
				posts.add(p);
			}
			//rs.close();
			//stmt.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			//DbConnection.close();
		} 
		return posts;
	}

	//add new post to the database
	@Override
	public void save(Post p) {
		Date d=new Date();
		SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
		p.setDate(dateFormat.format(d));
		System.out.println("post date="+ p.getDate());
		String sql = "insert into posts(`titre`,`contenu`,`date`,`idU`,`idG`,`category`) values('"
					+p.getTitre()+"','"+p.getContenu()+"','"+p.getDate()+"',"+p.getIdU()+","+p.getIdG()+",'"+p.getCategory()+"')";
		
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
	public void update(Post p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Post p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean exist(Post t) {
		// TODO Auto-generated method stub
		return false;
	}


	

}
