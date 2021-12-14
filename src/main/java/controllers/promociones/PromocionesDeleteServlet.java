package controllers.promociones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import services.PromocionService;
import services.TipoAtraccionService;

import java.io.IOException;

@WebServlet("/promociones/delete.adm")
public class PromocionesDeleteServlet extends HttpServlet implements Servlet {
    PromocionService promocionService;

    @Override
    public void init() throws ServletException {
        super.init();
        promocionService = new PromocionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
        if (logedUser != null && logedUser.isAdmin()) {
            Integer id = Integer.parseInt(req.getParameter("id"));
            promocionService.delete(id);
            resp.sendRedirect("/tierramedia/promociones/lista.adm");
        } else {
            resp.sendRedirect("/tierramedia/welcome");
        }
    }
}
