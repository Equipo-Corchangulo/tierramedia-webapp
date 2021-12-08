package persistence.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PerfilUsuario;
import model.TipoDeAtraccion;
import persistence.IUsuarioDAO;
import persistence.commons.ConnectionProvider;
//import persistence.commons.MissingDataException;



public class UsuariosDAOImpl implements IUsuarioDAO {
	
	@Override
	public PerfilUsuario toPerfilUsuario(ResultSet result) throws SQLException {
	    return new PerfilUsuario(result.getInt("id"), result.getString("nombre"), result.getInt("presupuesto"), 
	    		result.getInt("tiempo_disponible"), 
	    		TipoDeAtraccion.values()[result.getInt("atraccion_preferida")-1], result.getString("username"), 
	    		result.getString("password"), result.getBoolean("admin"), result.getBoolean("active"));
	}
	
	public List<PerfilUsuario> findAll() throws SQLException {
		String query = "SELECT * FROM usuarios";
		
		Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		List<PerfilUsuario> listaUsuarios = new ArrayList<PerfilUsuario>();
		while(results.next()) {
			listaUsuarios.add(toPerfilUsuario(results));
		}
		
		return listaUsuarios;
	}
	
	public PerfilUsuario findByID(int id) throws SQLException {
    	String query = "SELECT * FROM usuarios LEFT JOIN itinerarios ON itinerarios.usuario = usuarios.id where usuarios.id = " + id;
    	
    	Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		return toPerfilUsuario(results);
    }
    
	
	public static void updateUsuarios(int id, int nuevoPresupuesto, int nuevoTiempoDisponible) throws SQLException {
		String query = "UPDATE usuarios SET presupuesto = " + nuevoPresupuesto + "tiempo_disponible= " + nuevoTiempoDisponible 
				+ "WHERE id = " + id;
    	ConnectionProvider.executeUpdate(query);
	}
	
	public PerfilUsuario findByUsername(String username) throws Exception {
		try {
			String sql = "SELECT * FROM usuarios WHERE username = ?";
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, username);
			
			ResultSet resultados = statement.executeQuery();

			PerfilUsuario usuario = null;
			if (resultados.next()) {
				usuario = toPerfilUsuario(resultados);
			}

			return usuario;
		} catch (Exception e) {
			throw new Exception(e);
			//throw new MissingDataException(e);
		}
	}

	@Override
	public PerfilUsuario find(Integer id) {
		// TODO Auto-generated method stub
		return null;
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
			statement.setString(6, String.valueOf(t.getTipoDeAtraccion().ordinal()+1));
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
