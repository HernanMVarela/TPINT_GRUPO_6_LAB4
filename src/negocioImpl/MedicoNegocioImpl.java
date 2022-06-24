package negocioImpl;

import dao.MedicoDao;
import daoImpl.MedicoDaoImpl;
import entidad.Medico;
import negocio.MedicoNegocio;

public class MedicoNegocioImpl implements MedicoNegocio{

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

}
