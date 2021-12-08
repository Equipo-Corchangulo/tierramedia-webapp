package persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.TipoDeAtraccion;
import persistence.TipoAtraccionDAO;

public class TipoAtraccionesDAOImpl implements TipoAtraccionDAO {

	@Override
	public TipoDeAtraccion find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TipoDeAtraccion> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(TipoDeAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(TipoDeAtraccion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int findByname(String name) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public TipoDeAtraccion toAtraccion(ResultSet res) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
