package service;

 
import model.Usuario;
import dao.UsuarioDAO;

public class UsuarioService {
	UsuarioDAO dao = new UsuarioDAO();
	
	
	public boolean validar(Usuario usuario){		 
		return dao.validar(usuario);
	}
	
	public String criar(Usuario pais) {
		return dao.criar(pais);
	}
	
}
