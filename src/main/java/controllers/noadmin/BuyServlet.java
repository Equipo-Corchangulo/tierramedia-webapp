package controllers.noadmin;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Facturable;
import model.PerfilUsuario;
import services.AtraccionService;
import services.PromocionService;
import jakarta.servlet.Servlet;

/**
 * Servlet implementation class BuyServlet
 */
@WebServlet("/buy.do")
public class BuyServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
	AtraccionService atraccionService;
	PromocionService promocionService;
    

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		this.atraccionService = new AtraccionService();
		this.promocionService = new PromocionService();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		boolean esPromocion = req.getParameter("promo").equals("true");
		String redir = esPromocion ? "promociones/lista": "atracciones/lista";
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		
		try {
			Facturable facturable =(Facturable) (esPromocion? promocionService.find(id): atraccionService.find(id));
			logedUser.agregarAtraccion(facturable);
			logedUser.update();
			resp.sendRedirect(redir);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			resp.sendRedirect("welcome");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		;
	}

}
