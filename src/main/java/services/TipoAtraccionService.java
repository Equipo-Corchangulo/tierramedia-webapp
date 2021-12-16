package services;

import model.TipoAtraccion;
import persistence.commons.DAOFactory;

import java.sql.SQLException;
import java.util.List;

public class TipoAtraccionService {

    public TipoAtraccion getByName(String name) throws SQLException {
        return DAOFactory.getTipoAtraccionDAO().findByname(name);
    }

    public List<TipoAtraccion> list() throws SQLException{
        return DAOFactory.getTipoAtraccionDAO().findAll();
    }
    public void delete(int id ) {
        DAOFactory.getTipoAtraccionDAO().delete(id);
    }

    public TipoAtraccion find(int id) throws SQLException {
        return DAOFactory.getTipoAtraccionDAO().find(id);
    }

    public void create(TipoAtraccion tipoAtraccion) throws SQLException {
        DAOFactory.getTipoAtraccionDAO().insert(tipoAtraccion);
    }

    public void update(TipoAtraccion tipoAtraccion) throws SQLException{
        DAOFactory.getTipoAtraccionDAO().update(tipoAtraccion);
    }
}
