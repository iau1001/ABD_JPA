package es.ubu.lsi.dao.conciertos;

import java.util.List;

import javax.persistence.EntityManager;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Cliente;

/**
 * ClienteDAO
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 *
 * @param <E> entity type
 * @param <K> key type
 */
public class ClienteDAO<E,K> extends JpaDAO<Cliente,String> {

	/**
	 * Constructor.
	 * 
	 * @param em entity manager.
	 */
	public ClienteDAO(EntityManager em) {
		super(em);
	}
	
	/**
	 * Busca todas las entidades.
	 * 
	 * @return todas las entidades.
	 */
	@Override
	public List<Cliente> findAll() {
		return getEntityManager().createNamedQuery("Cliente.findAll",
				Cliente.class)
				.getResultList();
	}
}
