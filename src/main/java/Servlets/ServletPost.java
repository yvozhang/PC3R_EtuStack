package Servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import composant.Group;
import composant.Post;
import composant.User;
import dao.Dao;
import dao.GroupDao;
import dao.PostDao;
import dao.UserDao;

/**
 * Servlet implementation class ServletPost
 */
@WebServlet("/ServletPost")
public class ServletPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson; 
	private Dao<Post> postDao;
	private Dao<User> userDao;
	private Dao<Group> groupDao;
	
	private Post newPost;
	private Post selectedPost;
	private List<Post> selectedPosts;
	private List<Post> allPosts;


    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPost() {
        super();
        this.gson = new GsonBuilder().serializeNulls().create();
    }
    
    public void init(ServletConfig config)throws ServletException{
    	postDao = new PostDao();
    	userDao = new UserDao();
    	groupDao = new GroupDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allPosts = postDao.getAll();
		//System.out.println("titre:"+allPosts.get(0).getTitre());

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//System.out.println("Authentification par login: "+ email + " mdp: " + mdp);
		response.getWriter().write(gson.toJson(allPosts));
		System.out.println(gson.toJson(allPosts));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("begin ServletPost doPost()");
		String titre = request.getParameter("titre");
		String category = request.getParameter("category");
		String group = request.getParameter("group");
		String contenu = request.getParameter("contenue");
		String user = request.getParameter("username");
		
		System.out.println("In ServletPost, i got titre of post: "+titre+", groupname:"+group+",contenu: "+contenu
				+", username: "+user);
		
		if(titre != null ) {
			if(contenu != null) {
				int idU = ((UserDao)userDao).getIdByUsername(user);
				System.out.println("idU="+idU);

				int idG = ((GroupDao)groupDao).getIdByGroupname(group);
				System.out.println("idG="+idG);

				String categoryG = ((GroupDao)groupDao).getCategoryByGroupname(group);
				System.out.println("categoryG="+categoryG);

				newPost = new Post(idU, idG, titre, contenu,categoryG, user);
				postDao.save(newPost);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gson.toJson("Réussi create a post!!!"));
				System.out.println("Create Post Réussi!!!!!!!!!!!!!!!");
				
				
			}else {
				selectedPost = ((PostDao) postDao).get(titre);
				System.out.println("get post:"+selectedPost.getContenu());
				System.out.println("titre got from:"+titre);
				
				System.out.println("begin ServletPost doPost()");
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gson.toJson(selectedPost));
				System.out.println("in ServletPost:!!!!!!"+gson.toJson(selectedPost));
			}
			
		}else{
			if(category != null) {
				selectedPosts = ((PostDao) postDao).getSelectedByCategory(category);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gson.toJson(selectedPosts));
				System.out.println(gson.toJson(selectedPosts));
			}else {
				selectedPosts = ((PostDao) postDao).getSelectedByGroup(group);
				response.setContentType("application/json");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(gson.toJson(selectedPosts));
				System.out.println(gson.toJson(selectedPosts));
			}
			
			
		}
	}

}
