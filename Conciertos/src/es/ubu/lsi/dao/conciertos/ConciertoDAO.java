package es.ubu.lsi.dao.conciertos;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TemporalType;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Concierto;

/**
 * ConciertoDAO
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 *
 * @param <E> entity type
 * @param <K> key type
 */
public class ConciertoDAO<E,K> extends JpaDAO<Concierto,Integer> {

	/**
	 * Constructor.
	 * 
	 * @param em entity manager.
	 */
	public ConciertoDAO(EntityManager em) {
		super(em);
	}

	/**
	 * Busca todas las entidades.
	 * 
	 * @return todas las entidades.
	 */
	@Override
	public List<Concierto> findAll() {
		return getEntityManager().createNamedQuery("Concierto.findAll",
				Concierto.class)
				.getResultList();
	}
	
	
	/**
	 * Busca entidades por grupo y fecha.
	 * 
	 * @param fecha la fecha.
	 * @param grupo id de grupo.
	 * @return las entidades con ese id de grupo y esa fecha.
	 */
	public List<Concierto> findByDateGroup(Date fecha, int grupo) {
		return getEntityManager().createQuery("SELECT c "+
											"FROM Concierto c "+
											"WHERE c.fecha = ?1 AND "+
											"c.grupo.idgrupo = ?2",Concierto.class)
								.setParameter(1, fecha,TemporalType.TIMESTAMP)
								.setParameter(2, grupo)
								.getResultList();
	}
	
	/**
	 * Modifica el numero de tickets.
	 * 
	 * @param concierto entidad Concierto.
	 * @param tickets numero de tickets.
	 */
	public void actualizaTickets(Concierto concierto, int tickets) {
		concierto.setTickets(tickets);
	}
	
	
	/**
	 * Busca entidades por grupo.
	 * 
	 * @param grupo id de grupo.
	 * @return las entidades con ese id de grupo.
	 */
	public List<Concierto> findByGroup(int grupo) {
		return getEntityManager().createQuery("SELECT c "+
											"FROM Concierto c "+
											"WHERE c.grupo.idgrupo = ?1",Concierto.class)
								.setParameter(1, grupo)
								.getResultList();
	}

}
