package controllers.atracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Atraccion;
import model.Promocion;
import model.TipoAtraccion;
import services.AtraccionService;
import services.TipoAtraccionService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/atracciones/modelchange.adm")
public class AtraccionesDinamicServlet extends HttpServlet implements Servlet {
    AtraccionService atraccionService;
    TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {
    this.atraccionService = new AtraccionService();
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

                Atraccion atraccion = atraccionService.find(numericId);
                req.setAttribute("atraccion", atraccion);

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        req.setAttribute("viewState", viewState);
        req.setAttribute("selectedMenu", "atracciones");
        getServletContext()
                .getRequestDispatcher("/views/atracciones/form.jsp")
                .forward(req, resp);;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String nombre = req.getParameter("nombre");
        String costo = req.getParameter("costo");
        String tiempo = req.getParameter("tiempo");
        String tipo = req.getParameter("tipo");
        String cupo = req.getParameter("cupo");
        try{
            TipoAtraccion tipoAtraccion = tipoAtraccionService.getByName(tipo);
            int numericId = id==null?-1: Integer.parseInt(id);
            Atraccion atraccion = new Atraccion(nombre, Double.parseDouble(costo),Double.parseDouble(tiempo),Integer.parseInt(cupo),tipoAtraccion, numericId, true);
            if (atraccion.getID()!=-1){
                atraccionService.update(atraccion);
            }else {
                atraccionService.create(atraccion);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }





        resp.sendRedirect("lista.adm");
    }
}
