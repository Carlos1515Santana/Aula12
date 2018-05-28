package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

 
import model.Usuario;

public class UsuarioDAO {
	
	public String criar(Usuario pais) {
		String sqlInsert = "INSERT INTO usuario(username,password) VALUES (?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionUsuario.obterConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			
			stm.setString(1, pais.getUsername());
			stm.setString(2, pais.getPassword());			 
			stm.execute();
			String sqlQuery  = "SELECT max(username)";
			try(PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
				ResultSet rs = stm2.executeQuery();) {	
				if(rs.next()){
					pais.setUsername(rs.getString(""));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				 
			}
			 
		} catch (SQLException e) {
			e.printStackTrace();
			 
		}
		return pais.getUsername();
	}
	
	
	
	public boolean validar(Usuario usuario) {
		String sqlSelect = "SELECT username, password FROM usuario "
				+ "WHERE username = ? and password = ?";
		// pega a conexão em um try normal para que ela não seja fechada
		try {
			Connection conn = ConnectionUsuario.obterConexao();
			// usando o try with resources do Java 7, que fecha o que abriu
			try (PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
				stm.setString(1, usuario.getUsername());
				stm.setString(2, usuario.getPassword());
				try (ResultSet rs = stm.executeQuery();) {
					if (rs.next()) {
						return true;
					} else {
						return false;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				System.out.print(e1.getStackTrace());
			}
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		return false;
	}
}
