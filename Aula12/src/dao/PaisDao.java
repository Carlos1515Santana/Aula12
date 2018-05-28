package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Pais;
import dao.ConnectionFactory;

public class PaisDao {
	//Criar
		public int criar(Pais pais) {
			String sqlInsert = "INSERT INTO pais(nome,populacao, area) VALUES (?, ?, ?)";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
				
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulaçao());
				stm.setDouble(3 ,pais.getArea());
				stm.execute();
				String sqlQuery  = "SELECT LAST_INSERT_ID()";
				try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {	
					if(rs.next()){
						pais.setId(rs.getInt(1));
					}
				} catch (SQLException e) {
					e.printStackTrace();
					 
				}
				 
			} catch (SQLException e) {
				e.printStackTrace();
				 
			}
			return pais.getId();
		}
		
		
		public void atualizar(Pais pais) {
			String sqlUpdate = "UPDATE pais SET nome=?, populacao=?, area=? WHERE id=?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			 
				stm.setString(1, pais.getNome());
				stm.setLong(2, pais.getPopulaçao());
				stm.setDouble(3, pais.getArea());
				stm.setInt(4, pais.getId());
				stm.execute();
				 
			} catch (Exception e) {
				e.printStackTrace();
				 
			}
		 
			 
			 
		}
		public void excluir(int id) {
			String sqlDelete = "DELETE FROM pais WHERE id = ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlDelete);) {
				stm.setInt(1,id);
				stm.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 
		  } 
		
		

		public Pais carregar(int id) {
			Pais pais = new  Pais();
			pais.setId(id);

			String sqlSelect = "SELECT nome, populacao, area FROM pais WHERE pais.id = ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setInt(1, pais.getId());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()){	 
						pais.setNome(rs.getString("nome"));
						pais.setPopulaçao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
					} else {
						pais.setId(-1);
						pais.setNome(null);
						pais.setPopulaçao(0);
						pais.setArea(0);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return pais;
		}
		
	 
		
		public Pais maiorPopulacao(){
		 Pais populacao = new Pais();
		 String sqlSelect = "select * from Pais order by populacao desc limit 1";

			 try(Connection conn =   ConnectionFactory.obtemConexao();
		                    PreparedStatement stm = conn.prepareStatement(sqlSelect))
					 {
					 try(ResultSet rs = stm.executeQuery()){
						 if(rs.next()){
							 populacao.setPopulaçao(rs.getLong("populacao"));
						 }else{
							 populacao.setPopulaçao(-1);
						 }
					 } catch(SQLException e){
							 e.printStackTrace();
						 }
					 
		                    } catch(SQLException e1){
						        e1.printStackTrace();
					 }
		 
						 return populacao;
					 }
		
		public Pais menorArea(){
			 Pais area = new Pais();
			 String sqlSelect = "select * from Pais order by area asc limit 1;";

				 try(Connection conn =   ConnectionFactory.obtemConexao();
			                    PreparedStatement stm = conn.prepareStatement(sqlSelect))
						 {
						 try(ResultSet rs = stm.executeQuery()){
							 if(rs.next()){
								 area.setArea(rs.getDouble("area"));
							 }else{
								 area.setArea(-1);
							 }
						 } catch(SQLException e){
								 e.printStackTrace();
							 }
						 
			                    } catch(SQLException e1){
							        e1.printStackTrace();
						 }
			 
							 return area;
						 }
		
		
					 
			 
		public ArrayList<Pais> buscarTodos() throws SQLException {
			Connection conn = ConnectionFactory.obtemConexao();
			final String query = "select * from Pais";
			PreparedStatement prepare = null;
			ResultSet result = null;
			ArrayList<Pais> lista = new ArrayList<Pais>();
			try {
				prepare = conn.prepareStatement(query);
				result = prepare.executeQuery();
				while (result.next()) {
					Pais caso = new Pais();
					caso.setNome(result.getString("Brasil"));//0
					caso.setNome(result.getString("Alemanha"));//1
					caso.setNome(result.getString("EUA"));//2
					lista.add(caso);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return lista;
		}
		
		public ArrayList<Pais> listarTodos() {
			ArrayList<Pais> paises = new ArrayList<>();
			String sqlSelect = "SELECT id, nome, populacao, area FROM pais";
			Pais pais;

			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);
					ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						pais = new Pais();
						pais.setId(rs.getInt("id"));
						pais.setNome(rs.getString("nome"));
						pais.setPopulaçao(rs.getLong("populacao"));
						pais.setArea(rs.getDouble("area"));
						paises.add(pais);
					} 
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return paises;
		}
		
		public ArrayList<Pais> listarClientes(String chave) {
			Pais cliente;
			ArrayList<Pais> lista = new ArrayList<>();
			String sqlSelect = "SELECT id, nome, populacao, area FROM pais where upper(nome) like ?";
			// usando o try with resources do Java 7, que fecha o que abriu
			try (Connection conn = ConnectionFactory.obtemConexao();
					PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, "%" + chave.toUpperCase() + "%");
				try (ResultSet rs = stm.executeQuery();) {
					while (rs.next()) {
						cliente = new Pais();
						cliente.setId(rs.getInt("id"));
						cliente.setNome(rs.getString("nome"));
						cliente.setPopulaçao(rs.getLong("populacao"));
						cliente.setArea(rs.getDouble("area"));
						lista.add(cliente);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
			return lista;
		}

			
	


		 

}
