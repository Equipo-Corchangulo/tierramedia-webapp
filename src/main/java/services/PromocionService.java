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

    public void delete(Integer id) {
		DAOFactory.getPromocionDAO().delete(id);
    }

    public Promocion find(int numericId) throws SQLException {
        return DAOFactory.getPromocionDAO().find(numericId);
    }

    public void create(Promocion promocion) {
        DAOFactory.getPromocionDAO().insert(promocion);
    }
    public void update(Promocion promocion){
        DAOFactory.getPromocionDAO().update(promocion);
    }
}
