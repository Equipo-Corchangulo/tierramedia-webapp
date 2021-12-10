package persistence.impl;

import model.Atraccion;
import model.TipoAtraccion;
import model.TipoDeAtraccion;
import persistence.IAtraccionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AtraccionesDAO implements IAtraccionDAO {
    private static List<Atraccion> atraccionList = new ArrayList<Atraccion>();

    public Atraccion toAtraccion(ResultSet result) throws SQLException {
		TipoAtraccion tipoAtraccion =  DAOFactory.getTipoAtraccionDAO().find(result.getInt("tipo"));

        return new Atraccion(result.getString("nombre"), result.getDouble("costo_visita"),
                result.getDouble("tiempo_promedio"), result.getInt("cupo_diario"),
				tipoAtraccion, result.getInt("id"), result.getInt("active") == 1);
    }

    public List<Atraccion> findAll() throws SQLException {
        String query = "SELECT * FROM atracciones";
        List<Atraccion> listaAtracciones = new ArrayList<Atraccion>();
        ResultSet result = ConnectionProvider.executeQuery(query);
        while (result.next()) {
            listaAtracciones.add(toAtraccion(result));
        }
        atraccionList = listaAtracciones;
        return listaAtracciones;
    }
    
    public static void editarCupoDeAtraccion(int id, int nuevoCupo) throws SQLException {
    	String query = "update atracciones SET cupo_diario = " + nuevoCupo + " WHERE id = " + id;
    	ConnectionProvider.executeUpdate(query);
    }

	@Override
	public Atraccion find(Integer id) throws SQLException {
		String query = "SELECT * FROM atracciones where atracciones.id = " + id;
    	Optional<Atraccion> atraccionEncontrada =  atraccionList.stream().filter(atraccion -> atraccion.getID() == id).findFirst();
    	if (atraccionEncontrada.isPresent()) {
    		return (Atraccion) atraccionEncontrada.get();
    	}
    	else {
    		ResultSet result = ConnectionProvider.executeQuery(query);
    		return toAtraccion(result);
    	}
	}

	@Override
	public int insert(Atraccion t) {
		String query = "INSERT into atracciones(nombre, costo_visita,tiempo_promedio,cupo_diario,tipo) VALUES (?,?,?,?,?)";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1,  String.valueOf(t.getNombre()));
			statement.setString(2,  String.valueOf(t.obtenerCostoTotal()));
			statement.setString(3,  String.valueOf(t.obtenerTiempoTotal()));
			statement.setString(4, String.valueOf(t.getCupo()));
			statement.setString(5, String.valueOf(t.getTipo().getID()));
			statement.execute();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(Atraccion t) {
		String query = "UPDATE atracciones SET nombre = ?, costo_visita = ?,tiempo_promedio = ?,cupo_diario = ?,tipo = ? where id = ?";
		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);

			statement.setString(1,  String.valueOf(t.getNombre()));
			statement.setString(2,  String.valueOf(t.obtenerCostoTotal()));
			statement.setString(3,  String.valueOf(t.obtenerTiempoTotal()));
			statement.setString(4, String.valueOf(t.getCupo()));
			statement.setString(5, String.valueOf(t.getTipo().getID()));
			statement.setString(6, String.valueOf(t.getID()));
			statement.execute();
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer id) {
		String query = "UPDATE atracciones SET active = 0 " + "WHERE id = " + id;
		try {
			ConnectionProvider.executeUpdate(query);
			return 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public Atraccion findByname(String name) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
    
}
