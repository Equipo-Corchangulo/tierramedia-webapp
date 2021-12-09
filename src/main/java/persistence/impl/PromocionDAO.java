package persistence.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import persistence.IAtraccionDAO;
import persistence.IPromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

public class PromocionDAO implements IPromocionDAO {
	private static IAtraccionDAO atraccionesDAO = DAOFactory.getAtraccionDAO();
	public Promocion toPromocion(ResultSet result) throws SQLException {
		TipoAtraccion tipoAtraccion =  DAOFactory.getTipoAtraccionDAO().find(result.getInt("tipo_atraccion"));
		Promocion promo;
		//List<Facturable> listaDeAtracciones, TipoDeAtraccion tipoDeAtraccion, String nombreDePromocion
		List<Facturable> listaDeAtracciones = new ArrayList<Facturable>();
		String[] atraccionIdList = result.getString("atraccion_list").split("-");
		for(String a : atraccionIdList) {
			listaDeAtracciones.add(atraccionesDAO.find(Integer.parseInt(a)));
		}
		boolean active = result.getBoolean("active");
	    int id = result.getInt("id");
		if(result.getDouble("porcentaje") >0 ) 
		{
			promo = new PromoPorcentual(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),result.getDouble("porcentaje"),active, id);
		}
		else if (result.getInt("atraccion_extra")!=0) {
			Atraccion atraccionExtra = atraccionesDAO.find(result.getInt("atraccion_extra"));
			promo = new PromoAxB(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),atraccionExtra,active, id);
		}
		else {
			promo = new PromoAbsoluta(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),result.getDouble("costo_fijo"),active, id);
		}
		return promo;
	}
	
	public List<Promocion>findAll() throws SQLException {
		String query = "SELECT *, GROUP_CONCAT(lista_atracciones.atraccion_id,'-') AS atraccion_list "
				+ "FROM promociones "
				+ " LEFT JOIN atracciones_promocion AS lista_atracciones on lista_atracciones.promocion_id = promociones.id"
				+ "  GROUP by lista_atracciones.promocion_id";
		List<Promocion> listaPromociones = new ArrayList<Promocion>();
        ResultSet result = ConnectionProvider.executeQuery(query);
        
        while (result.next()) {
        	listaPromociones.add(toPromocion(result));
        }
        
        return listaPromociones;
	}

	@Override
	public Promocion find(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(Promocion t) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) {
		String query = "UPDATE promociones SET active = 0 " + "WHERE id = " + id;
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
