package services;

import java.sql.SQLException;
import model.Itinerario;

import persistence.commons.DAOFactory;

public class ItinerarioService {


	
	public Itinerario find(Integer id) throws SQLException {
		return DAOFactory.getItinerarioDAO().find(id);
	}
	

}
