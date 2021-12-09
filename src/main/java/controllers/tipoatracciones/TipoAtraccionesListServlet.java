package controllers.tipoatracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import services.PromocionService;
import services.TipoAtraccionService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/tipos/lista.adm")
public class TipoAtraccionesListServlet extends HttpServlet implements Servlet {
    private TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        tipoAtraccionService = new TipoAtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setAttribute("tiposList", tipoAtraccionService.list());
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        req.setAttribute("selectedMenu", "tipo");
        getServletContext()
                .getRequestDispatcher("/views/tipo/lista.jsp")
                .forward(req, resp);
    }
}
