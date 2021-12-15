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
        try {
			DAOFactory.getPromocionDAO().insert(promocion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    public void update(Promocion promocion){
        try {
			DAOFactory.getPromocionDAO().update(promocion);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
