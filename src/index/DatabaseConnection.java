package index;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

	public Connection connection;
	public Statement statement;
	
	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ptpuddingdb", "root", "");
			statement = connection.createStatement();
			System.out.println("Create connection successful");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public void migrateTable() {
		createMenuTable();
	}
	
	public void createMenuTable() {
		String query = "CREATE TABLE IF NOT EXISTS menu(" 
					+ "kodeMenu CHAR(6) PRIMARY KEY," 
					+ "namaMenu VARCHAR (50) NOT NULL,"
					+ "hargaMenu INT NOT NULL,"
					+ "stokMenu INT NOT NULL)";
		exec(query);
	}
	
	
	public void exec(String query) {
		try {
			statement.execute(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
