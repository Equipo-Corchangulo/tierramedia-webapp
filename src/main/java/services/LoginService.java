package services;
import model.PerfilUsuario;
import persistence.IUsuarioDAO;
import persistence.commons.DAOFactory;

public class LoginService {

	public PerfilUsuario login(String username, String password) throws Exception {
		IUsuarioDAO userDao = DAOFactory.getUsuarioDAO();
		PerfilUsuario user = userDao.findByUsername(username);
    	
    	if (user == null || !user.auth(password)) {
    		return null;
    	}
    	return user;
	}
}
