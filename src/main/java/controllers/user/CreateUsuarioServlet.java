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
import model.TipoDeAtraccion;
import services.TipoAtraccionService;
import services.UsuarioService;

@WebServlet("/usuario/create.adm")
public class CreateUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -4953157559512379392L;
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
			String viewState = "create";
			req.setAttribute("selectedMenu", "usuario");
			req.setAttribute("viewState", viewState);
			getServletContext()
					.getRequestDispatcher("/views/usuarios/create.jsp")
					.forward(req, resp);
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
		if (logedUser != null && logedUser.isAdmin()) {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String nombre = req.getParameter("nombre");
			Double money = Double.parseDouble(req.getParameter("money"));
			int tiempo = Integer.parseInt(req.getParameter("tiempo"));
			boolean isAdmin = req.getParameter("usertype").equals("Admin");
			TipoAtraccion tipo = null;
			try {
				tipo = tipoAtraccionService.getByName(req.getParameter("tipo").toUpperCase());
			} catch (Exception e) {
				e.printStackTrace();
			}

			PerfilUsuario user = null;
			try {
				user = usuarioService.create(nombre, username, password, money, tiempo, isAdmin, tipo, true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (user.isValid()) {
				resp.sendRedirect("lista.adm");
			} else {
				req.setAttribute("errors", user.validate());
				req.setAttribute("userInstance", user);

				getServletContext()
						.getRequestDispatcher("/views/usuarios/form.jsp")
						.forward(req, resp);
			}
		} else {
			resp.sendRedirect("/tierramedia/welcome");
		}
	}
}

