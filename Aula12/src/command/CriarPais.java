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

public class CriarPais implements Command {

	
	@Override
	public void executar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pId = request.getParameter("id");
		String pPopulacao = request.getParameter("populacao");
		String pArea = request.getParameter("area");
		String pNome = request.getParameter("nome");
		
		long populacao = Long.parseLong(pPopulacao);
		double area = Double.parseDouble(pArea);
		
		int id = -1;
		try {
			id = Integer.parseInt(pId);
		} catch (NumberFormatException e) {

		}

		Pais pais = new Pais();
		pais.setId(id);
		pais.setNome(pNome);
		pais.setPopulaçao(populacao);
		pais.setArea(area);
		PaisService ps = new PaisService();

		RequestDispatcher view = null;
		HttpSession session = request.getSession();

		ps.criar(pais);
		
		ArrayList<Pais> lista = new ArrayList<>();
		lista.add(pais);
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
