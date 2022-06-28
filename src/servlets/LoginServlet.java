package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDao;
import daoImpl.UsuarioDaoImpl;
import entidad.Usuario;
import negocio.UsuarioNegocio;
import negocioImpl.UsuarioNegocioImpl;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public LoginServlet() {
        super();

    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Usuario User = new Usuario();
		String Username;
		String Password;
		if(request.getParameter("btnLogin")!=null) {
			
			if(request.getParameter("txfUsername")!=null) {
				Username = request.getParameter("txfUsername").toString();
				User.setUser(request.getParameter("txfUsername").toString());
			}else {
				request.setAttribute("Loginok", false);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				User = null;
				rd.forward(request, response);				
			}
			if(request.getParameter("txfPassword")!=null) {
				User.setPassword(request.getParameter("txfPassword").toString());
			}else {
				request.setAttribute("Loginok", false);
				RequestDispatcher rd = request.getRequestDispatcher("/Login.jsp");
				User = null;
				rd.forward(request, response);
			}
			
			
			UsuarioNegocio userneg = new UsuarioNegocioImpl();
			
			User = userneg.ObtenerObjeto(userneg.existeUsuario(request.getParameter("txfUsername").toString()));
						
			if(User != null && User.getUser().equals(request.getParameter("txfUsername")) && User.getPassword().equals(request.getParameter("txfPassword"))) {
				System.out.println("USUARIO LOGUEADO: "+User.getIdUsuario() + " | " + User.getUser() + " | " + User.getPassword());
				request.setAttribute("Loginok", true);
				request.setAttribute("Usuario", User);
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}else {
				request.setAttribute("Loginok", false);
				User = null;
				RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
				rd.forward(request, response);
			}		
		}
		
		if(request.getParameter("btnCerrar")!=null) {
			request.setAttribute("Loginok", null);
			User = null;
			RequestDispatcher rd = request.getRequestDispatcher("Home.jsp");
			System.out.println("SESSION CERRADA");
			rd.forward(request, response);
		}
	}

}
