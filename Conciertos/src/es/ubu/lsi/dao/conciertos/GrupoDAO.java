package es.ubu.lsi.dao.conciertos;

import java.util.List;

import javax.persistence.EntityManager;

import es.ubu.lsi.dao.JpaDAO;
import es.ubu.lsi.model.conciertos.Grupo;


/**
 * GrupoDAO
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 *
 * @param <E> entity type
 * @param <K> key type
 */
public class GrupoDAO<E,K> extends JpaDAO<Grupo,Integer> {

	/**
	 * Constructor.
	 * 
	 * @param em entity manager.
	 */
	public GrupoDAO(EntityManager em) {
		super(em);
	}

	
	/**
	 * Busca todas las entidades.
	 * 
	 * @return todas las entidades.
	 */
	@Override
	public List<Grupo> findAll() {
		return getEntityManager().createNamedQuery("Grupo.findAll",
				Grupo.class)
				.getResultList();
	}
	
	
	/**
	 * Desactiva grupo, asigna 0 a la propiedad activo.
	 * 
	 * @param grupo id de grupo.
	 */
	public void desactivarGrupo(Grupo grupo) {
		grupo.setActivo(0);
	}
	
	/**
	 * Busca todas las entidades de un grafo de entidades. 
	 * 
	 * @param nombreGrafo grafo de entidad.
	 * @param pista tipo de carga.
	 * @return todas las entidades.
	 */
	public List<Grupo> consultar(String nombreGrafo,String pista ) {
		return getEntityManager().createNamedQuery("Grupo.findAll",Grupo.class)
				.setHint(pista, getEntityManager().getEntityGraph(nombreGrafo))
				.getResultList();
	}

}
