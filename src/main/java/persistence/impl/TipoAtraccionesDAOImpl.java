package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.TipoAtraccion;
import persistence.ITipoAtraccionDAO;
import persistence.commons.ConnectionProvider;

public class TipoAtraccionesDAOImpl implements ITipoAtraccionDAO {

	@Override
	public TipoAtraccion find(Integer id)  throws SQLException{
		String query="SELECT * FROM tipo_de_atracciones WHERE id = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, String.valueOf(id));

		ResultSet resultados = statement.executeQuery();
		return toTipoAtraccion(resultados);
	}

	@Override
	public List<TipoAtraccion> findAll() throws SQLException {
		List<TipoAtraccion> tipoAtraccionList = new ArrayList<TipoAtraccion>();
		String query="SELECT * FROM tipo_de_atracciones";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet resultados = statement.executeQuery();
		while (resultados.next()) {
			tipoAtraccionList.add(toTipoAtraccion(resultados));
		}
		return tipoAtraccionList;
	}

	@Override
	public int insert(TipoAtraccion t) {
		String query = "Insert into tipo_de_atracciones(nombre_tipo) values(?)";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, t.getName());
			statement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(TipoAtraccion t) {
		String query = "UPDATE tipo_de_atracciones SET nombre_tipo = ? WHERE id = ?";
		try {
			Connection conn = ConnectionProvider.getConnection();
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setString(1, t.getName());
			statement.setString(2, String.valueOf(t.getID()));
			statement.execute();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int delete(Integer id) {
		String query = "UPDATE tipo_de_atracciones SET active = 0 " + "WHERE id = " + id;
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
	public TipoAtraccion findByname(String name) throws SQLException {
		String query="SELECT * FROM tipo_de_atracciones WHERE nombre_tipo = ?";
		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, name);

		ResultSet resultados = statement.executeQuery();
		return toTipoAtraccion(resultados);
	}

	@Override
	public TipoAtraccion toTipoAtraccion(ResultSet res) throws SQLException {
		TipoAtraccion tipoDeAtraccion = new TipoAtraccion(res.getInt("id"), res.getString("nombre_tipo"), res.getBoolean("active"));

		return tipoDeAtraccion;
	}

}
