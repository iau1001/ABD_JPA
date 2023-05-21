package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * Clase persistente para tabla GRUPO.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 */
@Entity
@NamedQuery(name="Grupo.findAll", query="SELECT g FROM Grupo g")
@NamedEntityGraph(
		name = "gruposConConciertosComprasyClientes",
		attributeNodes = {
				@NamedAttributeNode(value="conciertos",subgraph = "conciertosComprasClientes")
		
		},
		subgraphs = {
				@NamedSubgraph(
						name = "conciertosComprasClientes",
						attributeNodes = {
								@NamedAttributeNode(value = "compras",subgraph = "comprasClientes")
						}),
				@NamedSubgraph(
						name="comprasClientes",
						attributeNodes = {
								@NamedAttributeNode(value = "cliente")
						})
		}								
)
public class Grupo implements Serializable {
	private static final long serialVersionUID = 1L;

	//Clave primaria
	@Id
	private int idgrupo;

	//Activo
	private int activo;

	//Estilo
	private String estilo;

	//Nombre
	private String nombre;

	//Relacion muchos a uno hacia Concierto
	@OneToMany(mappedBy="grupo")
	private Set<Concierto> conciertos;

	//Constructor
	public Grupo() {
	}

	//Getter idgrupo
	public int getIdgrupo() {
		return this.idgrupo;
	}

	//Setter idgrupo
	public void setIdgrupo(int idgrupo) {
		this.idgrupo = idgrupo;
	}

	//Getter activo
	public int getActivo() {
		return this.activo;
	}

	//Setter activo
	public void setActivo(int activo) {
		this.activo = activo;
	}

	//Getter estilo
	public String getEstilo() {
		return this.estilo;
	}

	//Setter estilo
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	//Getter nombre
	public String getNombre() {
		return this.nombre;
	}

	//Setter nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Getter conciertos
	public Set<Concierto> getConciertos() {
		return this.conciertos;
	}

	//Setter conciertos
	public void setConciertos(Set<Concierto> conciertos) {
		this.conciertos = conciertos;
	}

	//Añadir concierto
	public Concierto addConcierto(Concierto concierto) {
		getConciertos().add(concierto);
		concierto.setGrupo(this);
		return concierto;
	}

	//Eliminar concierto
	public Concierto removeConcierto(Concierto concierto) {
		getConciertos().remove(concierto);
		concierto.setGrupo(null);
		return concierto;
	}
	
	//Redefinicion toString
	@Override
	public String toString() {
		return "Grupo [idgrupo ="+getIdgrupo()+", nombre="+getNombre()+
				", estilo="+getEstilo()+", activo="+getActivo()+"]";
	}

}