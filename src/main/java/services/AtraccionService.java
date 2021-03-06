package services;

import java.sql.SQLException;
import java.util.List;

import model.Atraccion;
import model.PerfilUsuario;
import model.TipoAtraccion;
import model.TipoDeAtraccion;
import persistence.commons.DAOFactory;

public class AtraccionService {

	public List<Atraccion> list() throws SQLException {
		return DAOFactory.getAtraccionDAO().findAll();
	}
	
	public void delete(Integer id) {
		DAOFactory.getAtraccionDAO().delete(id);
	}
	
	public PerfilUsuario update(Integer id, String nombre, String username, String password, Double money, int tiempo, Boolean isAdmin, TipoAtraccion tipo, boolean active) throws SQLException {
		PerfilUsuario user = new PerfilUsuario(id, nombre, money, tiempo, 
				tipo, username, password, isAdmin, active);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().update(user);
		}
		return user;
	}
	
	public Atraccion find(Integer id) throws SQLException {
		return DAOFactory.getAtraccionDAO().find(id);
	}

	public void delete(int id){
		DAOFactory.getAtraccionDAO().delete(id);
	}

	public void create(Atraccion atraccion) throws SQLException {
		DAOFactory.getAtraccionDAO().insert(atraccion);
	}

	public void update(Atraccion atraccion) throws SQLException {
		DAOFactory.getAtraccionDAO().update(atraccion);
	}
}
