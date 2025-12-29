package jdbc;

import java.sql.*;

public class EjemploJdbc {
	public static void main(String[] args) {
		final String URL = "jdbc:sqlite:ejemplo.db";
		final String USER = "";
		final String PASS = "";

		try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

			try (Statement st = con.createStatement()) {
				con.setAutoCommit(false);

				st.executeUpdate("DROP TABLE IF EXISTS personas");
				st.executeUpdate("""
						CREATE TABLE personas (
							id INTEGER PRIMARY KEY AUTOINCREMENT,
							nombre VARCHAR(50) NOT NULL,
							email VARCHAR(100) UNIQUE
						);
						""");

				st.executeUpdate("INSERT INTO personas (nombre, email) VALUES ('Javier', 'javier@email.net')");
				st.executeUpdate("INSERT INTO personas (nombre, email) VALUES ('Pepe', 'pepe@email.net')");

				con.commit();
				
				try (ResultSet rs = st.executeQuery("SELECT * FROM personas")) {
					while (rs.next()) {
						System.out.printf("%5s %-20s %-30s\n", rs.getString("idd"), rs.getString("nombre"),
								rs.getString("email"));
					}
				}
				
			} catch (SQLException e) {
				con.rollback();
				
				throw e;
			}
			
			try (PreparedStatement pst = con.prepareStatement("INSERT INTO personas (nombre, email) VALUES (?,?)")) {
				pst.setString(1, "Nuevo");
				pst.setString(2, "nuevo@email.net");
				
				pst.executeUpdate();
			}

		} catch (SQLException e) {

			System.out.println("Ha habido un error en el acceso a la base de datos");

			System.out.println(e.getMessage());

			e.printStackTrace();
		}

	}
}
