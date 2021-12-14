package controllers.main;

import jakarta.servlet.Servlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.PerfilUsuario;
import services.LoginService;

import java.io.IOException;
@WebServlet("/welcome")
public class MainServlet  extends HttpServlet implements Servlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PerfilUsuario user = (PerfilUsuario) req.getSession().getAttribute("user");
        String redirectURL ="";
        if(user== null){
            redirectURL = "/tierramedia/login";
        }else {
            redirectURL = user.isAdmin()?"/tierramedia/usuario/lista.adm":"/tierramedia/home";
        }

        resp.sendRedirect(redirectURL);
    }

}
