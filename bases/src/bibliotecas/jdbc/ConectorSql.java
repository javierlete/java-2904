package bibliotecas.jdbc;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.function.Function;

import bibliotecas.fabrica.Fabrica;

public class ConectorSql {
	private static final String URL;
	private static final String USER;
	private static final String PASS;

	static {
		try {
			Properties props = new Properties();
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));

			URL = props.getProperty("URL");
			USER = props.getProperty("USER");
			PASS = props.getProperty("PASS");

		} catch (Exception e) {
			throw new DaoException("No se ha podido cargar el fabrica.properties", e);
		}
	}
	
	public static <T> Collection<T> ejecutarSql(String sql, Object... valores) {
		return ejecutarSql(sql, null, valores);
	}

	public static <T> Collection<T> ejecutarSql(String sql, Function<ResultSet, T> mapeador, Object... valores) {
		var objetos = new ArrayList<T>();

		try (var con = DriverManager.getConnection(URL, USER, PASS); var pst = con.prepareStatement(sql);) {
			for (int i = 0; i < valores.length; i++) {
				pst.setObject(i + 1, valores[i]);
			}

			if (pst.execute()) {
				var rs = pst.getResultSet();
				while (rs.next()) {
					objetos.add(mapeador.apply(rs));
				}

				return objetos;
			} else {
				return null;
			}
		} catch (SQLException e) {
			throw new DaoException("Error en la consulta", e);
		}
	}
}
