package daoImpl;

import java.util.ArrayList;
import java.util.Iterator;

import dao.LocalidadDao;
import dao.PaisDao;
import dao.ProvinciaDao;
import dao.SexoDao;
import entidad.Localidad;
import entidad.Pais;
import entidad.Provincia;
import entidad.Sexo;

public class PruebaConexionSQL {

	public static void main(String[] args) {
		Localidad localidad = new Localidad();
		LocalidadDao daoLocalidad = new LocalidadDaoImpl();
		Sexo sexo = new Sexo();
		SexoDao daoSexo = new SexoDaoImpl();
		ProvinciaDao daoProvincia = new ProvinciaDaoImpl();
		Provincia provincia = new Provincia();
		provincia = daoProvincia.ObtenerObjeto(1);
		localidad.setNombre("Prueba");
		sexo.setNombre("Prueba");
		
		localidad.setProvincia(provincia);
		
		//AGREGAR
		if(daoSexo.Agregar(sexo)) {
			System.out.println("El sexo ha sigo agregado. Sexo: "+sexo.getNombre());
	} else {
			System.out.println("NO PUDO SER AGREGADO");
			}
		
		if(daoLocalidad.Agregar(localidad)) {
			System.out.println("La localidad ha sigo agregada. Localidad: "+localidad.getNombre());
	} else {
			System.out.println("NO PUDO SER AGREGADO");
			}
		
		//MODIFICAR
				
		//LISTAR
		ArrayList<Sexo> sexos = daoSexo.ListarTodo();
		Iterator<Sexo> it = sexos.iterator();
		
		while(it.hasNext())
		{
			Sexo p = (Sexo) it.next();
			System.out.println(p.toString());
		}
		
		ArrayList<Localidad> localidades = daoLocalidad.ListarTodo();
		Iterator<Localidad> it2 = localidades.iterator();
		
		while(it2.hasNext())
		{
			Localidad p = (Localidad) it2.next();
			System.out.println(p.getNombre());
		}
		
		//OBTENER
		sexo = daoSexo.ObtenerObjeto(2);
		System.out.println("Pais con ID 2: "+sexo.getNombre());
		
		localidad = daoLocalidad.ObtenerObjeto(2);
		System.out.println("Pais con ID 2: "+localidad.getNombre());	
		
	}
}
