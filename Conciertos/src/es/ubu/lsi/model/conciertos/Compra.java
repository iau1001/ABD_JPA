package es.ubu.lsi.model.conciertos;

import java.io.Serializable;
import javax.persistence.*;


/**
 * Clase persistente para tabla COMPRA.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 */
@Entity
@NamedQuery(name="Compra.findAll", query="SELECT c FROM Compra c")
public class Compra implements Serializable {
	private static final long serialVersionUID = 1L;

	//Clave primaria
	@Id
	private int idcompra;

	//Tickets
	@Column(name="N_TICKETS")
	private int nTickets;

	//Relacion muchos a uno hacia Cliente
	@ManyToOne
	@JoinColumn(name="NIF")
	private Cliente cliente;

	//Relacion muchos a uno hacia Concierto
	@ManyToOne
	@JoinColumn(name="IDCONCIERTO")
	private Concierto concierto;

	//Constructor
	public Compra() {
	}

	//Getter idcompra
	public long getIdcompra() {
		return this.idcompra;
	}

	//Setter idcompra
	public void setIdcompra(int idcompra) {
		this.idcompra = idcompra;
	}

	//Getter tickets
	public int getNTickets() {
		return this.nTickets;
	}

	//Setter tickets
	public void setNTickets(int nTickets) {
		this.nTickets = nTickets;
	}

	//Getter cliente
	public Cliente getCliente() {
		return this.cliente;
	}

	//Setter cliente
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	//Getter concierto
	public Concierto getConcierto() {
		return this.concierto;
	}

	//Setter concierto
	public void setConcierto(Concierto concierto) {
		this.concierto = concierto;
	}
	
	//Redefinicion toString
	@Override
	public String toString() {
		return "Compra [idcompra="+getIdcompra()+", cliente="+getCliente().toString()+
				", concierto="+getConcierto().toString()+", tickets="+getNTickets()+"]";
	}

}
