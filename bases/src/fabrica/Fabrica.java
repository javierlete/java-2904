package fabrica;

import java.util.Properties;

import daos.DaoPersona;
import daos.DaoPersonaCollection;
import daos.DaoPersonaMap;

public class Fabrica {
	private static final DaoPersona daoPersona;

	static {
		Properties props = new Properties();
		
		try {
			props.load(Fabrica.class.getClassLoader().getResourceAsStream("fabrica.properties"));
		} catch (Exception e) {
			throw new FabricaException("No se ha podido cargar el fabrica.properties", e);
		}
		
		String tipo = props.getProperty("daos.persona");
		
		daoPersona = switch (tipo) {
		case "coleccion" -> new DaoPersonaCollection();
		case "mapa" -> new DaoPersonaMap();
		default -> throw new IllegalArgumentException("Tipo no esperado: " + tipo);
		};
	}

	public static DaoPersona getDaoPersona() {
		return daoPersona;
	}
}
