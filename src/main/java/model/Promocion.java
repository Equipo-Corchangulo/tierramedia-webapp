package model;

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public abstract class Promocion extends ActivableItem  implements Facturable {

	public enum enumDePromocion {
		ABSOLUTA,
		AXB,
		PORCENTUAL;
	}

	private String nombreDePromocion;
	private String descripcion;
	private enumDePromocion promoType;
	private List<Facturable> listaDeAtracciones;

	private TipoAtraccion tipoDeAtraccion;
	private enumDePromocion tipo;

	public enumDePromocion getPromoType() {
		return promoType;
	}

	public void setPromoType(enumDePromocion promoType) {
		this.promoType = promoType;
	}

	private int ID;


	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Promocion(List<Facturable> listaDeAtracciones, TipoAtraccion tipoDeAtraccion,
					 String nombreDePromocion, boolean active, enumDePromocion tipo, int ID,
					 String descripcion, enumDePromocion promoType) {
		super(active);
		this.listaDeAtracciones = listaDeAtracciones;
		this.tipoDeAtraccion = tipoDeAtraccion;
		this.nombreDePromocion = nombreDePromocion;
		this.tipo = tipo;
		this.ID = ID;
		this.descripcion = descripcion;
		this.promoType = promoType;
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

	public TipoAtraccion getTipoDeAtraccion() {
		return tipoDeAtraccion;
	}

	public void setTipoDeAtraccion(TipoAtraccion tipoDeAtraccion) {
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
	public TipoAtraccion getTipo() {

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
	
	public boolean isValid() {
		return validate().isEmpty();
	}
	
	public HashMap<String, String> validate(){
		HashMap<String, String> errors = new HashMap<String, String>();
		if(this.nombreDePromocion.isBlank()) errors.put("nombre de promcion", "El nombre es requerido");		
		if(this.listaDeAtracciones.size() < 1) errors.put("lista de atracciones", "se debe agregar por lo menos 1 Atraccion");
		System.out.println(errors);
		return errors;
	}
	
	
}
