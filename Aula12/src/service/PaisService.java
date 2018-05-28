package service;

 
import model.Pais;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PaisDao;


public class PaisService {
	PaisDao dao = new PaisDao();
	
	public int criar(Pais pais) {
		return dao.criar(pais);
	}
	
	public void atualizar(Pais pais){
		dao.atualizar(pais);
	}
	
	public void excluir(int id){
		dao.excluir(id);
	}
	
	public Pais carregar(int id){
		return dao.carregar(id);
	}
	
	public Pais MenorArea(){
		return dao.menorArea();
	}
	public Pais MaiorPopulacao(){
		return dao.maiorPopulacao();
	}
	public ArrayList<Pais> buscarTodos() throws SQLException{
		return dao.buscarTodos();
	}
	public ArrayList<Pais> listarTodos() {
		return dao.listarTodos();
	}
	 
	public ArrayList<Pais> listarClientes(String chave){
		return dao.listarClientes(chave);
	}

	
	}




