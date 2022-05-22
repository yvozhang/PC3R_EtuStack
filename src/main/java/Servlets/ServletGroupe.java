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
import dao.CommentaireDao;
import dao.Dao;
import dao.GroupDao;
import dao.PostDao;
import dao.UserDao;

/**
 * Servlet implementation class ServletCategory
 */
@WebServlet("/ServletGroupe")
public class ServletGroupe extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson; 
	private Dao groupeDao;
	private Dao<User> userDao;

	private Group newGroup;
	private List<Group> selectedGroupes;	
	private List<Group> allGroups;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletGroupe() {
        super();
        this.gson = new GsonBuilder().serializeNulls().create();
    }
    
    public void init(ServletConfig config)throws ServletException{
    	groupeDao = new GroupDao();
    	userDao = new UserDao();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		allGroups = groupeDao.getAll();
		
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		//System.out.println("Authentification par login: "+ email + " mdp: " + mdp);
		response.getWriter().write(gson.toJson(allGroups));
		System.out.println(gson.toJson(allGroups));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("begin ServletGroupe doPost()");
		String category = request.getParameter("category");
		String username = request.getParameter("username");
		String groupname = request.getParameter("groupname");
		System.out.println("ServletGroupe get category:"+category);

		selectedGroupes = ((GroupDao) groupeDao).getGroupeByCategory(category);
		System.out.println("get groups:"+selectedGroupes.get(0).getNom());
		if(groupname == null ) {
			System.out.println("begin ServletPost doPost()");
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//System.out.println("Authentification par login: "+ email + " mdp: " + mdp);
			response.getWriter().write(gson.toJson(selectedGroupes));
			System.out.println("in ServletGroups:!!!!!!"+gson.toJson(selectedGroupes));
		}else {
			int idU = ((UserDao)userDao).getIdByUsername(username);
			System.out.println("idU="+idU);
			
			newGroup = new Group(groupname,category,idU);
			groupeDao.save(newGroup);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson("Réussi create a group!!!"));
			System.out.println("Create Group Réussi!!!!!!!!!!!!!!!");
			
		}
	}

}
