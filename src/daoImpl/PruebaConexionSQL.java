package daoImpl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import dao.EspecialidadDao;
import dao.EstadoDao;
import dao.LocalidadDao;
import dao.PaisDao;
import dao.PersonaDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import dao.TipoDao;
import dao.UsuarioDao;
import entidad.Especialidad;
import entidad.Estado;
import entidad.Localidad;
import entidad.Pais;
import entidad.Persona;
import entidad.Provincia;
import entidad.Sexo;
import entidad.Tipo;
import entidad.Usuario;

public class PruebaConexionSQL {

	public static void main(String[] args) {
		Persona persona;
		PersonaDao personaDao = new PersonaDaoImpl();
		Especialidad especialidad;
		EspecialidadDao especialidadDao;
		Estado estado;
		EstadoDao estadoDao;
		Tipo tipo;
		TipoDao tipoDao;
		Usuario usuario;
		UsuarioDao usuarioDao;
		
		//AGREGAR
		
		//Persona(int dni, String nombre, String apellido, Pais nacionalidad, String direccion, Sexo sexo,
		//Localidad localidad, String email, String telefono, Date fecha_nacimiento)
		
		Pais pais = new Pais();
		PaisDao paisDao = new PaisDaoImpl();
		pais = paisDao.ObtenerObjeto(1);
		
		Sexo sexo = new Sexo();
		SexoDao sexoDao = new SexoDaoImpl();
		sexo = sexoDao.ObtenerObjeto(1);
		
		Localidad localidad = new Localidad();
		LocalidadDao localidadDao = new LocalidadDaoImpl();
		localidad = localidadDao.ObtenerObjeto(1);
		
		Date nacimiento = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		
		persona = new Persona(1111, "Prueba Nombre", "Prueba Apellido", pais, "132 rodo", sexo,
				localidad, "123@456.com", "123 321", nacimiento);
		
		if(personaDao.Agregar(persona)) {
			System.out.println("El persona ha sigo agregado. Persona: "+persona.getNombre());
	} else {
			System.out.println("NO PUDO SER AGREGADO");
			}
		
		
		
		//MODIFICAR
		persona = personaDao.ObtenerObjeto(71176348);
		persona.setNombre("Rosa");
		if(personaDao.Modificar(persona)) {
			System.out.println("El persona ha sigo modificado. Persona: "+persona.getNombre());
	} else {
			System.out.println("NO PUDO SER AGREGADO");
			}
		
		
		//LISTAR
		ArrayList<Persona> personas = personaDao.ListarTodo();
		Iterator<Persona> it = personas.iterator();
		
		while(it.hasNext())
		{
			Persona p = (Persona) it.next();
			System.out.println(p.getNombre());
		}
				
		//OBTENER
		persona = personaDao.ObtenerObjeto(71176348);
		System.out.println("Persona con DNI 71176348: "+persona.getNombre());
		
			
		
	}
}
