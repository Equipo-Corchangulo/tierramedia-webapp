package utils;

import java.util.Comparator;

import model.Facturable;
import model.TipoAtraccion;

public class ComparadorDeFacturable implements Comparator<Facturable> {

	private  TipoAtraccion preferencia;

	public ComparadorDeFacturable(TipoAtraccion preferencia) {

		this.preferencia = preferencia;
	}



	@Override
	public int compare(Facturable o1, Facturable o2) {
		if (this.preferencia.getID() == o1.getTipo().getID() && this.preferencia.getID() != o2.getTipo().getID())
			return -1;
		else if (this.preferencia != o1.getTipo() && this.preferencia == o2.getTipo())
			return 1;
		else if (o1.esPromocion() == true && o2.esPromocion() == false)
			return -1;
		else if (o1.esPromocion() == false && o2.esPromocion() == true)
			return 1;
		else if (o1.obtenerCostoTotal() > o2.obtenerCostoTotal())
			return -1;
		else if (o1.obtenerCostoTotal() < o2.obtenerCostoTotal())
			return 1;
		else if (o1.obtenerTiempoTotal() > o2.obtenerTiempoTotal())
			return -1;
		else if (o1.obtenerTiempoTotal() < o2.obtenerTiempoTotal())
			return 1;
		else
			return 0;	
	}

}