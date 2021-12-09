package model;

import java.util.List;

public class PromoAbsoluta extends Promocion {

	private double costoFijo;

	public PromoAbsoluta(List<Facturable> listaDeAtracciones, TipoAtraccion tipoDePromocion, String nombreDePromocion, double costoFijo, boolean active, int id) {
		super(listaDeAtracciones, tipoDePromocion, nombreDePromocion, active, enumDePromocion.ABSOLUTA, id);
		this.costoFijo = costoFijo;
	}

	@Override
	public double obtenerCostoTotal() {
		return costoFijo;
	}

	@Override
	public String toString() {
		return super.toString() + " Todo al precio de: " + costoFijo + "\n";
	}

}
