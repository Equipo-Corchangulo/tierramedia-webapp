package persistence.impl;

import model.Atraccion;
import model.TipoAtraccion;
import model.TipoDeAtraccion;
import persistence.IAtraccionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

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
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Atraccion t) {
		// TODO Auto-generated method stub
		return 0;
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
