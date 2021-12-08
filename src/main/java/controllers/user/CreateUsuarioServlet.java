package controllers.user;

import java.io.IOException;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import model.TipoDeAtraccion;
import services.UsuarioService;

@WebServlet("/usuario/create.adm")
public class CreateUsuarioServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = -4953157559512379392L;
	UsuarioService usuarioService;
	
	@Override
	public void init() throws ServletException {
		super.init();
		usuarioService = new UsuarioService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("selectedMenu", "usuario");
		getServletContext()
			.getRequestDispatcher("/views/usuarios/create.jsp")
			.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String nombre = req.getParameter("nombre");
		Double money = Double.parseDouble(req.getParameter("money"));
		int tiempo = Integer.parseInt(req.getParameter("tiempo"));
		boolean isAdmin = req.getParameter("usertype").equals("Admin");
		TipoDeAtraccion tipo = TipoDeAtraccion.valueOf(req.getParameter("tipo").toUpperCase());
			
		PerfilUsuario user = usuarioService.create(nombre, username, password, money, tiempo, isAdmin, tipo, true);
		
		if(user.isValid()) {
			resp.sendRedirect("lista.adm");
		} else {
			req.setAttribute("errors", user.validate());
			req.setAttribute("userInstance", user);

			getServletContext()
				.getRequestDispatcher("/views/usuarios/create.jsp")
				.forward(req, resp);			
		}
	}
}

