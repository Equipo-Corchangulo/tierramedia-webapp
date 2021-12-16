package controller.home;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Facturable;
import model.Itinerario;
import model.PerfilUsuario;
import services.AtraccionService;
import services.ItinerarioService;
import services.PromocionService;
import utils.ComparadorDeFacturable;

@WebServlet("/home")
public class HomeServlet extends HttpServlet implements Servlet{
	PromocionService promocionService;
	AtraccionService atraccionService;
	
	public void init() throws ServletException {
		super.init();
		this.atraccionService = new AtraccionService();
		this.promocionService = new PromocionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if(logedUser != null && !logedUser.isAdmin()) {
			req.setAttribute("user", logedUser);
			List<Facturable> listaDeFacturables = new ArrayList<Facturable>();
			try {
				listaDeFacturables.addAll(atraccionService.list());
				listaDeFacturables.addAll(promocionService.list());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			Collections.sort(listaDeFacturables, new ComparadorDeFacturable(logedUser.getTipoDeAtraccion()));
			req.setAttribute("listaDeFacturables", listaDeFacturables);
			getServletContext()
				.getRequestDispatcher("/views/noadmin/home.jsp")
				.forward(req, resp);
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}

		

	}

}
