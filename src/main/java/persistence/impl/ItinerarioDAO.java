package persistence.impl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import model.Itinerario;
import persistence.IItinerarioDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;
//import persistence.commons.MissingDataException;



public class ItinerarioDAO implements IItinerarioDAO {
	
	
	@Override
	public Itinerario toItinerario(ResultSet result) throws SQLException {
		Itinerario itinerario =  DAOFactory.getItinerarioDAO().find(result.getInt("itinerario"));

	    return new Itinerario();
	}
	
	
	
	
	public Itinerario findByID(int id) throws SQLException {
    	String query = "SELECT * FROM usuarios LEFT JOIN itinerarios ON itinerarios.usuario = usuarios.id where usuarios.id = " + id;
    	
    	Connection conn = ConnectionProvider.getConnection();
		
		PreparedStatement statement = conn.prepareStatement(query);
		ResultSet results = statement.executeQuery();
		
		return toItinerario(results);
    }




	@Override
	public Itinerario find(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public List<Itinerario> findAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public int insert(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int update(Itinerario t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public int delete(Integer id) {
		// TODO Auto-generated method stub
		return 0;
	}




	@Override
	public Itinerario findByUsername(String username) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public Itinerario toPerfilUsuario(ResultSet res) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
    
	
	
}
