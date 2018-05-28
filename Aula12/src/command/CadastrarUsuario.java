package command;

 
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 

 
import model.Usuario;
import service.UsuarioService;
import criptografia.CryptoAES;

public class CadastrarUsuario implements Command {

	@Override
	public void executar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String nome = request.getParameter("username");
		String senha = request.getParameter("passwd");
		
		
		CryptoAES aes = new CryptoAES();
		Usuario usuario = new Usuario();
		usuario.setUsername(nome);
		usuario.setPassword(senha);
		
		try {
			usuario.setUsername(aes.encrypt(nome, "rootrootrootroot"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		 
			 
		 
		
		UsuarioService service = new UsuarioService();		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		service.criar(usuario);

		view = request.getRequestDispatcher("Login.jsp");
		view.forward(request, response);
 
		
			} 
		
		 
	}