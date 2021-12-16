package model;

import java.sql.SQLException;

public interface Facturable {

	 double obtenerCostoTotal();

	 double obtenerTiempoTotal();

	 boolean hayCupo();

	 void restarCupo() throws SQLException;

	 boolean esPromocion();

	TipoAtraccion getTipo();

	 boolean seEncuentraEnElFacturable(Facturable facturable);

	 void update() throws SQLException;
	 
	 int getID();

}
