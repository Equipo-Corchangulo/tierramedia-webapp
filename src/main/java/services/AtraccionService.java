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
	
	public PerfilUsuario update(Integer id, String nombre, String username, String password, Double money, int tiempo, Boolean isAdmin, TipoAtraccion tipo, boolean active) {
		PerfilUsuario user = new PerfilUsuario(id, nombre, money, tiempo, 
				tipo, username, password, isAdmin, active);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().update(user);
		}
		return user;
	}
	
	public PerfilUsuario find(Integer id) throws SQLException {
		return DAOFactory.getUsuarioDAO().find(id);
	}
	
	public PerfilUsuario create( String nombre, String username, String password, Double money, int tiempo, boolean isAdmin, TipoAtraccion tipo, boolean active) {
		PerfilUsuario user = new PerfilUsuario(nombre, money, tiempo, 
				tipo, username, password, isAdmin, active);
		if(user.isValid()) {
			DAOFactory.getUsuarioDAO().insert(user);
		}
		return user;
	}

	public void delete(int id){
		DAOFactory.getAtraccionDAO().delete(id);
	}
}
