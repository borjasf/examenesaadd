package tablones.repositorio;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tablones.modelo.Anuncio;
import tablones.modelo.Tablon;
import utils.EntityManagerHelper;

public class RepositorioTablonesAdHocJPA extends RepositorioTablonesJPA implements RepositorioTablonesAdHoc {

	
	@Override
	public List<Anuncio> buscarMensajeTablonPalabraClave(String idTablon, String texto) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		try {
			String jpql = "SELECT a FROM Anuncio a WHERE a.tablon.id = :idTablon " +
						  "AND (a.titulo LIKE :texto OR a.resumen LIKE :texto)";
			
			TypedQuery<Anuncio> query = em.createQuery(jpql, Anuncio.class);
			query.setParameter("idTablon", idTablon);
			query.setParameter("texto", "%" + texto + "%");
			
			return query.getResultList();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	@Override
	public List<Anuncio> todosMensajesTablonYFecha(String idTablon, LocalDateTime fecha) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		try {
			String jpql = "SELECT a FROM Anuncio a WHERE a.tablon.id = :idTablon " +
						  "AND a.fechaPublicacion <= :fecha";
			
			TypedQuery<Anuncio> query = em.createQuery(jpql, Anuncio.class);
			query.setParameter("idTablon", idTablon);
			query.setParameter("fecha", fecha.toLocalDate());
			
			return query.getResultList();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	public List<Anuncio> buscarMensajesTablonPalabraClaveYFecha(String idTablon,String texto,LocalDateTime fecha){
		
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		try {
			String jpql = "SELECT a FROM Anuncio a WHERE a.tablon.id = :idTablon " +
					  "AND (a.titulo LIKE :texto OR a.resumen LIKE :texto)" + 
					  "AND a.fechaPublicacion <= :fecha";
			
			TypedQuery<Anuncio> query = em.createQuery(jpql, Anuncio.class);
			query.setParameter("idTablon", idTablon);
			query.setParameter("fecha", fecha.toLocalDate());
			query.setParameter("texto", "%" + texto + "%");
			
			return query.getResultList();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
		
	
	}
	
	/**
	 * Recuperar mensajes con filtros opcionales de texto y fecha.
	 * Si texto es null, no se aplica filtro por texto.
	 * Si fecha es null, no se aplica filtro por fecha.
	 * Si ambos son null, se retornan todos los anuncios del tablón.
	 */
	@Override
	public List<Anuncio> recuperarMensajes(String idTablon, String texto, LocalDateTime fecha) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		try {
			StringBuilder jpql = new StringBuilder("SELECT a FROM Anuncio a WHERE a.tablon.id = :idTablon");
			
			// Añadir filtro por texto si no es nulo
			if (texto != null) {
				jpql.append(" AND (a.titulo LIKE :texto OR a.resumen LIKE :texto)");
			}
			
			// Añadir filtro por fecha si no es nula (a partir de la fecha indicada)
			if (fecha != null) {
				jpql.append(" AND a.fechaPublicacion >= :fecha");
			}
			
			TypedQuery<Anuncio> query = em.createQuery(jpql.toString(), Anuncio.class);
			query.setParameter("idTablon", idTablon);
			
			if (texto != null) {
				query.setParameter("texto", "%" + texto + "%");
			}
			
			if (fecha != null) {
				query.setParameter("fecha", fecha);
			}
			
			return query.getResultList();
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
	
	/**
	 * Busca un tablón por su nombre exacto.
	 */
	@Override
	public Tablon buscarPorNombre(String nombre) {
		EntityManager em = EntityManagerHelper.getEntityManager();
		
		try {
			String jpql = "SELECT t FROM Tablon t WHERE t.nombre = :nombre";
			
			TypedQuery<Tablon> query = em.createQuery(jpql, Tablon.class);
			query.setParameter("nombre", nombre);
			
			List<Tablon> resultados = query.getResultList();
			
			// Retornar el primer resultado o null si no hay resultados
			return resultados.isEmpty() ? null : resultados.get(0);
		} finally {
			EntityManagerHelper.closeEntityManager();
		}
	}
}
