package controllers.tipoatracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.TipoAtraccionService;
import services.UsuarioService;

import java.io.IOException;

@WebServlet("/tipos/delete.adm")
public class TipoAtraccionesDeleteServlet extends HttpServlet implements Servlet {

    TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        tipoAtraccionService = new TipoAtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        tipoAtraccionService.delete(id);
        resp.sendRedirect("/tierramedia/tipos/lista.adm");
    }
}
