package fabrica;

import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public class Fabrica {
	private static final Properties props = new Properties();

	static {
		try {
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));
		} catch (Exception e) {
			throw new FabricaException("No se ha podido cargar el fabrica.properties", e);
		}
		
	}

	public static <T> T getObjeto(String clave, Class<T> tipo) { // "daos.persona"
		String nombreClase = props.getProperty(clave); // "daos.DaoPersonaMap"
		
		T instancia;
		
		try {
			@SuppressWarnings("unchecked")
			var clase = (Class<T>)Class.forName(nombreClase); // daos.DaoPersonaMap
			var constructor = clase.getConstructor(); // public daos.DaoPersonaMap() {}
			instancia = constructor.newInstance();
		} catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new FabricaException("No se ha podido instanciar el objeto", e);
		}
		
		return instancia;
	}
}
