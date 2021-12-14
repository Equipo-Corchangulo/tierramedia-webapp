package controllers.promociones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.*;
import services.AtraccionService;
import services.PromocionService;
import services.TipoAtraccionService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/promociones/modelchange.adm")
public class PromocionesDinamicServlet extends HttpServlet implements Servlet {

    PromocionService promocionService;
    TipoAtraccionService tipoAtraccionService;
    AtraccionService atraccionService;

    @Override
    public void init() throws ServletException {
        super.init();
        promocionService = new PromocionService();
        tipoAtraccionService = new TipoAtraccionService();
        atraccionService = new AtraccionService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerfilUsuario logedUser = (PerfilUsuario) req.getSession().getAttribute("user");
        if (logedUser != null && logedUser.isAdmin()) {
            String viewState = "create";
            String id = req.getParameter("id");
            try {
                List<TipoAtraccion> tipoAtraccionList = tipoAtraccionService.list();
                req.setAttribute("tipoAtraccionList", tipoAtraccionList);
                List<Atraccion> atraccionList = atraccionService.list();
                req.setAttribute("atraccionList", atraccionList);
                if (id != null) {
                    viewState = "update";
                    int numericId = Integer.parseInt(id);

                    Promocion promocion = promocionService.find(numericId);
                    req.setAttribute("promocion", promocion);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("viewState", viewState);
            req.setAttribute("selectedMenu", "promociones");
            getServletContext()
                    .getRequestDispatcher("/views/promociones/form.jsp")
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
            String lista = req.getParameter("atraccioneslist");
            String tipoPromo = req.getParameter("tipoPromo");
            String descriptcion = req.getParameter("descripcion");
            String TipoAtraccion = req.getParameter("tipo");
            String porcentaje = req.getParameter("porcentaje");
            String extra = req.getParameter("extra");
            String costoFinal = req.getParameter("costoFinal");
            String nombre = req.getParameter("nombre");
            int ID = -1;
            try {
                if (id != null) {
                    ID = Integer.parseInt(id);
                }
                TipoAtraccion tipoAtraccion = tipoAtraccionService.getByName(TipoAtraccion);
                Atraccion atraccionExtra = atraccionService.find(Integer.parseInt(extra));
                String[] listaAtraccionesstr = lista.split(",");
                List<Facturable> atraccionList = new ArrayList<Facturable>();
                Arrays.stream(listaAtraccionesstr).sequential().forEach(atracionid -> {
                    try {
                        atraccionList.add(atraccionService.find(Integer.parseInt(atracionid)));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                });
                Promocion promocion = null;
                switch (tipoPromo) {
                    case "AXB":
                        promocion = new PromoAxB(atraccionList, tipoAtraccion, nombre, atraccionExtra, true, ID, descriptcion);
                        break;
                    case "porcentaje":
                        promocion = new PromoPorcentual(atraccionList, tipoAtraccion, nombre, Double.parseDouble(porcentaje), true, ID, descriptcion);
                        break;
                    case "ABSOLUTA":
                        promocion = new PromoAbsoluta(atraccionList, tipoAtraccion, nombre, Double.parseDouble(costoFinal), true, ID, descriptcion);
                        break;
                    default:
                        //throw new Exception("mal tipo de promo");
                        break;
                }
                if (ID != -1) {
                    promocionService.update(promocion);
                } else {
                    promocionService.create(promocion);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("lista.adm");
        } else {
            resp.sendRedirect("/tierramedia/welcome");
        }
    }
}
