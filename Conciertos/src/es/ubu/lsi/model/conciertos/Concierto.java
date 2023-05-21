package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.Set;


/**
 * Clase persistente para tabla CONCIERTO.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 */
@Entity
@NamedQuery(name="Concierto.findAll", query="SELECT c FROM Concierto c")
public class Concierto implements Serializable {
	private static final long serialVersionUID = 1L;

	//Clave primaria
	@Id
	private int idconcierto;

	//Ciudad
	private String ciudad;
	
	//Fecha
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	//Nombre
	private String nombre;

	//Precio
	private double precio;

	//Tickets
	private int tickets;

	//Relacion muchos a uno hacia Compra
	@OneToMany(mappedBy="concierto")
	private Set<Compra> compras;

	//Relacion muchos a uno hacia Grupo
	@ManyToOne
	@JoinColumn(name="IDGRUPO")
	private Grupo grupo;

	//Constructor
	public Concierto() {
	}

	//Getter idconcierto
	public int getIdconcierto() {
		return this.idconcierto;
	}

	//Setter idconcierto
	public void setIdconcierto(int idconcierto) {
		this.idconcierto = idconcierto;
	}

	//Getter ciudad
	public String getCiudad() {
		return this.ciudad;
	}

	//Setter ciudad
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	//Getter fecha
	public Date getFecha() {
		return this.fecha;
	}

	//Setter fecha
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	//Getter nombre
	public String getNombre() {
		return this.nombre;
	}

	//Setter nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	//Getter precio
	public double getPrecio() {
		return this.precio;
	}

	//Setter precio
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	//Getter tickets
	public int getTickets() {
		return this.tickets;
	}

	//Setter tickets
	public void setTickets(int tickets) {
		this.tickets = tickets;
	}

	//Getter compras
	public Set<Compra> getCompras() {
		return this.compras;
	}

	//Setter compras
	public void setCompras(Set<Compra> compras) {
		this.compras = compras;
	}

	//Añadir compra
	public Compra addCompra(Compra compra) {
		getCompras().add(compra);
		compra.setConcierto(this);
		return compra;
	}

	//Eliminar compra
	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setConcierto(null);
		return compra;
	}

	//Getter grupo
	public Grupo getGrupo() {
		return this.grupo;
	}

	//Setter grupo
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	//Redefinicioin toString
	@Override
	public String toString() {
		return "Concierto [idconcierto="+getIdconcierto()+", nombre="+getNombre()+
				", ciudad="+getCiudad()+", fecha="+getFecha()+", tickets="+getTickets()+
				", precio="+getPrecio()+"]";
	}

}
