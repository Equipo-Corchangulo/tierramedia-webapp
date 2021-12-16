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

		 Itinerario itinerario = (Itinerario) req.getSession().getAttribute("itinerario");
	        if (itinerario != null) {
	            Integer id = Integer.parseInt(req.getParameter("id"));
	           	try {
					itinerarioService.find(id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           	
				getServletContext()
						.getRequestDispatcher("/tierramedia/views/noadmin/itinerario.jsp")
						.forward(req, resp);
			
		}	        } 
	    
		
		
}
