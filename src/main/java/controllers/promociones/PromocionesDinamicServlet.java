package controllers.promociones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Promocion;
import model.TipoAtraccion;
import services.PromocionService;
import services.TipoAtraccionService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/promociones/modelchange.adm")
public class PromocionesDinamicServlet extends HttpServlet implements Servlet {

    PromocionService promocionService;
    TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        promocionService = new PromocionService();
        tipoAtraccionService = new TipoAtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewState ="create";
        String id = req.getParameter("id");
        try {
            List<TipoAtraccion> tipoAtraccionList = tipoAtraccionService.list();
            req.setAttribute("tipoAtraccionList",tipoAtraccionList);
            if(id != null){
                viewState = "update";
                int numericId = Integer.parseInt(id);

                    Promocion promocion = promocionService.find(numericId);
                    req.setAttribute("promocion", promocion);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        req.setAttribute("viewState", viewState);
        req.setAttribute("selectedMenu", "promociones");
        getServletContext()
                .getRequestDispatcher("/views/promociones/form.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String desc = req.getParameter("descripcion");
       String lista = req.getParameter("lista");
       String nombre = req.getParameter("nombre");
    }
}
