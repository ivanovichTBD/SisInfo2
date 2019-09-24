import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Conexion {
	
	private static final String CONTROLADOR ="com.mysql.jdbc.Driver";
	private static final String URL ="jdbc:mysql://localhost:3309/postre_db";
	private static final String USER ="root";
	private static final String PASS ="";
	
	static {
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar jdbc");
			e.printStackTrace();
		}
	}
	
	public Connection conectar() {
		Connection conexion = null;
		try {
			
			conexion = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Conexion OK");
		}catch (SQLException e) {
			System.out.println("Error en la conexion");
			e.printStackTrace();
		}
		return conexion;
	}
}
