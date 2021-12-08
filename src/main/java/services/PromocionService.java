package services;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;
import model.Promocion;
import persistence.commons.DAOFactory;

public class PromocionService {
	public List<Promocion> list() throws SQLException {
		return DAOFactory.getPromocionDAO().findAll();
	}

}
