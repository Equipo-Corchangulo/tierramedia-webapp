package controllers.tipoatracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import model.TipoAtraccion;
import services.TipoAtraccionService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/tipos/modelchange.adm")
public class TipoAtraccionesDinamicServlet extends HttpServlet implements Servlet {
    TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        tipoAtraccionService = new TipoAtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
        if (logedUser != null && logedUser.isAdmin()) {
            String viewState = "create";
            req.setAttribute("selectedMenu", "tipo");
            String tipoID = req.getParameter("id");
            if (tipoID != null) {
                viewState = "update";
                TipoAtraccion tipoAtraccion = null;
                try {
                    tipoAtraccion = tipoAtraccionService.find(Integer.parseInt(tipoID));
                    req.setAttribute("tipoAtraccion", tipoAtraccion);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            req.setAttribute("viewState", viewState);
            getServletContext()
                    .getRequestDispatcher("/views/tipo/form.jsp")
                    .forward(req, resp);
        } else {
            resp.sendRedirect("/tierramedia/welcome");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
        if (logedUser != null && logedUser.isAdmin()) {
            String id = req.getParameter("id");
            String nombre = req.getParameter("nombre");
            if (id != null) {
                int numericId = Integer.parseInt(id);
                TipoAtraccion tipoAtraccion = new TipoAtraccion(numericId, nombre, true);
                tipoAtraccionService.update(tipoAtraccion);
            } else {
                TipoAtraccion tipoAtraccion = new TipoAtraccion(nombre);
                tipoAtraccionService.create(tipoAtraccion);
            }
            resp.sendRedirect("lista.adm");
        } else {
            resp.sendRedirect("/tierramedia/welcome");
        }
    }
}
