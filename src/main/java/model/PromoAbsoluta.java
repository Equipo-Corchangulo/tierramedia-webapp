package model;

import java.util.HashMap;
import java.util.List;

public class PromoAbsoluta extends Promocion {

	private double costoFijo;

	public PromoAbsoluta(List<Facturable> listaDeAtracciones, TipoAtraccion tipoDePromocion,
						 String nombreDePromocion, double costoFijo, boolean active, int id, String descripcion) {
		super(listaDeAtracciones, tipoDePromocion, nombreDePromocion, active, enumDePromocion.ABSOLUTA, id, descripcion,
				enumDePromocion.ABSOLUTA);
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
	
	@Override
	public HashMap<String, String> validate(){
		HashMap<String, String> errors =super.validate();
		if(this.costoFijo < 1) errors.put("Costo fijo", "el costo fijo debe ser mayor a 0");
		System.out.println(errors);
		
		return errors;
	}

}
