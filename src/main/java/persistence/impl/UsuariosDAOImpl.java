package persistence.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Facturable;
import model.PerfilUsuario;
import model.TipoAtraccion;
import persistence.IUsuarioDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
//import persistence.commons.MissingDataException;



public class UsuariosDAOImpl implements IUsuarioDAO {
	
	public PerfilUsuario toPerfilUsuario(ResultSet result) throws SQLException {
		List<Facturable> listadeItinerario = new ArrayList<Facturable>();
		TipoAtraccion tipoAtraccion =  DAOFactory.getTipoAtraccionDAO().find(result.getInt("atraccion_preferida"));
		
		String atraccionesStr = result.getString("atracciones");
		if(atraccionesStr != null)
		{
			String[] atraccionIdList = atraccionesStr.split("-");
			getFacturableByIdList(atraccionIdList,false, listadeItinerario);
		}
		
		String promoStr = result.getString("promociones");
		if(promoStr != null) {
			
			String[] promoIdList= promoStr.split("-");
			getFacturableByIdList(promoIdList,true, listadeItinerario);
		}
		
		
		
	    return new PerfilUsuario(result.getString("nombre"), result.getDouble("presupuesto"), 
	    		result.getInt("tiempo_disponible"), 
	    		tipoAtraccion, 
	    		result.getInt("id"),
	    		listadeItinerario, result.getString("username"), result.getString("password"), result.getBoolean("admin"), result.getBoolean("active"));
	}
	
	public static void getFacturableByIdList(String[] facturableList, boolean isPromo, List<Facturable> resultList) throws NumberFormatException, SQLException {
		AtraccionesDAO atraccionesDAO = (AtraccionesDAO) DAOFactory.getAtraccionDAO();
		PromocionDAO promocionDAO = (PromocionDAO) DAOFactory.getPromocionDAO();
		for(String a : facturableList) {
			if (!isPromo)
				resultList.add(atraccionesDAO.find(Integer.parseInt(a)));
			else
				resultList.add(promocionDAO.find(Integer.parseInt(a)));
		}
	}
	
	public List<PerfilUsuario> findAll() throws SQLException {
		String query = "SELECT usuarios.*, group_concat(itinerarios.atraccion,'-') AS atracciones," +
				" group_concat(itinerarios.promocion,'-') AS promociones" +
				" FROM usuarios" +
				" LEFT JOIN itinerarios ON usuarios.id = itinerarios.usuario" +
				" GROUP BY usuarios.id";
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		List<PerfilUsuario> listaUsuarios = new ArrayList<PerfilUsuario>();
		while(results.next()) {
			listaUsuarios.add(toPerfilUsuario(results));
		}
		
		return listaUsuarios;
	}
    
	@Override
	public void updateUsuarios(Integer id, double nuevoPresupuesto, double nuevoTiempoDisponible) throws SQLException {
		String query = "UPDATE usuarios SET presupuesto = " + nuevoPresupuesto
				+ ", tiempo_disponible = " + nuevoTiempoDisponible 
				+ " WHERE id = " + id;
    	ConnectionProvider.executeUpdate(query);
	}
	
	public PerfilUsuario findByUsername(String username) throws Exception {
		try {
			String query = "SELECT usuarios.*, group_concat(itinerarios.atraccion,'-') AS atracciones," +
					" group_concat(itinerarios.promocion,'-') AS promociones" +
					" FROM usuarios" +
					" LEFT JOIN itinerarios ON usuarios.id = itinerarios.usuario" + " WHERE usuarios.username = '"+username+
					"' GROUP BY usuarios.id ";
			Connection conn = ConnectionProvider.getConnection();
			
			PreparedStatement statement = conn.prepareStatement(query);
			ResultSet results = statement.executeQuery();
			
			return toPerfilUsuario(results);
		} catch (Exception e) {
			throw new Exception(e);
			//throw new MissingDataException(e);
		}
	}

	@Override
	public PerfilUsuario find(Integer id) throws SQLException {
		String query = "SELECT usuarios.*, group_concat(itinerarios.atraccion,'-') AS atracciones," +
				" group_concat(itinerarios.promocion,'-') AS promociones" +
				" FROM usuarios" +
				" LEFT JOIN itinerarios ON usuarios.id = itinerarios.usuario WHERE usuarios.id = "+id +
				" GROUP BY usuarios.id ";
    	
    	Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		return toPerfilUsuario(results);
	}

	@Override
	public int insert(PerfilUsuario t) {
		String query = "Insert into usuarios(username,password,nombre,presupuesto,tiempo_disponible,atraccion_preferida,admin) values(?,?,?,?,?,?,?)";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			
			statement.setString(1, t.getUsername());
			statement.setString(2, t.getPassword());
			statement.setString(3, t.getNombre());
			statement.setString(4, String.valueOf(t.getPresupuesto()));
			statement.setString(5, String.valueOf(t.getTiempoDisponible()));
			statement.setString(6, String.valueOf(t.getTipoDeAtraccion().getID()));
			statement.setString(7, t.isAdmin()?"1":"0");
			statement.execute();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}

	@Override
	public int update(PerfilUsuario t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		String query = "UPDATE usuarios SET active = 0 " + "WHERE id = " + id;
    	try {
			ConnectionProvider.executeUpdate(query);
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
