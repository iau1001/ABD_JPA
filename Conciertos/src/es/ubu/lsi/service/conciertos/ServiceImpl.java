package es.ubu.lsi.service.conciertos;

import java.util.Date;
import java.util.List;


import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import es.ubu.lsi.dao.conciertos.ClienteDAO;
import es.ubu.lsi.dao.conciertos.CompraDAO;
import es.ubu.lsi.dao.conciertos.ConciertoDAO;
import es.ubu.lsi.dao.conciertos.GrupoDAO;
import es.ubu.lsi.model.conciertos.Cliente;
import es.ubu.lsi.model.conciertos.Compra;
import es.ubu.lsi.model.conciertos.Concierto;
import es.ubu.lsi.model.conciertos.Grupo;
import es.ubu.lsi.service.PersistenceException;
import es.ubu.lsi.service.PersistenceService;

/**
 * ServiceImpl. Implementacion de servicios.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 *
 */
public class ServiceImpl extends PersistenceService implements Service{

	private static final Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

	/**
	 * Alta de una nueva compra sobre un concierto y un cliente.
	 * 
	 * @param fecha fecha
	 * @param nif nif
	 * @param grupo identificador del grupo
	 * @throws PersistenceException si se produce un error
	 */
	@Override
	public void comprar(Date fecha, String nif, int grupo, int tickets) throws PersistenceException {
		EntityManager em = this.createSession();
		//Se intenta realizar la compra
		try {
			beginTransaction(em);
			//ClienteDAO
			ClienteDAO<Cliente,String> clienteDAO = new ClienteDAO<Cliente,String>(em);
			//Se busca el cliente por el nif
			Cliente cliente = clienteDAO.findById(nif);
			//Si el cliente no existe se lanza error
			if (cliente==null) {
				throw new IncidentException(IncidentError.NOT_EXIST_CLIENT);
			}
			//GrupoDAO
			GrupoDAO<Grupo,Integer> grupoDAO = new GrupoDAO<Grupo,Integer>(em);
			//Se busca el grupo por su id
			Grupo grupoObj = grupoDAO.findById(grupo);
			//Si el grupo no existe se lanza error
			if(grupoObj==null) {
				throw new IncidentException(IncidentError.NOT_EXIST_MUSIC_GROUP);
			}
			
			//ConciertoDAO
			ConciertoDAO<Concierto,Integer> conciertoDAO = new ConciertoDAO<Concierto,Integer>(em);
			//Se buscan los conciertos con la fecha y grupo pasados por parametro
			List<Concierto> conciertos = conciertoDAO.findByDateGroup(fecha,grupo);
			//Si no existe ningun concierto se lanza error
			if(conciertos.size()==0) {
				throw new IncidentException(IncidentError.NOT_EXIST_CONCERT);
			}
			
			//Si no, se obtiene el concierto
			Concierto concierto = conciertos.get(0);
			//Si no hay tickets suficientes se lanza error
			if (tickets>concierto.getTickets()) {
				throw new IncidentException(IncidentError.NOT_AVAILABLE_TICKETS);
			}
			
			//Si hay tickets, actualiza los tickets disponibles del concierto.
			conciertoDAO.actualizaTickets(concierto, concierto.getTickets()-tickets);
			
			//CompraDAO
			CompraDAO<Compra,Integer> compraDAO = new CompraDAO<Compra,Integer>(em);
			//Se obtiene el siguiente id
			int siguienteId = compraDAO.nextId();
			//Nueva compra
			Compra compra = new Compra();
			//Se inserta la compra
			compraDAO.insert(compra, siguienteId, tickets, cliente, concierto);
			//Persist
			em.persist(compra);
			//Commit
			commitTransaction(em);
			
		//Captura el error
		}catch (Exception e){
			logger.error("Exception");
			//Rollback si transaccion activa
			if (em.getTransaction().isActive()) {
				logger.info("Commit rollback");
				rollbackTransaction(em);
			}
			logger.error(e.getLocalizedMessage());
			//Relanzar errores
			if (e instanceof IncidentException) {
				throw (IncidentException) e;
			}else {
				throw e;
			}
			
		//Cierre de recursos	
		}finally {
			em.close();
		}
		
	}
}