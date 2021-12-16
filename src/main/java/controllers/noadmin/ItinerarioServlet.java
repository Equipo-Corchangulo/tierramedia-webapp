package controllers.noadmin;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Itinerario;
import model.PerfilUsuario;
import services.ItinerarioService;



@WebServlet("/itinerario")
public class ItinerarioServlet extends HttpServlet implements Servlet{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 502459824071482089L;
	private ItinerarioService itinerarioService;



	@Override
	public void init() throws ServletException {
		super.init();
		itinerarioService = new ItinerarioService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if (logedUser != null) {
		    
			
			getServletContext()
					.getRequestDispatcher("/views/noadmin/itinerario.jsp")
						.forward(req, resp);
		
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	} 
	    
		
		
}
