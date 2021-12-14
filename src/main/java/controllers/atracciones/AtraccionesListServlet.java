package controllers.atracciones;

import java.io.File;
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

@WebServlet("/atracciones/lista.adm")
public class AtraccionesListServlet extends HttpServlet implements Servlet {
	private static final String UPLOAD_DIRECTORY = "uploadfiles";
	private AtraccionService atraccionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		atraccionService = new AtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if (logedUser != null && logedUser.isAdmin()) {
			try {
				req.setAttribute("atraccionList", atraccionService.list());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("selectedMenu", "atracciones");
			getServletContext()
					.getRequestDispatcher("/views/atracciones/lista.jsp")
					.forward(req, resp);
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}

}
