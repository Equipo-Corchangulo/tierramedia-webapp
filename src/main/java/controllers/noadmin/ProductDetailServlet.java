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

/**
 * Servlet implementation class ProductDetailServlet
 */
@WebServlet("/detail")
public class ProductDetailServlet extends HttpServlet implements Servlet {
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
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if(logedUser == null || logedUser.isAdmin() || !logedUser.isActive()) {
			resp.sendRedirect("/tierramedia/welcome");
		} else {
			
			int id = Integer.parseInt(req.getParameter("id"));
			boolean esPromo = Boolean.parseBoolean(req.getParameter("promo"));
			try {
				Facturable facturable = esPromo ? promocionService.find(id) : atraccionService.find(id);
				req.setAttribute("facturable", facturable);
				getServletContext()
				.getRequestDispatcher("/views/noadmin/detail.jsp")
					.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
