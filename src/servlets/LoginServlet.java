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

		String username = "";
		String password = "";
		String redirect = "/Home.jsp";
		
		Usuario log = new Usuario();
		if(request.getParameter("btnLogin")!=null) {
			if(validar_campos(request, response)) {
				username = request.getParameter("txfUsername").toString();
				password = request.getParameter("txfPassword").toString();
				log=verificar_usuario(username, password);
				request.getSession().setAttribute("login", log);
			}else{
				request.getSession().setAttribute("login", null);
			}
		}
		
		if(request.getParameter("btnCerrar")!=null) {
			request.getSession().setAttribute("login", null);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(redirect);   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

	private boolean validar_campos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean flag = true;
		if(request.getParameter("txfUsername")==null || request.getParameter("txfUsername").toString().isEmpty()) {
			flag=false;
		}
		
		if(request.getParameter("txfPassword")==null || request.getParameter("txfPassword").toString().isEmpty()) {
			flag=false;
		}
		
		return flag;
	}
	
	private Usuario verificar_usuario(String username, String password) {
		UsuarioNegocio userneg = new UsuarioNegocioImpl();
		Usuario log = new Usuario();
		System.out.println("DATOS RECIBIDOS: " + username + " " + password);
		System.out.println("USER ID ENCONTRADO: " +userneg.existeUsuario(username));
		
		log = userneg.ObtenerObjeto(userneg.existeUsuario(username));
		
		if(log == null || log.getIdUsuario()<=0) {
			return null;
		}
		System.out.println("USUARIO CARGADO: " + log.getIdUsuario() +" "+ log.getUser() );
		if(!log.getUser().equals(username)) {
			return null;
		}
		System.out.println("SEGUNDA VALIDACION: " + log.getUser() + " " + username);
		if(!log.getPassword().equals(password)) {
			return null;
		}		
		System.out.println("VALIDACION PASSWORD: " + log.getPassword() +" "+ password );
		
		return log;
	}
	
}
