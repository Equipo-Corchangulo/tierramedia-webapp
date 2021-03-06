package controllers.promociones;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import services.AtraccionService;
import services.PromocionService;

@WebServlet("/promociones/lista")
public class PromocionesListServlet extends HttpServlet implements Servlet {
	private PromocionService promocionService;
	@Override
	public void init() throws ServletException {
		super.init();
		promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if (logedUser != null) {
			try {
				req.setAttribute("promocionList", promocionService.list());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("selectedMenu", "promociones");
			getServletContext()
					.getRequestDispatcher("/views/promociones/lista.jsp")
					.forward(req, resp);
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}
}
