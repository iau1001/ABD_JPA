package es.ubu.lsi.model.conciertos;

import java.io.Serializable;

import javax.persistence.*;

/**
 * Clase embebida DireccionPostal.
 * 
 * @author <a href="mailto:iau1001@alu.ubu.es">Irati Arraiza Urquiola</a>
 */
@Embeddable
public class DireccionPostal implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//Ciudad
	private String ciudad;

	//Cp
	private String cp;

	//Direccion
	private String direccion;
	
	//Getter ciudad
	public String getCiudad() {
		return this.ciudad;
	}

	//Setter ciudad
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	//Getter cp
	public String getCp() {
		return this.cp;
	}

	//Setter cp
	public void setCp(String cp) {
		this.cp = cp;
	}

	//Getter direccion
	public String getDireccion() {
		return this.direccion;
	}

	//Setter direccion
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	//Redefinicion toString
	@Override
	public String toString() {
		return "DireccionPostal [direccion="+getDireccion()+
				", codigoPostal="+getCp()+", ciudad="+getCiudad()+"]";
	}

}
