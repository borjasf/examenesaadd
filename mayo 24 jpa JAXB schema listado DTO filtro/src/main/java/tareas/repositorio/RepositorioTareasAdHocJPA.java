package tareas.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tareas.modelo.Estado;
import tareas.modelo.Tarea;
import utils.EntityManagerHelper;

public class RepositorioTareasAdHocJPA extends RepositorioTareasJPA implements RepositorioTareasAdHoc {

	@Override
	public List<Tarea> buscarTareasEstado(Estado estado) {
		if(estado==null) {
			throw new IllegalArgumentException("El estado no puede ser nulo.");
		}
		EntityManager em = EntityManagerHelper.getEntityManager();
		try {
			String jpql = "SELECT t FROM Tarea t JOIN t.estados e WHERE e.estado = :estado AND e.fechaFin IS NULL";
			TypedQuery<Tarea> query = em.createQuery(jpql, Tarea.class);
			query.setParameter("estado", estado);
			return query.getResultList();
		}finally{
			EntityManagerHelper.closeEntityManager();
		}
	}
	
}
