package model;

import java.sql.SQLException;

import persistence.impl.AtraccionesDAO;


public class Atraccion  extends ActivableItem implements Facturable {

	private double costoVisita;
	private double tiempoPromedio;
	private int cupoDiario;
	private String nombre;
	private TipoDeAtraccion tipoAtraccion;
	private int ID;

	public Atraccion(String nombre, double costoVisita, double tiempoPromedio, int cupoDiario, TipoDeAtraccion tipoAtraccion, int ID, boolean active) {
		super(active);
		this.nombre = nombre;
		this.costoVisita = costoVisita;
		this.tiempoPromedio = tiempoPromedio;
		this.cupoDiario = cupoDiario;
		this.tipoAtraccion = tipoAtraccion;
		this.ID = ID;
	}
	public int getID(){
		return  this.ID;
	}

	@Override
	public String toString() {
		return "Atraccion: " + nombre + ", duracion: " + tiempoPromedio + ", costo: " + costoVisita +
				" tipo de atraccion: " + tipoAtraccion  + " cupo "+ cupoDiario +"\n";

	}

	@Override
	public double obtenerCostoTotal() {

		return this.costoVisita;
	}

	@Override
	public double obtenerTiempoTotal() {

		return  this.tiempoPromedio;
	}

	public int getCupoDiario() {
		return cupoDiario;
	}
	public void setCupoDiario(int cupoDiario) {
		this.cupoDiario = cupoDiario;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public TipoDeAtraccion getTipoAtraccion() {
		return tipoAtraccion;
	}
	public void setTipoAtraccion(TipoDeAtraccion tipoAtraccion) {
		this.tipoAtraccion = tipoAtraccion;
	}
	@Override
	public boolean hayCupo() {

		return this.cupoDiario > 0;
	}

	@Override
	public void restarCupo() throws SQLException {
		this.cupoDiario--;
		AtraccionesDAO.editarCupoDeAtraccion(this.ID, this.cupoDiario);

	}

	public int getCupo() {
		return this.cupoDiario;
	}
	
	@Override
	public TipoDeAtraccion getTipo() {

		return this.tipoAtraccion;

	}

	@Override
	public boolean seEncuentraEnElFacturable(Facturable facturable) {
		if(facturable.getClass() == Atraccion.class)
			return this.equals(facturable);

		return facturable.seEncuentraEnElFacturable(this);
	}

	@Override
	public  boolean esPromocion(){
		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costoVisita);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + cupoDiario;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		temp = Double.doubleToLongBits(tiempoPromedio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((tipoAtraccion == null) ? 0 : tipoAtraccion.hashCode());
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
		Atraccion other = (Atraccion) obj;
		if (Double.doubleToLongBits(costoVisita) != Double.doubleToLongBits(other.costoVisita))
			return false;
		if (cupoDiario != other.cupoDiario)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (Double.doubleToLongBits(tiempoPromedio) != Double.doubleToLongBits(other.tiempoPromedio))
			return false;
		if (tipoAtraccion != other.tipoAtraccion)
			return false;
		return true;
	}
	 
	
}
