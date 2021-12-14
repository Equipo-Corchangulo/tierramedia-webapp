package controllers.atracciones;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.Atraccion;
import model.PerfilUsuario;
import model.TipoAtraccion;
import services.AtraccionService;
import services.TipoAtraccionService;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/atracciones/modelchange.adm")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class AtraccionesDinamicServlet extends HttpServlet implements Servlet {
    private static final String UPLOAD_DIRECTORY = "uploadfiles";
    AtraccionService atraccionService;
    TipoAtraccionService tipoAtraccionService;

    @Override
    public void init() throws ServletException {

        this.atraccionService = new AtraccionService();
        tipoAtraccionService = new TipoAtraccionService();
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
                if (id != null) {
                    viewState = "update";
                    int numericId = Integer.parseInt(id);

                    Atraccion atraccion = atraccionService.find(numericId);
                    req.setAttribute("atraccion", atraccion);

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            req.setAttribute("viewState", viewState);
            req.setAttribute("selectedMenu", "atracciones");
            getServletContext()
                    .getRequestDispatcher("/views/atracciones/form.jsp")
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
            String costo = req.getParameter("costo");
            String tiempo = req.getParameter("tiempo");
            String tipo = req.getParameter("tipo");
            String cupo = req.getParameter("cupo");

            String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) uploadDir.mkdir();
            String fileDir = "";
            for (Part part : req.getParts()) {
                String fileName = getFileName(part);
                String nombreTemp = nombre.replace(" ", "");
                if(!fileName.equals("image.png")){

                    fileDir = nombreTemp+fileName;
                    part.write(uploadPath + File.separator + fileDir);
                }

            }
            try {
                TipoAtraccion tipoAtraccion = tipoAtraccionService.getByName(tipo);
                int numericId = id == null ? -1 : Integer.parseInt(id);
                Atraccion atraccion = new Atraccion(nombre, Double.parseDouble(costo), Double.parseDouble(tiempo), Integer.parseInt(cupo), tipoAtraccion, numericId, true);
                if (!fileDir.isBlank()){
                    atraccion.setImageDir(fileDir);
                }
                if (atraccion.getID() != -1) {
                    atraccionService.update(atraccion);
                } else {
                    atraccionService.create(atraccion);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            resp.sendRedirect("lista.adm");
        } else {
            resp.sendRedirect("/tierramedia/welcome");
        }
    }

    private String getFileName(Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename"))
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
        }
        return "img.png";
    }
}
