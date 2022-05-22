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

import composant.Commentaire;
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
@WebServlet("/ServletCommentaire")
public class ServletCommentaire extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson; 
	private Dao commentaireDao;
	private Dao<Post> postDao;
	private Dao<User> userDao;
	
	
	private List<Commentaire> selectedCommentaires;
	private Commentaire newCommentaire;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCommentaire() {
        super();
        this.gson = new GsonBuilder().serializeNulls().create();
    }
    
    public void init(ServletConfig config)throws ServletException{
    	postDao = new PostDao();
    	userDao = new UserDao();
    	commentaireDao = new CommentaireDao();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("begin ServletCommentaire doPost()");
		String titre = request.getParameter("titre");
		String titrePost = request.getParameter("titreP");
		System.out.println("In servletCommentaire, i got titrePost:"+titrePost);
		String contenueCommentaire = request.getParameter("contenu");
		String user = request.getParameter("username");


		selectedCommentaires = ((CommentaireDao) commentaireDao).getAllCommentaire(titre);
		if(titre != null ) {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			//System.out.println("Authentification par login: "+ email + " mdp: " + mdp);
			if(selectedCommentaires!=null) {
				response.getWriter().write(gson.toJson(selectedCommentaires));
				System.out.println(gson.toJson(selectedCommentaires));
			}else {
				response.getWriter().write(gson.toJson(null));
			}
			
		}else {
			int idU = ((UserDao)userDao).getIdByUsername(user);				
			System.out.println("idU="+idU);
			
			int idP = ((PostDao)postDao).getIdByPostTitre(titrePost);
			System.out.println("idP="+idP);
			
			newCommentaire = new Commentaire(idU,idP,contenueCommentaire); 
			commentaireDao.save(newCommentaire);
			
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(gson.toJson("Réussi postuler un commentaire!!!"));
			System.out.println("Create Commentaire Réussi!!!!!!!!!!!!!!!");
			
		}
	}

}
