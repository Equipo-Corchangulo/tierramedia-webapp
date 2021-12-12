package persistence.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.*;
import persistence.IAtraccionDAO;
import persistence.IPromocionDAO;
import persistence.commons.ConnectionProvider;
import persistence.commons.DAOFactory;

import javax.xml.crypto.dsig.CanonicalizationMethod;

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
			promo = new PromoPorcentual(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),
					result.getDouble("porcentaje"),active, id, result.getString("descripcion"));
		}
		else if (result.getInt("atraccion_extra")!=0) {
			Atraccion atraccionExtra = atraccionesDAO.find(result.getInt("atraccion_extra"));
			promo = new PromoAxB(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),
					atraccionExtra,active, id, result.getString("descripcion"));
		}
		else {
			promo = new PromoAbsoluta(listaDeAtracciones, tipoAtraccion, result.getString("nombre"),
					result.getDouble("costo_fijo"),active, id, result.getString("descripcion"));
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
		String query = "SELECT *, GROUP_CONCAT(lista_atracciones.atraccion_id,'-') AS atraccion_list "
				+ "FROM promociones "
				+ " LEFT JOIN atracciones_promocion AS lista_atracciones on lista_atracciones.promocion_id = promociones.id"
				+ "  WHERE promociones.id = ? GROUP by lista_atracciones.promocion_id";

		Connection conn = ConnectionProvider.getConnection();
		PreparedStatement statement = conn.prepareStatement(query);
		statement.setString(1, String.valueOf(id));

		ResultSet resultados = statement.executeQuery();
		return toPromocion(resultados);
	}

	@Override
	public int insert(Promocion t) {
		String query = "";
		String lastIDQuery = "select last_insert_rowid() as lastId";
		String atraccionesQuery = "INSERT INTO atracciones_promocion(promocion_id,atraccion_id) values( ?,?)";

		Connection conn;
		try {
			conn = ConnectionProvider.getConnection();
			boolean hasExtra = false;
			PreparedStatement statement = null ;

			String extraColumn ="";
			switch(t.getTipoPromo()){
				case ABSOLUTA:
					query = "INSERT INTO promociones(descripcion,nombre,tipo_atraccion,costo_fijo)values(?,?,?,?)";
					statement = conn.prepareStatement(query);
					statement.setString(4,String.valueOf(t.obtenerCostoTotal()));
					break;
				case PORCENTUAL:
					query = "INSERT INTO promociones(descripcion,nombre,tipo_atraccion,porcentaje)values(?,?,?,?)";
					statement = conn.prepareStatement(query);
					statement.setString(4,String.valueOf(((PromoPorcentual)t).getPorcentajeDescuento()));
					break;
				case AXB:
					query = "INSERT INTO promociones(descripcion,nombre,tipo_atraccion,atraccion_extra)values(?,?,?,?)";
					statement = conn.prepareStatement(query);
					hasExtra = true;
					statement.setString(4,String.valueOf(
							((Atraccion)((PromoAxB)t).getAtraccionExtra()).getID()
					));
					break;
			}
			statement.setString(1,t.getDescripcion());
			statement.setString(2,t.getNombreDePromocion());
			statement.setString(3,String.valueOf(t.getTipo().getID()));
			statement.execute();
			PreparedStatement lastIdSatatement = conn.prepareStatement(lastIDQuery);
			ResultSet res = lastIdSatatement.executeQuery();
			int lastId = res.getInt("lastId");
			boolean finalHasExtra = hasExtra;
			t.getListaDeAtracciones().stream().forEach(atraccion->{
				if(!(finalHasExtra && atraccion == ((PromoAxB)t).getAtraccionExtra()) ){
					PreparedStatement atraccionStatement = null;
					try {
						atraccionStatement = conn.prepareStatement(atraccionesQuery);
						atraccionStatement.setString(1,String.valueOf(lastId));
						atraccionStatement.setString(2,String.valueOf(((Atraccion)atraccion).getID()));
						atraccionStatement.execute();
					} catch (SQLException e) {
						e.printStackTrace();
					}

				}
			});
			return 0;
		}
		catch (SQLException e){
			e.printStackTrace();
			return -1;
		}
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
