/**
 * 
 */
package storing.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Daniel Candido
 *
 */
public class AcessoDAO {

	private String dbUrl = "jdbc:oracle:thin:@localhost:1521:XE";
	private String dbUser = "Daniel";
	private String dbPassword = "123";
	private  Connection myConnection;

	public AcessoDAO() throws ClassNotFoundException {
		// Carrega driver do oracle
		Class.forName("oracle.jdbc.driver.OracleDriver");
	}

	public Connection abrirConexao() throws SQLException {
		setMyConnection(DriverManager.getConnection(dbUrl, dbUser, dbPassword));

		return getMyConnection();
	}

	public Connection getMyConnection() {
		return myConnection;
	}

	public void setMyConnection(Connection myConnection) {
		this.myConnection = myConnection;
	}
	
}