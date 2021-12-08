package persistence.commons;

import persistence.IAtraccionDAO;
import persistence.IPromocionDAO;
import persistence.IUsuarioDAO;
import persistence.impl.AtraccionesDAO;
import persistence.impl.PromocionDAO;
import persistence.impl.UsuariosDAOImpl;

public class DAOFactory {
	
	public static IUsuarioDAO getUsuarioDAO() {
		return new UsuariosDAOImpl();
	}
	
	public static IAtraccionDAO getAtraccionDAO() {
		return new AtraccionesDAO();
	}
	
	public static IPromocionDAO getPromocionDAO() {
		return new PromocionDAO();
	}
}
