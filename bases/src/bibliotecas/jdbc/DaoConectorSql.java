package bibliotecas.jdbc;

import static bibliotecas.jdbc.ConectorSql.ejecutarSql;

import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DaoConectorSql<T> implements Dao<T> {

	private String tabla;
	private String[] campos;
	protected Function<ResultSet, T> mapeador;
	protected Function<T, Object[]> mapeadorInverso;
	
	public DaoConectorSql(String tabla, String... campos) {
		this.tabla = tabla;
		this.campos = campos;
		
	}

	@Override
	public Collection<T> obtenerTodos() {
		String listaCampos = String.join(",", campos);
		String sql = String.format("SELECT id, %s FROM %s", listaCampos, tabla);
		
		return ejecutarSql(sql, mapeador);
	}

	@Override
	public Optional<T> obtenerPorId(Long id) {
		String listaCampos = String.join(",", campos);
		String sql = String.format("SELECT id, %s FROM %s WHERE id=?", listaCampos, tabla);
		
		return ejecutarSql(sql, mapeador, id).stream().findFirst();
	}

	@Override
	public T insertar(T objeto) {
		String listaCampos = String.join(",", campos);
		String interrogaciones = String.join(",", Collections.nCopies(campos.length, "?"));
		
		String sql = String.format("INSERT INTO %s (%s) VALUES (%s)", tabla, listaCampos, interrogaciones);

		ejecutarSql(sql, mapeadorInverso.apply(objeto));

		return objeto;
	}

	@Override
	public T modificar(T objeto) {
		String camposConInterrogaciones = Arrays.stream(campos).map(c -> c + "=?").collect(Collectors.joining(", "));
		
		String sql = String.format("UPDATE %s SET %s WHERE id=?", tabla, camposConInterrogaciones);

		ejecutarSql(sql, mapeadorInverso.apply(objeto));

		return objeto;
	}

	@Override
	public void borrar(Long id) {
		String sql = String.format("DELETE FROM %s WHERE id=?", tabla);
		
		ejecutarSql(sql, id);
	}

}
