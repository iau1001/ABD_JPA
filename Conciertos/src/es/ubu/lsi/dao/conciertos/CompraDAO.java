package es.ubu.lsi.dao.conciertos;

import java.util.List;

import javax.persistence.EntityManager;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Cliente;
import es.ubu.lsi.model.conciertos.Compra;
import es.ubu.lsi.model.conciertos.Concierto;


/**
 * CompraDAO
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 *
 * @param <E> entity type
 * @param <K> key type
 */
public class CompraDAO<E,K> extends JpaDAO<Compra,Integer> {

	/**
	 * Constructor.
	 * 
	 * @param em entity manager.
	 */
	public CompraDAO(EntityManager em) {
		super(em);
	}

	/**
	 * Busca todas las entidades.
	 * 
	 * @return todas las entidades.
	 */
	@Override
	public List<Compra> findAll() {
		return getEntityManager().createNamedQuery("Compra.findAll",
				Compra.class)
				.getResultList();
	}
	
	/**
	 * Retorna el siguiente id.
	 * 
	 * @return siguiente id.
	 */
	public int nextId() {
		return getEntityManager().createQuery("SELECT MAX(c.idcompra) "+
											"FROM Compra c ",Integer.class)
								.getSingleResult()+1;
	}
	/**
	 * Modifica valores de compra.
	 * 
	 * @param compra entidad Compra.
	 * @param id id de compra.
	 * @param tickets tickets de compra.
	 * @param cliente cliente de compra.
	 * @param concierto concierto de compra. 
	 */
	public void insert(Compra compra,int id, int tickets, Cliente cliente, Concierto concierto) {
		compra.setIdcompra(id);
		compra.setNTickets(tickets);
		compra.setCliente(cliente);
		compra.setConcierto(concierto);
	}

}
