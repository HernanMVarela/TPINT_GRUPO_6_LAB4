package daoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import dao.PaisDao;
import entidad.Pais;

public class PruebaConexionSQL {

	public static void main(String[] args) {
		Pais pais = new Pais();
		pais.setIso("PRU");
		pais.setNombre("Prueba");
		PaisDao dao = new PaisDaoImpl();
		
		
		//AGREGAR
		if(dao.Agregar(pais)) {
			System.out.println("El pais ha sigo agregado. Pais: "+pais.getNombre());
	} else {
			System.out.println("NO PUDO SER AGREGADO");
			}
		
		//MODIFICAR
		pais.setNombre("Prueba Modificación");
		pais.setIdNacionalidad(15);
		if(dao.Modificar(pais)) {
			System.out.println("El pais ha sigo modificado. Pais: "+pais.getNombre());
	} else {
			System.out.println("NO PUDO SER MODIFICADO");
			}
		
		//LISTAR
		ArrayList<Pais> paises = dao.ListarTodo();
		System.out.println("Listado de todos los paises OK. ");
		
		Iterator<Pais> it = paises.iterator();
		
		while(it.hasNext())
		{
			Pais p = (Pais) it.next();
			System.out.println(p.toString());
		}
		
		//OBTENER
		pais = dao.ObtenerObjeto(2);
		System.out.println("Pais con ID 2: "+pais.getNombre());		
		
	}
}
