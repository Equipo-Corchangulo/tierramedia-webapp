package persistence.commons;

import persistence.IAtraccionDAO;
import persistence.IItinerarioDAO;
import persistence.IPromocionDAO;
import persistence.ITipoAtraccionDAO;
import persistence.IUsuarioDAO;
import persistence.impl.AtraccionesDAO;
import persistence.impl.ItinerarioDAO;
import persistence.impl.PromocionDAO;
import persistence.impl.TipoAtraccionesDAOImpl;
import persistence.impl.UsuariosDAOImpl;

public class DAOFactory {
	
	public static IUsuarioDAO getUsuarioDAO() {
		return new UsuariosDAOImpl();
	}
	
	public static IAtraccionDAO getAtraccionDAO() {
		return new AtraccionesDAO();
	}
	
	public static IPromocionDAO getPromocionDAO() { return new PromocionDAO(); }

	public static ITipoAtraccionDAO getTipoAtraccionDAO() { return new TipoAtraccionesDAOImpl(); }


public static IItinerarioDAO getItinerarioDAO() {
	return new ItinerarioDAO();
}
}