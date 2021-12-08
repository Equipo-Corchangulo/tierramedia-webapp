package model;

import java.sql.SQLException;
import java.util.List;

public abstract class Promocion extends ActivableItem  implements Facturable {
	public enum enumDePromocion {
		ABSOLUTA,
		AXB,
		PORCENTUAL;
	}

	public String nombreDePromocion;
	public List<Facturable> listaDeAtracciones;
	public TipoDeAtraccion tipoDeAtraccion;
	public enumDePromocion tipo;
	public int ID;

	public Promocion(List<Facturable> listaDeAtracciones, TipoDeAtraccion tipoDeAtraccion, String nombreDePromocion, boolean active, enumDePromocion tipo, int ID) {
		super(active);
		this.listaDeAtracciones = listaDeAtracciones;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.nombreDePromocion = nombreDePromocion;
		this.tipo = tipo;
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}
	
	public enumDePromocion getTipoPromo() {
		return tipo;
	}

	public void setTipo(enumDePromocion tipo) {
		this.tipo = tipo;
	}

	public List<Facturable> getListaDeAtracciones() {
		return listaDeAtracciones;
	}

	public void setListaDeAtracciones(List<Facturable> listaDeAtracciones) {
		this.listaDeAtracciones = listaDeAtracciones;
	}

	public TipoDeAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public void setTipoDeAtraccion(TipoDeAtraccion tipoDeAtraccion) {
		this.tipoDeAtraccion = tipoDeAtraccion;
	}

	public String getNombreDePromocion() {
		return nombreDePromocion;
	}

	public void setNombreDePromocion(String nombreDePromocion) {
		this.nombreDePromocion = nombreDePromocion;
	}

	@Override
	public String toString() {
		String salida = "Promocion: "+ this.nombreDePromocion
				+ " del tipo: " + this.getTipo()
				+ " costo: " + this.obtenerCostoTotal()
				+ " Tiempo requerido: " + this.obtenerTiempoTotal() + "\n"
				+ "El paquete incluye: \n";
			for (Facturable facturable : listaDeAtracciones) {
				salida += " " + facturable.toString();
			}
		return salida;
	}
	
	@Override
	public  boolean esPromocion(){
		return  true;
	}

	@Override
	public boolean hayCupo() {
		for (Facturable atraccion : listaDeAtracciones) {
			if (!atraccion.hayCupo())
				return false;
		}
		return true;
	}

	@Override
	public void restarCupo() throws SQLException {
		for(Facturable atraccion : listaDeAtracciones){
			atraccion.restarCupo();
		}

	}
	@Override
	public double obtenerTiempoTotal() {
		double tiempoTotal = 0;
		for(Facturable atraccion : listaDeAtracciones){
			tiempoTotal+= atraccion.obtenerTiempoTotal();
		}
		return tiempoTotal;

	}
	@Override
	public TipoDeAtraccion getTipo() {

		return this.tipoDeAtraccion;

	}

	@Override
	public double obtenerCostoTotal() {
		double costoTotal = 0;
		for (Facturable atraccion : listaDeAtracciones) {
			costoTotal += atraccion.obtenerCostoTotal();
		}
		return costoTotal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listaDeAtracciones == null) ? 0 : listaDeAtracciones.hashCode());
		result = prime * result + ((nombreDePromocion == null) ? 0 : nombreDePromocion.hashCode());
		result = prime * result + ((tipoDeAtraccion == null) ? 0 : tipoDeAtraccion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Promocion other = (Promocion) obj;
		if (listaDeAtracciones == null) {
			if (other.listaDeAtracciones != null)
				return false;
		} else if (!listaDeAtracciones.equals(other.listaDeAtracciones))
			return false;
		if (nombreDePromocion == null) {
			if (other.nombreDePromocion != null)
				return false;
		} else if (!nombreDePromocion.equals(other.nombreDePromocion))
			return false;
		if (tipoDeAtraccion != other.tipoDeAtraccion)
			return false;
		return true;
	}

	@Override
	public boolean seEncuentraEnElFacturable(Facturable facturable){
		for (Facturable atraccion : this.listaDeAtracciones) {
			if (facturable.seEncuentraEnElFacturable(atraccion)){
				return true;
			}
		}
		return false;
	}
	
	
}
