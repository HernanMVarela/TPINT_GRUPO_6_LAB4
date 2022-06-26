package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EspecialidadDao;
import dao.HorarioDao;
import dao.MedicoDao;
import daoImpl.EspecialidadDaoImpl;
import daoImpl.HorarioDaoImpl;
import daoImpl.MedicoDaoImpl;
import entidad.Especialidad;
import entidad.Medico;


@WebServlet("/servletMedicos")
public class servletMedicos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public servletMedicos() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("listaMedicos", listarMedicos());
		request.setAttribute("listaDeEsp", listarEspecialidades());
		
		HorarioDao dao = new HorarioDaoImpl();
		dao.Listar(1);
		
		RequestDispatcher rd = request.getRequestDispatcher("/Medicos.jsp");   
        rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
	
	private List<Medico> listarMedicos() {
		List<Medico> listaDeMedicos = new ArrayList<Medico>();
		MedicoDao meDao = new MedicoDaoImpl();
		listaDeMedicos = meDao.ListarTodo();
		return listaDeMedicos;
	}

	private List<Especialidad> listarEspecialidades() {
		List<Especialidad> listaDeEsp = new ArrayList<Especialidad>();
		EspecialidadDao espDao = new EspecialidadDaoImpl();
		listaDeEsp = espDao.ListarTodo();
		return listaDeEsp;
	}
	
}
