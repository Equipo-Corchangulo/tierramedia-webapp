package services;

import model.TipoAtraccion;
import persistence.commons.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class TipoAtraccionService {

    public TipoAtraccion getByName(String name) throws Exception {
        return DAOFactory.getTipoAtraccionDAO().findByname(name);
    }

    public List<TipoAtraccion> list() throws SQLException{
        return DAOFactory.getTipoAtraccionDAO().findAll();
    }
    public void delete(int id ) {
        DAOFactory.getTipoAtraccionDAO().delete(id);
    }
}
