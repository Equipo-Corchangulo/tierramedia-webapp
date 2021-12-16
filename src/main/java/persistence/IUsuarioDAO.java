package persistence;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.PerfilUsuario;
import persistence.commons.GenericDAO;

public interface IUsuarioDAO extends GenericDAO<PerfilUsuario> {
	
	public abstract PerfilUsuario findByUsername(String username) throws Exception;	
	public abstract PerfilUsuario toPerfilUsuario(ResultSet res) throws SQLException;
	public abstract void updateUsuarios(Integer id, double presupuesto, double tiempoDisponible) throws SQLException;
}
