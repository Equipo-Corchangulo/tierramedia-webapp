package controllers.atracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.AtraccionService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/atracciones/delete.adm")
public class AtraccionesDeleteServlet extends HttpServlet implements Servlet {
    private AtraccionService atraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        atraccionService = new AtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer id = Integer.parseInt(req.getParameter("id"));
        atraccionService.delete(id);
        resp.sendRedirect("/tierramedia/atracciones/lista.adm");
    }
}
