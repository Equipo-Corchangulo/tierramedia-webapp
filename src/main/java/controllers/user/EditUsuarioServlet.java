package controllers.user;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import model.TipoAtraccion;
import services.TipoAtraccionService;
import services.UsuarioService;


@WebServlet("/usuario/edit.adm")
public class EditUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;

	UsuarioService usuarioService;
	TipoAtraccionService tipoAtraccionService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
		tipoAtraccionService = new TipoAtraccionService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario user = (PerfilUsuario) req.getSession().getAttribute("user");
		
		if (user != null && user.isAdmin()) {
			String viewState = "update";
			req.setAttribute("selectedMenu", "usuario");
			req.setAttribute("viewState", viewState);
			
			int id = Integer.parseInt(req.getParameter("id"));
			try {
				PerfilUsuario userToEdit = usuarioService.find(id);
				req.setAttribute("userToEdit", userToEdit);
				getServletContext()
				.getRequestDispatcher("/views/usuarios/edit.jsp")
				.forward(req, resp);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if (logedUser != null && logedUser.isAdmin()) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String nombre = req.getParameter("nombre");
			Double money = req.getParameter("money")!= null? Double.parseDouble(req.getParameter("money")): 0.0;
			int id = Integer.parseInt(req.getParameter("id"));
			double tiempo = req.getParameter("tiempo")!= null? Double.parseDouble(req.getParameter("tiempo")):0.0;
			boolean isAdmin = req.getParameter("usertype").equals("Admin");
			TipoAtraccion tipo = null;
			try {
				tipo = tipoAtraccionService.getByName(req.getParameter("tipo").toUpperCase());
			} catch (Exception e) {
				e.printStackTrace();
			}

			PerfilUsuario user = null;
			try {
				user = usuarioService.update(id, nombre, username, password, money, tiempo, isAdmin, tipo, true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (user.isValid()) {
				resp.sendRedirect("lista.adm");
			} else {
				req.setAttribute("errors", user.validate());
				req.setAttribute("userToEdit", user);

				getServletContext()
						.getRequestDispatcher("/views/usuarios/edit.jsp")
						.forward(req, resp);
			}
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}

}
