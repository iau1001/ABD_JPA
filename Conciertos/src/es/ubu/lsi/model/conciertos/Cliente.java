package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * Clase persistente para tabla CLIENTE.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 */
@Entity
@NamedQuery(name="Cliente.findAll", query="SELECT c FROM Cliente c")
public class Cliente implements Serializable {
	private static final long serialVersionUID = 1L;

	//Clave primaria
	@Id
	private String nif;

	//Apellidos
	private String apellidos;

	//Nombre
	private String nombre;
	
	//Direccion embebida.
	@Embedded private DireccionPostal direccionPostal;

	//Relacion muchos a uno hacia Compra
	@OneToMany(mappedBy="cliente")
	private Set<Compra> compras;

	//Constructor
	public Cliente() {
	}

	//Getter nif
	public String getNif() {
		return this.nif;
	}

	//Setter nif
	public void setNif(String nif) {
		this.nif = nif;
	}

	//Getter apellidos
	public String getApellidos() {
		return this.apellidos;
	}

	//Setter apellidos
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	//Getter nombre
	public String getNombre() {
		return this.nombre;
	}

	//Setter nombre
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
		compra.setCliente(this);
		return compra;
	}

	//Eliminar compra
	public Compra removeCompra(Compra compra) {
		getCompras().remove(compra);
		compra.setCliente(null);
		return compra;
	}
	
	//Redefinicion toString
	@Override
	public String toString() {
		return "Cliente [nif="+getNif()+", nombre="+getNombre()+
				", apellidos="+getApellidos()+", direccionPostal="+direccionPostal.toString()+"]";
	}

}
