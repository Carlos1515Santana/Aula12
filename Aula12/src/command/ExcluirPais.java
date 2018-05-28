package command;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Pais;
import service.PaisService;

public class ExcluirPais implements Command {

	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		 
		
		 
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		
		System.out.print(id);
		Pais pais = new Pais();
		pais.setId(id);
		 
		PaisService ps = new PaisService();
		
		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		ps.excluir(pais.getId());
		@SuppressWarnings("unchecked")
		ArrayList<Pais> lista = (ArrayList<Pais>) session
				.getAttribute("lista");
		lista.remove(busca(pais, lista));
		session.setAttribute("lista", lista);
		view = request.getRequestDispatcher("ListaDePais.jsp");
		view.forward(request, response);

	}

	public int busca(Pais cliente, ArrayList<Pais> lista) {
		Pais to;
		for (int i = 0; i < lista.size(); i++) {
			to = lista.get(i);
			if (to.getId() == cliente.getId()) {
				return i;
			}
		}
		return -1;
	}

}