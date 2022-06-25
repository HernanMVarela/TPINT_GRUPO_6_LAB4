package negocioImpl;

import dao.MedicoDao;
import daoImpl.MedicoDaoImpl;
import entidad.Medico;
import negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio{

	@Override
	public boolean agregarMedico(Medico agregar) {
		MedicoDao medao = new MedicoDaoImpl();
		return medao.Agregar(agregar);
	}
	
	@Override
	public Medico buscar_usuario(int idUser) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.buscar_usuario(idUser);
	}

	@Override
	public boolean bajaMedico(Medico baja) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.bajaMedico(baja.getIdMedico());
	}

	@Override
	public boolean modificar_usuario(Medico modif_user) {
		MedicoDao meddao = new MedicoDaoImpl();
		return meddao.Modificar(modif_user);
	}

}
