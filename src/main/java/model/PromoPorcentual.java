package model;

import java.util.List;

public class PromoPorcentual extends Promocion {

	private double porcentajeDescuento;

	public PromoPorcentual(List<Facturable> listaDeAtracciones, TipoAtraccion tipoDePromocion
			, String nombreDePromocion, double porcentajeDescuento, boolean active, int id, String descripcion) {
		super(listaDeAtracciones, tipoDePromocion, nombreDePromocion, active, enumDePromocion.PORCENTUAL, id, descripcion,
				enumDePromocion.PORCENTUAL);
		this.porcentajeDescuento = porcentajeDescuento;
	}

	public double getPorcentajeDescuento() {
		return porcentajeDescuento;
	}

	@Override
	public double obtenerCostoTotal() {
		double costoTotal = super.obtenerCostoTotal();
		costoTotal -= costoTotal *  (porcentajeDescuento*0.01);
		return costoTotal;
	}

	@Override
	public String toString() {
		
		return super.toString() + " El porcentaje de descuento es de: " + porcentajeDescuento + "% \n";
	}
	
}
