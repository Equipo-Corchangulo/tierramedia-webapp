package model;

import java.sql.SQLException;
import java.util.List;

public class PromoAxB extends Promocion {

	public Facturable atraccionExtra;

	public PromoAxB(List<Facturable> listaDeAtracciones, TipoAtraccion tipoDePromocion
			, String nombreDePromocion, Facturable atraccionExtra, boolean active, int id, String descripcion) {
		super(listaDeAtracciones, tipoDePromocion, nombreDePromocion, active, enumDePromocion.AXB, id, descripcion);
		this.atraccionExtra = atraccionExtra;
	}
	
	@Override
	public List<Facturable> getListaDeAtracciones() {
		List<Facturable> atracciones = this.listaDeAtracciones;
		atracciones.add(atraccionExtra);
		return atracciones;
	}

	@Override
	public double obtenerTiempoTotal() {
		double tiempoTotal = super.obtenerTiempoTotal();
		tiempoTotal += atraccionExtra.obtenerTiempoTotal();
		return tiempoTotal;
	}

	@Override
	public boolean hayCupo() {
		boolean hayCupo = super.hayCupo();
		return hayCupo && atraccionExtra.hayCupo();
	}

	@Override
	public void restarCupo() throws SQLException {
		super.restarCupo();
		atraccionExtra.restarCupo();
	}

	@Override
	public boolean seEncuentraEnElFacturable(Facturable facturable) {

		return super.seEncuentraEnElFacturable(facturable) || this.atraccionExtra.seEncuentraEnElFacturable(facturable);
	}

	@Override
	public String toString() {
		return super.toString() +" Atraccion de regalo: \n" + atraccionExtra;
	}

}
