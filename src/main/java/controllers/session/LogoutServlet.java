package controllers.session;

import java.io.IOException;
import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet implements Servlet {
	
	private static final long serialVersionUID = -4027089359360036809L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Eliminamos de la sesion el atributo user
		//req.getSession().setAttribute("user", null);
		req.getSession().removeAttribute("user");
		
		// Preparamos el mensaje y el destino
		req.setAttribute("flash", "Hasta Pronto!");
		resp.sendRedirect("login");
	}
}