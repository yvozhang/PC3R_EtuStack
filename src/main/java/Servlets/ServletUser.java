package Servlets;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import composant.User;
import dao.Dao;
import dao.UserDao;

/**
 * Servlet implementation class ServletUser
 */
@WebServlet("/ServletUser")
public class ServletUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Gson gson; 
	private Dao userDao;
	private User user;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUser() {
        super();
        this.gson = new GsonBuilder().serializeNulls().create();
    }
    
    public void init(ServletConfig config)throws ServletException{
    	userDao = new UserDao();
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
		
		
		String name = request.getParameter("name");   
		String email = request.getParameter("email"); 
		System.out.println("Servlet: email- "+ email);
		String mdp = request.getParameter("mdp");
		boolean isExist = false;
	  
		user = ((UserDao)userDao).get(email);
		String pseudo = user.getPseudo();
		
		if(name!=null) {
			user = new User(name, email, mdp, null);
			userDao.save(user);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			System.out.println("Inscription réussi : "+ name + " mdp: " + mdp+"email:"+email);
			response.getWriter().write(gson.toJson("Réussi"));
			
		}else {
			if(mdp.equals(user.getPassword())) {
				System.out.println("password correct");
				HttpSession session=request.getSession();
				session.setAttribute("user",pseudo);
				response.setContentType("application/json");
				System.out.println("Servlet- pseudo: "+ user.getPseudo());
				System.out.println("Servlet- json: "+ gson.toJson(user.getPseudo()));
				response.setCharacterEncoding("UTF-8");	
				response.getWriter().write(gson.toJson(user.getPseudo()));
				response.setHeader("Location", "acceuil.html");
			}else {
				
			}	
		}
		
		
		

		  /*
		  if(isExist) {
			  response.setContentType("application/json");
			  System.out.println("Authentification par login: "+ email + " mdp: " + mdp);
			  response.getWriter().write(gson.toJson(new LoginReponse("ok", email)));
		  }else {
			  response.setContentType("application/json");
			  System.out.println("user doesn't exist");
			  response.getWriter().write(gson.toJson(new LoginReponse("user doesn't exist", email)));
		  }
		  */
	}

}
